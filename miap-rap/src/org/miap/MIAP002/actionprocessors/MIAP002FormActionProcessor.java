/*******************************************************************************
 * Copyright 2013 Mojave Innovations GmbH
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 * Contributors:
 *     Mojave Innovations GmbH - initial API and implementation
 ******************************************************************************/
/**
 * 
 */
package org.miap.MIAP002.actionprocessors;

import org.entirej.ServiceRetriever;
import org.entirej.framework.core.EJActionProcessorException;
import org.entirej.framework.core.EJApplicationException;
import org.entirej.framework.core.EJBlock;
import org.entirej.framework.core.EJForm;
import org.entirej.framework.core.EJManagedFrameworkConnection;
import org.entirej.framework.core.EJRecord;
import org.entirej.framework.core.EJScreenItem;
import org.entirej.framework.core.actionprocessor.interfaces.EJFormActionProcessor;
import org.entirej.framework.core.enumerations.EJRecordType;
import org.entirej.framework.core.enumerations.EJScreenType;
import org.entirej.framework.core.service.EJQueryCriteria;
import org.entirej.framework.core.service.EJRestrictions;
import org.entirej.framework.core.service.EJStatementExecutor;
import org.entirej.framework.core.service.EJStatementParameter;
import org.miap.forms.actions.MIAPDefaultFormActionProcessor;
import org.miap.forms.constants.F_MIAP002;
import org.miap.services.InvPosSequenceService;
import org.miap.services.InvoiceService;

/**
 * This class is used to perform all business functionalities including data
 * validations related to INVOICE & INVOICE_POSITIONS block service.
 */
public class MIAP002FormActionProcessor extends MIAPDefaultFormActionProcessor implements EJFormActionProcessor
{
    @Override
    public void newFormInstance(EJForm form) throws EJActionProcessorException
    {
        super.newFormInstance(form);

        // use the focused invoice record to validate the toolbar state
        EJBlock invoiceBlock = form.getBlock(F_MIAP002.B_INVOICE_BLOCK.ID);
        EJRecord invRecord = invoiceBlock.getFocusedRecord();

        validateToolbarState(form.getBlock(F_MIAP002.B_INVOICE_TOOLBAR.ID), false);

        validateToolbarState(form.getBlock(F_MIAP002.B_INVOICE_POSITIONS_TOOLBAR.ID), invRecord != null);
        validateToolbarCreateState(form.getBlock(F_MIAP002.B_INVOICE_POSITIONS_TOOLBAR.ID), invRecord != null);

        form.getBlock(F_MIAP002.B_INVOICE_BLOCK.ID).executeQuery();
        form.getBlock(F_MIAP002.B_INV_INV_POS.ID).executeQuery();
        updateStatus(form);

    }
    
    void updateStatus(EJForm form)
    {
        form.getApplicationLevelParameter("SB_SECTION_02").setValue("Invouces: "+String.valueOf(form.getBlock(F_MIAP002.B_INVOICE_BLOCK.ID).getBlockRecords().size()));
    }

    @Override
    public void executeActionCommand(EJForm form, EJRecord record, String command, EJScreenType screenType) throws EJActionProcessorException
    {
        if (record.getBlockName() != null && ((record.getBlockName().equals(F_MIAP002.B_INVOICE_BLOCK.ID)) || record.getBlockName().equals(F_MIAP002.B_INVOICE_TOOLBAR.ID)))
        {
            if (F_MIAP002.AC_TOOLBAR_NEW.equals(command))
            {
                form.getBlock(F_MIAP002.B_INVOICE_BLOCK.ID).enterInsert(false);
                return;
            }
            if (F_MIAP002.AC_TOOLBAR_EDIT.equals(command))
            {
                form.getBlock(F_MIAP002.B_INVOICE_BLOCK.ID).enterUpdate();
                return;
            }

            if (F_MIAP002.AC_TOOLBAR_DELETE.equals(command) && form.getBlock(F_MIAP002.B_INVOICE_BLOCK.ID).getFocusedRecord() != null)
            {
                // before deleting the selected record from database validate
                // and check if the record to be deleted has any FK constraints
                // usage with other table data and if so throw an exception and
                // block physical delete
                ServiceRetriever.getDBService(form).validateDeleteRecordUsage(form.getBlock(F_MIAP002.B_INVOICE_BLOCK.ID).getFocusedRecord(), "INVOICE");
                form.getBlock(F_MIAP002.B_INVOICE_BLOCK.ID).askToDeleteCurrentRecord("Are you sure you want to delete this invoice?");
                return;
            }
        }
        else if (record.getBlockName() != null && ((record.getBlockName().equals(F_MIAP002.B_INVOICE_POSITIONS_BLOCK.ID)) || record.getBlockName().equals(F_MIAP002.B_INVOICE_POSITIONS_TOOLBAR.ID)))
        {
            if (F_MIAP002.AC_TOOLBAR_NEW.equals(command))
            {
                form.getBlock(F_MIAP002.B_INVOICE_POSITIONS_BLOCK.ID).enterInsert(false);
                return;
            }
            if (F_MIAP002.AC_TOOLBAR_EDIT.equals(command))
            {
                form.getBlock(F_MIAP002.B_INVOICE_POSITIONS_BLOCK.ID).enterUpdate();
                return;
            }

            if (F_MIAP002.AC_TOOLBAR_DELETE.equals(command) && form.getBlock(F_MIAP002.B_INVOICE_POSITIONS_BLOCK.ID).getFocusedRecord() != null)
            {
                form.getBlock(F_MIAP002.B_INVOICE_POSITIONS_BLOCK.ID).askToDeleteCurrentRecord("Are you sure you want to delete this position?");
                return;
            }
        }
    }

    @Override
    public void postDelete(EJForm form, EJRecord record) throws EJActionProcessorException
    {
        super.postDelete(form, record);
        form.saveChanges();

        // update the invoice amounts after an invoice or invoice_position been
        // deleted
        updateInvoiceAmounts(form, record);

        // use the focused invoice record to validate the toolbar state after a
        // invoice or invoice_position record has been
        // deleted
        if (F_MIAP002.B_INVOICE_BLOCK.ID.equals(record.getBlockName()))
        {
            validateToolbarState(form.getBlock(F_MIAP002.B_INVOICE_TOOLBAR.ID), form.getBlock(F_MIAP002.B_INVOICE_BLOCK.ID).getFocusedRecord() != null);

            validateToolbarCreateState(form.getBlock(F_MIAP002.B_INVOICE_POSITIONS_TOOLBAR.ID), form.getBlock(F_MIAP002.B_INVOICE_BLOCK.ID).getFocusedRecord() != null);
            validateToolbarState(form.getBlock(F_MIAP002.B_INVOICE_POSITIONS_TOOLBAR.ID), form.getBlock(F_MIAP002.B_INVOICE_POSITIONS_BLOCK.ID).getFocusedRecord() != null && form.getBlock(F_MIAP002.B_INVOICE_BLOCK.ID).getFocusedRecord() != null);

        }
        if (F_MIAP002.B_INVOICE_POSITIONS_BLOCK.ID.equals(record.getBlockName()))
        {
            validateToolbarState(form.getBlock(F_MIAP002.B_INVOICE_POSITIONS_TOOLBAR.ID), form.getBlock(F_MIAP002.B_INVOICE_POSITIONS_BLOCK.ID).getFocusedRecord() != null && form.getBlock(F_MIAP002.B_INVOICE_BLOCK.ID).getFocusedRecord() != null);
        }
        
        updateStatus(form);
    }

    @Override
    public void preInsert(EJForm form, EJRecord record) throws EJActionProcessorException
    {

        super.preInsert(form, record);
        if (form.getBlock(F_MIAP002.B_INVOICE_BLOCK.ID).getName().equals(record.getBlockName()))
        {
            // before persisting new invoice record in the db set the
            // default values
            setInvCreationDefaults(form, record);
        }
        if (form.getBlock(F_MIAP002.B_INVOICE_POSITIONS_BLOCK.ID).getName().equals(record.getBlockName()))
        {
            // before persisting new cinvoice position record in the db set the
            // default values
            setInvPosCreationDefaults(form, record);
        }

    }

    /**
     * Set the default values for the invoice record before persisting them to
     * database
     * 
     * @param form
     *            - invoice block form
     * @param record
     *            - invoice record
     */
    private void setInvCreationDefaults(EJForm form, EJRecord record)
    {

        // set to default values
        if (record.containsItem(F_MIAP002.B_INVOICE_BLOCK.I_PRINTED))
        {
            record.setValue(F_MIAP002.B_INVOICE_BLOCK.I_PRINTED, 0);
        }
        if (record.containsItem(F_MIAP002.B_INVOICE_BLOCK.I_PAID))
        {
            record.setValue(F_MIAP002.B_INVOICE_BLOCK.I_PAID, 0);
        }
    }

    /**
     * Set the default values for the invoice position record before persisting
     * them to database
     * 
     * @param form
     *            - invoice position block form
     * @param record
     *            - invoice position record
     */
    private void setInvPosCreationDefaults(EJForm form, EJRecord record)
    {
        if (form.getBlock(F_MIAP002.B_INVOICE_POSITIONS_BLOCK.ID).getName().equals(record.getBlockName()))
        {
            if (record.containsItem(F_MIAP002.B_INVOICE_POSITIONS_BLOCK.I_INV_ID))
            {
                // Foreign key to INVOICE This is for invoice position
                // relation to invoice table
                EJRecord invoiceRecord = form.getBlock(F_MIAP002.B_INVOICE_BLOCK.ID).getFocusedRecord();
                record.setValue(F_MIAP002.B_INVOICE_POSITIONS_BLOCK.I_INV_ID, invoiceRecord.getValue(F_MIAP002.B_INVOICE_BLOCK.I_ID));
            }
            // auto-get sequence for the position number
            if (record.containsItem(F_MIAP002.B_INVOICE_POSITIONS_BLOCK.I_POSITION))
            {
                record.setValue(F_MIAP002.B_INVOICE_POSITIONS_BLOCK.I_POSITION, InvPosSequenceService.getInvPosSequence(form.getConnection()));
            }
        }

    }

    @Override
    public void validateRecord(EJForm form, EJRecord record, EJRecordType recordType) throws EJActionProcessorException
    {
        // check the screen type using record type to get the correct screen
        // items for record validation
        EJScreenType screenType = EJScreenType.MAIN;
        switch (recordType)
        {
            case INSERT:
            {
                screenType = EJScreenType.INSERT;
                break;
            }
            case UPDATE:
            {
                screenType = EJScreenType.UPDATE;
                break;
            }
            default:
                break;
        }
        // validate the invoice screen
        if (F_MIAP002.B_INVOICE_BLOCK.ID.equals(record.getBlockName()))
        {
            final Object nr = record.getValue(F_MIAP002.B_INVOICE_BLOCK.I_NR);
            final Object companyName = record.getValue(F_MIAP002.B_INVOICE_BLOCK.I_COMPANY_NAME);
            final Object customer = record.getValue(F_MIAP002.B_INVOICE_BLOCK.I_CUSTOMER_NAME);
            final Object term = record.getValue(F_MIAP002.B_INVOICE_BLOCK.I_PAYMENT_TERMS);
            final Object invoiceDate = record.getValue(F_MIAP002.B_INVOICE_BLOCK.I_INV_DATE);

            if (recordType == EJRecordType.INSERT || recordType == EJRecordType.UPDATE)
            {

                final EJScreenItem nrItem = form.getBlock(F_MIAP002.B_INVOICE_BLOCK.ID).getScreenItem(screenType, record.getItem(F_MIAP002.B_INVOICE_BLOCK.I_NR).getName());
                final EJScreenItem companyNameItem = form.getBlock(F_MIAP002.B_INVOICE_BLOCK.ID).getScreenItem(screenType, record.getItem(F_MIAP002.B_INVOICE_BLOCK.I_COMPANY_NAME).getName());
                final EJScreenItem customerNameItem = form.getBlock(F_MIAP002.B_INVOICE_BLOCK.ID).getScreenItem(screenType, record.getItem(F_MIAP002.B_INVOICE_BLOCK.I_CUSTOMER_NAME).getName());
                final EJScreenItem paymentTermsItem = form.getBlock(F_MIAP002.B_INVOICE_BLOCK.ID).getScreenItem(screenType, record.getItem(F_MIAP002.B_INVOICE_BLOCK.I_PAYMENT_TERMS).getName());
                final EJScreenItem invDateItem = form.getBlock(F_MIAP002.B_INVOICE_BLOCK.ID).getScreenItem(screenType, record.getItem(F_MIAP002.B_INVOICE_BLOCK.I_INV_DATE).getName());

                String nrError = validateRequiredField(nr, nrItem);
                if (nrError != null && nrError.length() > 0)
                {
                    throw new EJActionProcessorException(nrError);
                }
                String compNameError = validateRequiredField(companyName, companyNameItem);
                if (compNameError != null && compNameError.length() > 0)
                {
                    throw new EJActionProcessorException(compNameError);
                }
                String customerError = validateRequiredField(customer, customerNameItem);
                if (customerError != null && customerError.length() > 0)
                {
                    throw new EJActionProcessorException(customerError);
                }
                String termError = validateRequiredField(term, paymentTermsItem);
                if (termError != null && termError.length() > 0)
                {
                    throw new EJActionProcessorException(termError);
                }
                String invoiceDateError = validateRequiredField(invoiceDate, invDateItem);
                if (invoiceDateError != null && invoiceDateError.length() > 0)
                {
                    throw new EJActionProcessorException(invoiceDateError);
                }
            }
            if ((recordType == EJRecordType.INSERT) && InvoiceService.isNrExists(form.getConnection(), (String) nr))
            {
                throw new EJApplicationException("Entered Nr already Exist!");
            }
        }
        // validate the invoice position screen
        else if (F_MIAP002.B_INVOICE_POSITIONS_BLOCK.ID.equals(record.getBlockName()))
        {
            final Object prjName = record.getValue(F_MIAP002.B_INVOICE_POSITIONS_BLOCK.I_CUST_PROJ_NAME);
            final Object vatRate = record.getValue(F_MIAP002.B_INVOICE_POSITIONS_BLOCK.I_VAT_RATE);
            final Object hrsWorked = record.getValue(F_MIAP002.B_INVOICE_POSITIONS_BLOCK.I_HOURS_WORKED);
            final Object description = record.getValue(F_MIAP002.B_INVOICE_POSITIONS_BLOCK.I_DESCRIPTION);

            if (recordType == EJRecordType.INSERT || recordType == EJRecordType.UPDATE)
            {

                final EJScreenItem prjNameItem = form.getBlock(F_MIAP002.B_INVOICE_POSITIONS_BLOCK.ID).getScreenItem(screenType, record.getItem(F_MIAP002.B_INVOICE_POSITIONS_BLOCK.I_CUST_PROJ_NAME).getName());
                final EJScreenItem vatRateItem = form.getBlock(F_MIAP002.B_INVOICE_POSITIONS_BLOCK.ID).getScreenItem(screenType, record.getItem(F_MIAP002.B_INVOICE_POSITIONS_BLOCK.I_VAT_RATE).getName());
                final EJScreenItem hrsWorkedItem = form.getBlock(F_MIAP002.B_INVOICE_POSITIONS_BLOCK.ID).getScreenItem(screenType, record.getItem(F_MIAP002.B_INVOICE_POSITIONS_BLOCK.I_HOURS_WORKED).getName());
                final EJScreenItem descriptionItem = form.getBlock(F_MIAP002.B_INVOICE_POSITIONS_BLOCK.ID).getScreenItem(screenType, record.getItem(F_MIAP002.B_INVOICE_POSITIONS_BLOCK.I_DESCRIPTION).getName());

                String prjNameError = validateRequiredField(prjName, prjNameItem);
                if (prjNameError != null && prjNameError.length() > 0)
                {
                    throw new EJActionProcessorException(prjNameError);
                }
                String vatRateError = validateRequiredField(vatRate, vatRateItem);
                if (vatRateError != null && vatRateError.length() > 0)
                {
                    throw new EJActionProcessorException(vatRateError);
                }
                String hrsWorkedError = validateRequiredField(hrsWorked, hrsWorkedItem);
                if (hrsWorkedError != null && hrsWorkedError.length() > 0)
                {
                    throw new EJActionProcessorException(hrsWorkedError);
                }
                String descriptionError = validateRequiredField(description, descriptionItem);
                if (descriptionError != null && descriptionError.length() > 0)
                {
                    throw new EJActionProcessorException(descriptionError);
                }
            }
        }
    }

    private String validateRequiredField(final Object value, final EJScreenItem screenItem) throws EJActionProcessorException
    {

        if (screenItem == null)
        {
            throw new EJActionProcessorException(String.format("screenItem cannot be null !"));
        }

        final String label = screenItem.getLabel();

        if (value == null)
        {
            return String.format("%s cannot be Empty!", label);

        }

        if (value instanceof String)
        {
            if (((String) value).trim().length() == 0)
            {
                return String.format("%s cannot be Empty!", label);

            }
        }

        return null;
    }

    /**
     * This method is used to calculate the amount for a specific invoice
     * position
     * 
     * @param invPosRec
     *            - the invoice position record
     * @return
     */
    private Double calculateInvPosAmount(EJRecord invPosRec)
    {
        Double amount = 0.00;
        // if the invoice position record is null return 0 as the amount
        if (invPosRec == null)
        {
            return amount;
        }

        Double hrsWorked = (Double) invPosRec.getValue(F_MIAP002.B_INVOICE_POSITIONS_BLOCK.I_HOURS_WORKED);

        if (hrsWorked != null && hrsWorked > 0)
        {

            final Double payRate = (Double) invPosRec.getValue(F_MIAP002.B_INVOICE_POSITIONS_BLOCK.I_CUST_PROJ_PAY_RATE);

            if (payRate == null)
            {
                return amount;
            }
            amount = payRate * hrsWorked;

        }
        return amount;

    }

    @Override
    public void lovCompleted(EJForm form, EJScreenItem itemName, boolean arg2) throws EJActionProcessorException
    {

        super.lovCompleted(form, itemName, arg2);
        final EJBlock invPosBlock = form.getBlock(F_MIAP002.B_INVOICE_POSITIONS_BLOCK.ID);

        if (invPosBlock != null)
        {

            // if project name value in the following screen items change then
            // trigger the amount calculation
            if (F_MIAP002.B_INVOICE_POSITIONS_BLOCK.I_CUST_PROJ_NAME.equals(itemName.getName()))
            {
                final EJScreenType screenType = itemName.getScreenType();
                EJRecord invPosRec = invPosBlock.getCurrentScreenRecord(itemName.getScreenType());
                // set the calculated amount to the record instead of screen
                // item due to amount display field being a non block service
                // item
                invPosBlock.getScreenItem(screenType, F_MIAP002.B_INVOICE_POSITIONS_BLOCK.I_AMOUNT).setValue(calculateInvPosAmount(invPosRec));
            }
        }

    }

    @Override
    public void validateItem(final EJForm form, EJRecord record, String itemName, final EJScreenType screenType) throws EJActionProcessorException
    {

        super.validateItem(form, record, itemName, screenType);
        final EJBlock invPosBlock = form.getBlock(F_MIAP002.B_INVOICE_POSITIONS_BLOCK.ID);

        if (invPosBlock != null)
        {

            // if hours worked value in the following screen items change then
            // trigger the amount calculation
            if (F_MIAP002.B_INVOICE_POSITIONS_BLOCK.I_HOURS_WORKED.equals(itemName))
            {
                // set the calculated amount to the record instead of screen
                // item due to amount display field being a non block service
                // item
                record.setValue(F_MIAP002.B_INVOICE_POSITIONS_BLOCK.I_AMOUNT, calculateInvPosAmount(record));
            }
        }

    }

    @Override
    public void postInsert(EJForm form, EJRecord record) throws EJActionProcessorException
    {
        // all open changes within the form should be saved
        form.saveChanges();
        // refresh the invoice position totals after a new invoice position has
        // been created
        refreshInvPosTotals(form, record);
        // update the invoice amounts after a new invoice position has been
        // created
        updateInvoiceAmounts(form, record);
        updateStatus(form);
    }

    @Override
    public void postUpdate(EJForm form, EJRecord record) throws EJActionProcessorException
    {
        // all open changes within the form should be saved
        form.saveChanges();
        // refresh the invoice position totals after an invoice position has
        // been updated
        refreshInvPosTotals(form, record);
        // update the invoice amounts after an invoice position has been updated
        updateInvoiceAmounts(form, record);
    }

    @Override
    public void postQuery(EJForm form, EJRecord record) throws EJActionProcessorException
    {
        // refresh the invoice position totals after any change to record
        refreshInvPosTotals(form, record);
        updateStatus(form);

    }

    /**
     * This method is called after any database call to insert/update/delete of
     * invoice position record to calculate the invoice position amount
     * 
     * @param form
     *            - invoice position form
     * @param record
     *            - invoice position record
     */
    private void refreshInvPosTotals(EJForm form, EJRecord record)
    {
        if (record.getBlockName() != null && record.getBlockName().equals(F_MIAP002.B_INVOICE_POSITIONS_BLOCK.ID))
        {

            for (EJRecord invPosRec : form.getBlock(F_MIAP002.B_INVOICE_POSITIONS_BLOCK.ID).getBlockRecords())
            {
                invPosRec.setValue(F_MIAP002.B_INVOICE_POSITIONS_BLOCK.I_AMOUNT, calculateInvPosAmount(invPosRec));

            }
        }
    }

    /**
     * This method is called after any database call to insert/update/delete of
     * invoice record to update the invoice amounts and store in the database.
     * 
     * @param form
     *            - invoice form
     * @param record
     *            - invoice position record
     * @throws EJActionProcessorException
     */
    private void updateInvoiceAmounts(EJForm form, EJRecord record) throws EJActionProcessorException
    {
        if (record.getBlockName() != null && record.getBlockName().equals(F_MIAP002.B_INVOICE_POSITIONS_BLOCK.ID))
        {

            // get the selected invoice record
            EJRecord invRec = form.getBlock(F_MIAP002.B_INVOICE_BLOCK.ID).getFocusedRecord();
            if (invRec == null)
            {
                return;
            }
            Double amount = 0.00;
            Double amtWithVAT = 0.00;
            final EJManagedFrameworkConnection connection = form.getConnection();
            try
            {

                // calculate the amount and amount including vat
                for (EJRecord invPosRec : form.getBlock(F_MIAP002.B_INVOICE_POSITIONS_BLOCK.ID).getBlockRecords())
                {
                    amount += (Double) invPosRec.getValue(F_MIAP002.B_INVOICE_POSITIONS_BLOCK.I_AMOUNT);
                    amtWithVAT += (Double) invPosRec.getValue(F_MIAP002.B_INVOICE_POSITIONS_BLOCK.I_AMOUNT) + ((Double) invPosRec.getValue(F_MIAP002.B_INVOICE_POSITIONS_BLOCK.I_AMOUNT) * (Double) invPosRec.getValue(F_MIAP002.B_INVOICE_POSITIONS_BLOCK.I_VAT_RATE)) / 100;
                }

                // query the database and get the matching invoice record and
                // update it's amount values
                EJStatementExecutor statementExecutor = new EJStatementExecutor();
                EJQueryCriteria criteria = new EJQueryCriteria();
                criteria.add(EJRestrictions.equals("ID", invRec.getValue(F_MIAP002.B_INVOICE_BLOCK.I_ID)));
                statementExecutor.executeUpdate(form, "INVOICE", criteria, new EJStatementParameter[] { new EJStatementParameter("AMOUNT_EXCL_VAT", Double.class, amount), new EJStatementParameter("AMOUNT_INCL_VAT", Double.class, amtWithVAT) });

                // commit the database transaction
                connection.commit();

                invRec.setValue(F_MIAP002.B_INVOICE_BLOCK.I_AMOUNT_INCL_VAT, amtWithVAT);
            }
            catch (Exception e)
            {
                connection.rollback();
                throw new EJActionProcessorException(e.getMessage());
            }
            finally
            {
                connection.close();
            }

        }

    }

    @Override
    public void newRecordInstance(EJForm form, EJRecord record) throws EJActionProcessorException
    {
        // use the focused invoice record to validate all the toolbar states
        // when entering new record
        EJBlock invoiceBlock = form.getBlock(F_MIAP002.B_INVOICE_BLOCK.ID);
        EJRecord invRecord = invoiceBlock.getFocusedRecord();

        if (record.getBlockName().equals(F_MIAP002.B_INVOICE_BLOCK.ID))
        {
            validateToolbarState(form.getBlock(F_MIAP002.B_INVOICE_TOOLBAR.ID), record != null);

            validateToolbarCreateState(form.getBlock(F_MIAP002.B_INVOICE_POSITIONS_TOOLBAR.ID), record != null && invRecord != null);
            validateToolbarState(form.getBlock(F_MIAP002.B_INVOICE_POSITIONS_TOOLBAR.ID), form.getBlock(F_MIAP002.B_INVOICE_POSITIONS_BLOCK.ID).getFocusedRecord() != null && invRecord != null);

        }

        if (record.getBlockName().equals(F_MIAP002.B_INVOICE_POSITIONS_BLOCK.ID))
        {
            validateToolbarState(form.getBlock(F_MIAP002.B_INVOICE_POSITIONS_TOOLBAR.ID), record != null && invRecord != null);
        }

    }

    @Override
    public void postBlockQuery(EJForm form, EJBlock block) throws EJActionProcessorException
    {
        // use the focused invoice record to validate all the toolbar states
        // after a record is updated, deleted or newly added
        EJBlock invoiceBlock = form.getBlock(F_MIAP002.B_INVOICE_BLOCK.ID);
        EJRecord invRecord = invoiceBlock.getFocusedRecord();

        if (block.getName().equals(F_MIAP002.B_INVOICE_BLOCK.ID))
        {
            validateToolbarState(form.getBlock(F_MIAP002.B_INVOICE_TOOLBAR.ID), form.getBlock(F_MIAP002.B_INVOICE_BLOCK.ID).getFocusedRecord() != null && invRecord != null);

            validateToolbarCreateState(form.getBlock(F_MIAP002.B_INVOICE_POSITIONS_TOOLBAR.ID), form.getBlock(F_MIAP002.B_INVOICE_BLOCK.ID).getFocusedRecord() != null && invRecord != null);
            validateToolbarState(form.getBlock(F_MIAP002.B_INVOICE_POSITIONS_TOOLBAR.ID), form.getBlock(F_MIAP002.B_INVOICE_POSITIONS_BLOCK.ID).getFocusedRecord() != null && invRecord != null);
        }

        if (block.getName().equals(F_MIAP002.B_INVOICE_POSITIONS_BLOCK.ID))
        {
            validateToolbarState(form.getBlock(F_MIAP002.B_INVOICE_POSITIONS_TOOLBAR.ID), form.getBlock(F_MIAP002.B_INVOICE_POSITIONS_BLOCK.ID).getFocusedRecord() != null && invRecord != null);
        }
    }
}

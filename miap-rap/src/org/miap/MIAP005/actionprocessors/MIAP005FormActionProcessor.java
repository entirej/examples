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
package org.miap.MIAP005.actionprocessors;

import org.entirej.ServiceRetriever;
import org.entirej.framework.core.EJActionProcessorException;
import org.entirej.framework.core.EJBlock;
import org.entirej.framework.core.EJForm;
import org.entirej.framework.core.EJRecord;
import org.entirej.framework.core.EJScreenItem;
import org.entirej.framework.core.actionprocessor.interfaces.EJFormActionProcessor;
import org.entirej.framework.core.enumerations.EJRecordType;
import org.entirej.framework.core.enumerations.EJScreenType;
import org.miap.forms.actions.MIAPDefaultFormActionProcessor;
import org.miap.forms.constants.F_MIAP005;
import org.miap.services.PaymentTermsService;

/**
 * This class is used to perform all business functionalities including data validations related to
 * PAYMENT_INFORMATION block service.
 */
public class MIAP005FormActionProcessor extends MIAPDefaultFormActionProcessor implements EJFormActionProcessor
{
    @Override
    public void newFormInstance(EJForm form) throws EJActionProcessorException
    {
        super.newFormInstance(form);
        // validate the payment information toolbar state
        validateToolbarState(form.getBlock(F_MIAP005.B_PAYMENT_INFORMATION_TOOLBAR.ID), false);
        form.getBlock(F_MIAP005.B_PAYMENT_INFORMATION_BLOCK.ID).executeQuery();
    }

    @Override
    public void executeActionCommand(EJForm form, EJRecord record, String command, EJScreenType screenType) throws EJActionProcessorException
    {
        if (record.getBlockName() != null
                && ((record.getBlockName().equals(F_MIAP005.B_PAYMENT_INFORMATION_BLOCK.ID)) || record.getBlockName().equals(
                        F_MIAP005.B_PAYMENT_INFORMATION_TOOLBAR.ID)))
        {
            if (F_MIAP005.AC_TOOLBAR_NEW.equals(command))
            {
                form.getBlock(F_MIAP005.B_PAYMENT_INFORMATION_BLOCK.ID).enterInsert(false);
                return;
            }
            if (F_MIAP005.AC_TOOLBAR_EDIT.equals(command))
            {
                form.getBlock(F_MIAP005.B_PAYMENT_INFORMATION_BLOCK.ID).enterUpdate();
                return;
            }
            if (F_MIAP005.AC_TOOLBAR_DELETE.equals(command) && form.getBlock(F_MIAP005.B_PAYMENT_INFORMATION_BLOCK.ID).getFocusedRecord() != null)
            {
                // before deleting the selected record from database validate and check if the
                // record to be deleted has any FK constraints usage with other table data and if so
                // throw an exception and block physical delete
                ServiceRetriever.getDBService(form).validateDeleteRecordUsage(form.getBlock(F_MIAP005.B_PAYMENT_INFORMATION_BLOCK.ID).getFocusedRecord(),
                        "PAYMENT_INFORMATION");
                form.getBlock(F_MIAP005.B_PAYMENT_INFORMATION_BLOCK.ID).askToDeleteCurrentRecord("Are you sure you want to delete this payment term?");
                return;
            }
            if (F_MIAP005.AC_TOOLBAR_CLOSE.equals(command))
            {
                form.close();
                return;
            }
        }
    }

    @Override
    public void postDelete(EJForm form, EJRecord record) throws EJActionProcessorException
    {
        super.postDelete(form, record);
        form.saveChanges();
        // validate the payment information toolbar state after deleting payment information record
        if (F_MIAP005.B_PAYMENT_INFORMATION_BLOCK.ID.equals(record.getBlockName()))
        {
            validateToolbarState(form.getBlock(F_MIAP005.B_PAYMENT_INFORMATION_TOOLBAR.ID), form.getBlock(F_MIAP005.B_PAYMENT_INFORMATION_BLOCK.ID)
                    .getFocusedRecord() != null);
        }
    }

    @Override
    public void validateRecord(EJForm form, EJRecord record, EJRecordType recordType) throws EJActionProcessorException
    {
        // validate the payment information screen
        if (F_MIAP005.B_PAYMENT_INFORMATION_BLOCK.ID.equals(record.getBlockName()))
        {
            Object value = record.getValue(F_MIAP005.B_PAYMENT_INFORMATION_BLOCK.I_PAYMENT_TERMS);
            final EJScreenItem screenItem = form.getBlock(F_MIAP005.B_PAYMENT_INFORMATION_BLOCK.ID).getScreenItem(EJScreenType.MAIN,
                    record.getItem(F_MIAP005.B_PAYMENT_INFORMATION_BLOCK.I_PAYMENT_TERMS).getName());
            final String termsLabel = screenItem.getLabel();

            Object id = record.getValue(F_MIAP005.B_PAYMENT_INFORMATION_BLOCK.I_ID);

            if (recordType == EJRecordType.INSERT || recordType == EJRecordType.UPDATE)
            {
                if (value == null || ((String) value).trim().length() == 0)
                {
                    throw new EJActionProcessorException(String.format("%s cannot be Empty!", termsLabel));
                }

                String term = (String) value;

                if (PaymentTermsService.isPaymentTermExists(form.getConnection(), term, (Integer) id))
                {
                    throw new EJActionProcessorException(String.format("Entered %s already Exist!", termsLabel));
                }
            }
        }
    }

    @Override
    public void newRecordInstance(EJForm form, EJRecord record) throws EJActionProcessorException
    {
        // validate the toolbar states when
        // entering new record to the payment information screen
        if (record.getBlockName().equals(F_MIAP005.B_PAYMENT_INFORMATION_BLOCK.ID))
        {
            validateToolbarState(form.getBlock(F_MIAP005.B_PAYMENT_INFORMATION_TOOLBAR.ID), record != null);
        }
    }

    @Override
    public void postBlockQuery(EJForm form, EJBlock block) throws EJActionProcessorException
    {
        // validate the toolbar states after a record is updated, deleted or newly added to the
        // payment information screen
        if (block.getName().equals(F_MIAP005.B_PAYMENT_INFORMATION_BLOCK.ID))
        {
            validateToolbarState(form.getBlock(F_MIAP005.B_PAYMENT_INFORMATION_TOOLBAR.ID), form.getBlock(F_MIAP005.B_PAYMENT_INFORMATION_BLOCK.ID)
                    .getFocusedRecord() != null);
        }
    }
}

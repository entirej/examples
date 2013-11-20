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
package org.miap.MIAP004.actionprocessors;

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
import org.miap.forms.constants.F_MIAP004;

/**
 * This class is used to perform all business functionalities including data validations related to
 * COMPANY_INFORMATION block service.
 */
public class MIAP004FormActionProcessor extends MIAPDefaultFormActionProcessor implements EJFormActionProcessor
{
    @Override
    public void newFormInstance(EJForm form) throws EJActionProcessorException
    {
        form.getBlock(F_MIAP004.B_COMPANY_INFORMATION_BLOCK.ID).executeQuery();
    }

    @Override
    public void executeActionCommand(EJForm form, EJRecord record, String command, EJScreenType screenType) throws EJActionProcessorException
    {
        if (record.getBlockName() != null
                && ((record.getBlockName().equals(F_MIAP004.B_COMPANY_INFORMATION_BLOCK.ID)) || record.getBlockName().equals(
                        F_MIAP004.B_COMPANY_INFORMATION_TOOLBAR.ID)))
        {
            if (F_MIAP004.AC_TOOLBAR_NEW.equals(command))
            {
                form.getBlock(F_MIAP004.B_COMPANY_INFORMATION_BLOCK.ID).enterInsert(false);
                return;
            }
            if (F_MIAP004.AC_TOOLBAR_EDIT.equals(command))
            {
                form.getBlock(F_MIAP004.B_COMPANY_INFORMATION_BLOCK.ID).enterUpdate();
                return;
            }
            if (F_MIAP004.AC_TOOLBAR_DELETE.equals(command) && form.getBlock(F_MIAP004.B_COMPANY_INFORMATION_BLOCK.ID).getFocusedRecord() != null)
            {
                // before deleting the selected record from database validation and check if the
                // record to be deleted has any FK constraints usage with other table data and if so
                // throw an exception and block physical delete
                ServiceRetriever.getDBService(form).validateDeleteRecordUsage(form.getBlock(F_MIAP004.B_COMPANY_INFORMATION_BLOCK.ID).getFocusedRecord(),
                        "COMPANY_INFORMATION");
                form.getBlock(F_MIAP004.B_COMPANY_INFORMATION_BLOCK.ID).askToDeleteCurrentRecord("Are you sure you want to delete this company?");
                return;
            }
            if (F_MIAP004.AC_TOOLBAR_CLOSE.equals(command))
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
        // validate the company information toolbar state after deleting company
        // information record
        if (F_MIAP004.B_COMPANY_INFORMATION_BLOCK.ID.equals(record.getBlockName()))
        {
            validateToolbarState(form.getBlock(F_MIAP004.B_COMPANY_INFORMATION_TOOLBAR.ID), form.getBlock(F_MIAP004.B_COMPANY_INFORMATION_BLOCK.ID)
                    .getFocusedRecord() != null);
        }

    }

    @Override
    public void validateRecord(EJForm form, EJRecord record, EJRecordType recordType) throws EJActionProcessorException
    {
        // validate the company information screen
        if (F_MIAP004.B_COMPANY_INFORMATION_BLOCK.ID.equals(record.getBlockName()))
        {

            Object name = record.getValue(F_MIAP004.B_COMPANY_INFORMATION_BLOCK.I_NAME);
            Object bankName = record.getValue(F_MIAP004.B_COMPANY_INFORMATION_BLOCK.I_BANK_NAME);
            Object iban = record.getValue(F_MIAP004.B_COMPANY_INFORMATION_BLOCK.I_IBAN);

            final EJScreenItem nameItem = form.getBlock(F_MIAP004.B_COMPANY_INFORMATION_BLOCK.ID).getScreenItem(EJScreenType.MAIN,
                    record.getItem(F_MIAP004.B_COMPANY_INFORMATION_BLOCK.I_NAME).getName());

            final EJScreenItem bankNameItem = form.getBlock(F_MIAP004.B_COMPANY_INFORMATION_BLOCK.ID).getScreenItem(EJScreenType.MAIN,
                    record.getItem(F_MIAP004.B_COMPANY_INFORMATION_BLOCK.I_BANK_NAME).getName());
            final EJScreenItem ibanItem = form.getBlock(F_MIAP004.B_COMPANY_INFORMATION_BLOCK.ID).getScreenItem(EJScreenType.MAIN,
                    record.getItem(F_MIAP004.B_COMPANY_INFORMATION_BLOCK.I_IBAN).getName());

            if (recordType == EJRecordType.INSERT || recordType == EJRecordType.UPDATE)
            {

                String nameError = validateString(name, nameItem);
                if (nameError != null && nameError.length() > 0)
                {

                    throw new EJActionProcessorException(nameError);

                }
                String bankNameError = validateString(bankName, bankNameItem);
                if (bankNameError != null && bankNameError.length() > 0)
                {

                    throw new EJActionProcessorException(bankNameError);

                }
                String ibanError = validateString(iban, ibanItem);
                if (ibanError != null && ibanError.length() > 0)
                {

                    throw new EJActionProcessorException(ibanError);

                }

            }

        }
    }

    private String validateString(final Object value, final EJScreenItem screenItem) throws EJActionProcessorException
    {

        if (screenItem == null)
        {
            throw new EJActionProcessorException(String.format("screenItem cannot be null !"));
        }

        final String label = screenItem.getLabel();

        if (value == null || ((String) value).trim().length() == 0)
        {
            return String.format("%s cannot be Empty!", label);

        }

        return null;
    }

    @Override
    public void newRecordInstance(EJForm form, EJRecord record) throws EJActionProcessorException
    {
        // validate the toolbar states when
        // entering new record to the company information form
        if (record.getBlockName().equals(F_MIAP004.B_COMPANY_INFORMATION_BLOCK.ID))
        {
            validateToolbarState(form.getBlock(F_MIAP004.B_COMPANY_INFORMATION_TOOLBAR.ID), record != null);

        }
    }

    @Override
    public void postBlockQuery(EJForm form, EJBlock block) throws EJActionProcessorException
    {
        // validate the toolbar states after
        // a record is updated, deleted or newly added to the company
        // information screen
        if (block.getName().equals(F_MIAP004.B_COMPANY_INFORMATION_BLOCK.ID))
        {
            validateToolbarState(form.getBlock(F_MIAP004.B_COMPANY_INFORMATION_TOOLBAR.ID), form.getBlock(F_MIAP004.B_COMPANY_INFORMATION_BLOCK.ID)
                    .getFocusedRecord() != null);

        }
    }
}

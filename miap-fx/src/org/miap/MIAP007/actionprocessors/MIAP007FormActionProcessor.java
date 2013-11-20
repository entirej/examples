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
package org.miap.MIAP007.actionprocessors;

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
import org.miap.forms.constants.F_MIAP007;
import org.miap.services.VATRatesService;

/**
 * This class is used to perform all business functionalities including data
 * validations related to VAT_RATES block service.
 */
public class MIAP007FormActionProcessor extends MIAPDefaultFormActionProcessor implements EJFormActionProcessor
{
    @Override
    public void newFormInstance(EJForm form) throws EJActionProcessorException
    {
        form.getBlock(F_MIAP007.B_VAT_RATES_BLOCK.ID).executeQuery();
    }

    @Override
    public void executeActionCommand(EJForm form, EJRecord record, String command, EJScreenType screenType) throws EJActionProcessorException
    {
        if (record.getBlockName() != null && record.getBlockName().equals(F_MIAP007.B_VAT_RATES_TOOLBAR.ID))
        {
            if (F_MIAP007.AC_TOOLBAR_NEW.equals(command))
            {
                form.getBlock(F_MIAP007.B_VAT_RATES_BLOCK.ID).enterInsert(false);
                return;
            }
            if (F_MIAP007.AC_TOOLBAR_EDIT.equals(command))
            {
                form.getBlock(F_MIAP007.B_VAT_RATES_BLOCK.ID).enterUpdate();
                return;
            }
            if (F_MIAP007.AC_TOOLBAR_DELETE.equals(command) && form.getBlock(F_MIAP007.B_VAT_RATES_BLOCK.ID).getFocusedRecord() != null)
            {
                // before deleting the selected record from database validate
                // and check if the record to be deleted has any FK constraints
                // usage with other table data and if so throw an exception and
                // block physical delete
                ServiceRetriever.getDBService(form).validateDeleteRecordUsage(form.getBlock(F_MIAP007.B_VAT_RATES_BLOCK.ID).getFocusedRecord(), "VAT_RATES");
                form.getBlock(F_MIAP007.B_VAT_RATES_BLOCK.ID).askToDeleteCurrentRecord();
                return;
            }
            if (F_MIAP007.AC_TOOLBAR_CLOSE.equals(command))
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
        // validate the vat rates toolbar state after deleting vat rate
        // record
        if (F_MIAP007.B_VAT_RATES_BLOCK.ID.equals(form.getBlock(F_MIAP007.B_VAT_RATES_BLOCK.ID).getName()))
        {
            validateToolbarState(form.getBlock(F_MIAP007.B_VAT_RATES_TOOLBAR.ID), form.getBlock(F_MIAP007.B_VAT_RATES_BLOCK.ID).getFocusedRecord() != null);
        }
    }

    @Override
    public void validateRecord(EJForm form, EJRecord record, EJRecordType recordType) throws EJActionProcessorException
    {
        // validate the vat rates screen
        if (F_MIAP007.B_VAT_RATES_BLOCK.ID.equals(record.getBlockName()))
        {
            Object rate = record.getValue(F_MIAP007.B_VAT_RATES_BLOCK.I_RATE);
            Object name = record.getValue(F_MIAP007.B_VAT_RATES_BLOCK.I_NAME);
            Object id = record.getValue(F_MIAP007.B_VAT_RATES_BLOCK.I_ID);

            final EJScreenItem vatRateItem = form.getBlock(F_MIAP007.B_VAT_RATES_BLOCK.ID).getScreenItem(EJScreenType.MAIN,
                    record.getItem(F_MIAP007.B_VAT_RATES_BLOCK.I_RATE).getName());
            final String vatRateLabel = vatRateItem.getLabel();
            final EJScreenItem nameItem = form.getBlock(F_MIAP007.B_VAT_RATES_BLOCK.ID).getScreenItem(EJScreenType.MAIN,
                    record.getItem(F_MIAP007.B_VAT_RATES_BLOCK.I_NAME).getName());
            final String nameLabel = nameItem.getLabel();

            if (recordType == EJRecordType.INSERT || recordType == EJRecordType.UPDATE)
            {

                if (rate == null)
                {
                    throw new EJActionProcessorException(String.format("%s cannot be Empty!", vatRateLabel));
                }
                Double vatRate = (Double) rate;
                if (vatRate <= 0)
                {
                    throw new EJActionProcessorException(String.format("%s should be greater than zero!", vatRateLabel));
                }
                if (name == null || ((String) name).trim().length() == 0)
                {
                    throw new EJActionProcessorException(String.format("%s cannot be Empty!", nameLabel));
                }

                if (VATRatesService.isVatRateNameExists(form.getConnection(), (String) name, (Integer) id))
                {
                    throw new EJActionProcessorException(String.format("Entered %s already Exist!", nameLabel));
                }
            }
        }
    }

    @Override
    public void newRecordInstance(EJForm form, EJRecord record) throws EJActionProcessorException
    {
        // validate the toolbar states when
        // entering new record to the vat rates screen
        if (record.getBlockName().equals(F_MIAP007.B_VAT_RATES_BLOCK.ID))
        {
            validateToolbarState(form.getBlock(F_MIAP007.B_VAT_RATES_TOOLBAR.ID), record != null);
        }
    }

    @Override
    public void postBlockQuery(EJForm form, EJBlock block) throws EJActionProcessorException
    {
        // validate the toolbar states after
        // a record is updated, deleted or newly added to the vat rates screen
        if (block.getName().equals(F_MIAP007.B_VAT_RATES_BLOCK.ID))
        {
            validateToolbarState(form.getBlock(F_MIAP007.B_VAT_RATES_TOOLBAR.ID), form.getBlock(F_MIAP007.B_VAT_RATES_BLOCK.ID).getFocusedRecord() != null);
        }
    }

}

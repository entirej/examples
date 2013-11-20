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
package org.miap.forms.actions;

import org.entirej.framework.core.EJActionProcessorException;
import org.entirej.framework.core.EJBlock;
import org.entirej.framework.core.EJForm;
import org.entirej.framework.core.EJRecord;
import org.entirej.framework.core.EJScreenItem;
import org.entirej.framework.core.actionprocessor.EJDefaultFormActionProcessor;
import org.entirej.framework.core.enumerations.EJScreenType;
import org.entirej.framework.core.service.EJQueryCriteria;
import org.miap.forms.reusableblocks.constants.F_STANDARD_TOOLBAR_QIUD;
import org.miap.services.PKSequenceService;

/**
 * This is a custom class that extends
 * <code>EJDefaultFormActionProcessor.</code> Contains mandatory business
 * methods required to any FormActionProcessor extends this class.
 */
public class MIAPDefaultFormActionProcessor extends EJDefaultFormActionProcessor
{
    @Override
    public void validateQueryCriteria(EJForm form, EJQueryCriteria queryCriteria) throws EJActionProcessorException
    {
        // Don't do anything if query criteria is used in an Lov
        if (queryCriteria.isUsedInLov())
        {
            return;
        }

    }

    @Override
    public void preInsert(EJForm form, EJRecord record) throws EJActionProcessorException
    {
        // Don't do anything if this is a control block
        if (form.getBlock(record.getBlockName()).isControlBlock())
        {
            return;
        }

        if (record.containsItem("ID"))
        {
            // auto generated integer sequence number to set to the primary key
            // of record
            final int idSeqNextval = PKSequenceService.getPKSequence(form.getConnection());
            record.setValue("ID", idSeqNextval);
        }
    }

    /**
     * Validates enable/disable state of edit and delete buttons/links of the
     * toolbar block that has been created using EJControllerBlock.
     * 
     * @param toolbarBlock
     *            - the EJBlock used for the toolbar block
     * @param enable
     *            - boolean value used to set enable/disable state of the
     *            toolbar screen item
     */
    public final void validateToolbarState(final EJBlock toolbarBlock, boolean enable)
    {
        if (toolbarBlock != null)
        {

            if (toolbarBlock.containsItem(F_STANDARD_TOOLBAR_QIUD.B_STANDARD_TOOLBAR_QIUD.I_EDIT))
            {
                EJScreenItem editItem = toolbarBlock.getScreenItem(EJScreenType.MAIN, F_STANDARD_TOOLBAR_QIUD.B_STANDARD_TOOLBAR_QIUD.I_EDIT);
                editItem.setEditable(enable);
            }
            if (toolbarBlock.containsItem(F_STANDARD_TOOLBAR_QIUD.B_STANDARD_TOOLBAR_QIUD.I_DELETE))
            {
                EJScreenItem deleteItem = toolbarBlock.getScreenItem(EJScreenType.MAIN, F_STANDARD_TOOLBAR_QIUD.B_STANDARD_TOOLBAR_QIUD.I_DELETE);
                deleteItem.setEditable(enable);
            }

        }
    }

    /**
     * Validates enable/disable state of new button/link of the toolbar block
     * that has been created using EJControllerBlock.
     * 
     * @param toolbarBlock
     *            - the EJBlock used for the toolbar block
     * @param enable
     *            - boolean value used to set enable/disable state of the
     *            toolbar screen item
     */
    public void validateToolbarCreateState(final EJBlock toolbarBlock, boolean enable)
    {
        if (toolbarBlock.containsItem(F_STANDARD_TOOLBAR_QIUD.B_STANDARD_TOOLBAR_QIUD.I_NEW))
        {
            EJScreenItem newItem = toolbarBlock.getScreenItem(EJScreenType.MAIN, F_STANDARD_TOOLBAR_QIUD.B_STANDARD_TOOLBAR_QIUD.I_NEW);
            newItem.setEditable(enable);
        }
    }

}

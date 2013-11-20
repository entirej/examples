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
package org.miap.MIAP003.blockservices;

import java.util.ArrayList;
import java.util.List;
import org.entirej.framework.core.EJApplicationException;
import org.entirej.framework.core.EJForm;
import org.entirej.framework.core.service.EJBlockService;
import org.entirej.framework.core.service.EJQueryCriteria;
import org.entirej.framework.core.service.EJRestrictions;
import org.entirej.framework.core.service.EJStatementCriteria;
import org.entirej.framework.core.service.EJStatementExecutor;
import org.entirej.framework.core.service.EJStatementParameter;
import org.miap.MIAP003.blockservices.pojos.InvoicePositions;

public class InvoicePositionsBlockService implements EJBlockService<InvoicePositions>
{
    private final EJStatementExecutor _statementExecutor;
    private String                    _selectStatement = "SELECT CUPR_ID,DESCRIPTION,HOURS_WORKED,ID,INV_ID,POSITION,VAT_ID FROM INVOICE_POSITIONS";

    public InvoicePositionsBlockService()
    {
        _statementExecutor = new EJStatementExecutor();
    }

    @Override
    public boolean canQueryInPages()
    {
        return false;
    }

    @Override
    public List<InvoicePositions> executeQuery(EJForm form, EJQueryCriteria queryCriteria)
    {
        return _statementExecutor.executeQuery(InvoicePositions.class, form, _selectStatement, queryCriteria);
    }

    @Override
    public void executeInsert(EJForm form, List<InvoicePositions> newRecords)
    {
        List<EJStatementParameter> parameters = new ArrayList<EJStatementParameter>();
        int recordsProcessed = 0;
        for (InvoicePositions record : newRecords)
        {
            // Initialise the value list
            parameters.clear();
            parameters.add(new EJStatementParameter("CUPR_ID", Integer.class, record.getCuprId()));
            parameters.add(new EJStatementParameter("DESCRIPTION", String.class, record.getDescription()));
            parameters.add(new EJStatementParameter("HOURS_WORKED", Double.class, record.getHoursWorked()));
            parameters.add(new EJStatementParameter("ID", Integer.class, record.getId()));
            parameters.add(new EJStatementParameter("INV_ID", Integer.class, record.getInvId()));
            parameters.add(new EJStatementParameter("POSITION", Integer.class, record.getPosition()));
            parameters.add(new EJStatementParameter("VAT_ID", Integer.class, record.getVatId()));
            EJStatementParameter[] paramArray = new EJStatementParameter[parameters.size()];
            recordsProcessed += _statementExecutor.executeInsert(form, "INVOICE_POSITIONS", parameters.toArray(paramArray));
            record.clearInitialValues();
        }
        if (recordsProcessed != newRecords.size())
        {
            throw new EJApplicationException("Unexpected amount of records processed in insert. Expected: " + newRecords.size() + ". Inserted: "
                    + recordsProcessed);
        }
    }

    @Override
    public void executeUpdate(EJForm form, List<InvoicePositions> updateRecords)
    {
        List<EJStatementParameter> parameters = new ArrayList<EJStatementParameter>();

        int recordsProcessed = 0;
        for (InvoicePositions record : updateRecords)
        {
            parameters.clear();

            // First add the new values
            parameters.add(new EJStatementParameter("CUPR_ID", Integer.class, record.getCuprId()));
            parameters.add(new EJStatementParameter("DESCRIPTION", String.class, record.getDescription()));
            parameters.add(new EJStatementParameter("HOURS_WORKED", Double.class, record.getHoursWorked()));
            parameters.add(new EJStatementParameter("ID", Integer.class, record.getId()));
            parameters.add(new EJStatementParameter("INV_ID", Integer.class, record.getInvId()));
            parameters.add(new EJStatementParameter("POSITION", Integer.class, record.getPosition()));
            parameters.add(new EJStatementParameter("VAT_ID", Integer.class, record.getVatId()));

            EJStatementCriteria criteria = new EJStatementCriteria();
            if (record.getInitialCuprId() == null)
            {
                criteria.add(EJRestrictions.isNull("CUPR_ID"));
            }
            else
            {
                criteria.add(EJRestrictions.equals("CUPR_ID", record.getInitialCuprId()));
            }
            if (record.getInitialDescription() == null)
            {
                criteria.add(EJRestrictions.isNull("DESCRIPTION"));
            }
            else
            {
                criteria.add(EJRestrictions.equals("DESCRIPTION", record.getInitialDescription()));
            }
            if (record.getInitialHoursWorked() == null)
            {
                criteria.add(EJRestrictions.isNull("HOURS_WORKED"));
            }
            else
            {
                criteria.add(EJRestrictions.equals("HOURS_WORKED", record.getInitialHoursWorked()));
            }
            if (record.getInitialId() == null)
            {
                criteria.add(EJRestrictions.isNull("ID"));
            }
            else
            {
                criteria.add(EJRestrictions.equals("ID", record.getInitialId()));
            }
            if (record.getInitialInvId() == null)
            {
                criteria.add(EJRestrictions.isNull("INV_ID"));
            }
            else
            {
                criteria.add(EJRestrictions.equals("INV_ID", record.getInitialInvId()));
            }
            if (record.getInitialPosition() == null)
            {
                criteria.add(EJRestrictions.isNull("POSITION"));
            }
            else
            {
                criteria.add(EJRestrictions.equals("POSITION", record.getInitialPosition()));
            }
            if (record.getInitialVatId() == null)
            {
                criteria.add(EJRestrictions.isNull("VAT_ID"));
            }
            else
            {
                criteria.add(EJRestrictions.equals("VAT_ID", record.getInitialVatId()));
            }
            EJStatementParameter[] paramArray = new EJStatementParameter[parameters.size()];
            recordsProcessed += _statementExecutor.executeUpdate(form, "INVOICE_POSITIONS", criteria, parameters.toArray(paramArray));
            record.clearInitialValues();
        }
        if (recordsProcessed != updateRecords.size())
        {
            throw new EJApplicationException("Unexpected amount of records processed in update. Expected: " + updateRecords.size() + ". Updated: "
                    + recordsProcessed);
        }
    }

    @Override
    public void executeDelete(EJForm form, List<InvoicePositions> recordsToDelete)
    {
        ArrayList<EJStatementParameter> parameters = new ArrayList<EJStatementParameter>();

        int recordsProcessed = 0;
        for (InvoicePositions record : recordsToDelete)
        {
            parameters.clear();

            EJStatementCriteria criteria = new EJStatementCriteria();

            if (record.getInitialCuprId() == null)
            {
                criteria.add(EJRestrictions.isNull("CUPR_ID"));
            }
            else
            {
                criteria.add(EJRestrictions.equals("CUPR_ID", record.getInitialCuprId()));
            }
            if (record.getInitialDescription() == null)
            {
                criteria.add(EJRestrictions.isNull("DESCRIPTION"));
            }
            else
            {
                criteria.add(EJRestrictions.equals("DESCRIPTION", record.getInitialDescription()));
            }
            if (record.getInitialHoursWorked() == null)
            {
                criteria.add(EJRestrictions.isNull("HOURS_WORKED"));
            }
            else
            {
                criteria.add(EJRestrictions.equals("HOURS_WORKED", record.getInitialHoursWorked()));
            }
            if (record.getInitialId() == null)
            {
                criteria.add(EJRestrictions.isNull("ID"));
            }
            else
            {
                criteria.add(EJRestrictions.equals("ID", record.getInitialId()));
            }
            if (record.getInitialInvId() == null)
            {
                criteria.add(EJRestrictions.isNull("INV_ID"));
            }
            else
            {
                criteria.add(EJRestrictions.equals("INV_ID", record.getInitialInvId()));
            }
            if (record.getInitialPosition() == null)
            {
                criteria.add(EJRestrictions.isNull("POSITION"));
            }
            else
            {
                criteria.add(EJRestrictions.equals("POSITION", record.getInitialPosition()));
            }
            if (record.getInitialVatId() == null)
            {
                criteria.add(EJRestrictions.isNull("VAT_ID"));
            }
            else
            {
                criteria.add(EJRestrictions.equals("VAT_ID", record.getInitialVatId()));
            }
            EJStatementParameter[] paramArray = new EJStatementParameter[parameters.size()];
            recordsProcessed += _statementExecutor.executeDelete(form, "INVOICE_POSITIONS", criteria, parameters.toArray(paramArray));
            record.clearInitialValues();
        }
        if (recordsProcessed != recordsToDelete.size())
        {
            throw new EJApplicationException("Unexpected amount of records processed in delete. Expected: " + recordsToDelete.size() + ". Deleted: "
                    + recordsProcessed);
        }
    }

}

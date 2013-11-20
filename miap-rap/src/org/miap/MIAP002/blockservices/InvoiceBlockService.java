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
package org.miap.MIAP002.blockservices;

import java.sql.Date;
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
import org.miap.MIAP002.blockservices.pojos.Invoice;

public class InvoiceBlockService implements EJBlockService<Invoice>
{
    private final EJStatementExecutor _statementExecutor;
    private String                    _selectStatement = "SELECT AMOUNT_EXCL_VAT,AMOUNT_INCL_VAT,COINF_ID,CUST_ID,ID,INV_DATE,INV_ID,NR,PAID,PAYINF_ID,PRINTED FROM INVOICE";

    public InvoiceBlockService()
    {
        _statementExecutor = new EJStatementExecutor();
    }

    @Override
    public boolean canQueryInPages()
    {
        return false;
    }

    @Override
    public List<Invoice> executeQuery(EJForm form, EJQueryCriteria queryCriteria)
    {
        StringBuilder stmt = new StringBuilder();
        stmt.append("SELECT INV.AMOUNT_EXCL_VAT ");
        stmt.append(",      INV.AMOUNT_INCL_VAT ");
        stmt.append(",      INV.COINF_ID ");
        stmt.append(",      INV.CUST_ID ");
        stmt.append(",      INV.ID INV_ID ");
        stmt.append(",      INV.INV_DATE ");
        stmt.append(",      INV.NR ");
        stmt.append(",      INV.PAID ");
        stmt.append(",      INV.PAYINF_ID ");
        stmt.append(",      INV.PRINTED ");
        stmt.append(",      INP.CUPR_ID ");
        stmt.append(",      INP.DESCRIPTION ");
        stmt.append(",      INP.HOURS_WORKED ");
        stmt.append(",      INP.ID INP_ID");
        stmt.append(",      INP.POSITION ");
        stmt.append(",      INP.VAT_ID ");
        stmt.append("FROM INVOICE AS INV");
        stmt.append(",    INVOICE_POSITIONS AS INP");
        stmt.append("WHERE INV.ID = INP.INV_ID ");
          
        return _statementExecutor.executeQuery(Invoice.class, form, _selectStatement, queryCriteria);
    }

    @Override
    public void executeInsert(EJForm form, List<Invoice> newRecords)
    {
        List<EJStatementParameter> parameters = new ArrayList<EJStatementParameter>();
        int recordsProcessed = 0;
        for (Invoice record : newRecords)
        {
            // Initialise the value list
            parameters.clear();
            parameters.add(new EJStatementParameter("AMOUNT_EXCL_VAT", Double.class, record.getAmountExclVat()));
            parameters.add(new EJStatementParameter("AMOUNT_INCL_VAT", Double.class, record.getAmountInclVat()));
            parameters.add(new EJStatementParameter("COINF_ID", Integer.class, record.getCoinfId()));
            parameters.add(new EJStatementParameter("CUST_ID", Integer.class, record.getCustId()));
            parameters.add(new EJStatementParameter("ID", Integer.class, record.getId()));
            parameters.add(new EJStatementParameter("INV_DATE", Date.class, record.getInvDate()));
            parameters.add(new EJStatementParameter("INV_ID", Integer.class, record.getInvId()));
            parameters.add(new EJStatementParameter("NR", String.class, record.getNr()));
            parameters.add(new EJStatementParameter("PAID", Integer.class, record.getPaid()));
            parameters.add(new EJStatementParameter("PAYINF_ID", Integer.class, record.getPayinfId()));
            parameters.add(new EJStatementParameter("PRINTED", Integer.class, record.getPrinted()));
            EJStatementParameter[] paramArray = new EJStatementParameter[parameters.size()];
            recordsProcessed += _statementExecutor.executeInsert(form, "INVOICE", parameters.toArray(paramArray));
            record.clearInitialValues();
        }
        if (recordsProcessed != newRecords.size())
        {
            throw new EJApplicationException("Unexpected amount of records processed in insert. Expected: " + newRecords.size() + ". Inserted: "
                    + recordsProcessed);
        }
    }

    @Override
    public void executeUpdate(EJForm form, List<Invoice> updateRecords)
    {
        List<EJStatementParameter> parameters = new ArrayList<EJStatementParameter>();

        int recordsProcessed = 0;
        for (Invoice record : updateRecords)
        {
            parameters.clear();

            // First add the new values
            parameters.add(new EJStatementParameter("AMOUNT_EXCL_VAT", Double.class, record.getAmountExclVat()));
            parameters.add(new EJStatementParameter("AMOUNT_INCL_VAT", Double.class, record.getAmountInclVat()));
            parameters.add(new EJStatementParameter("COINF_ID", Integer.class, record.getCoinfId()));
            parameters.add(new EJStatementParameter("CUST_ID", Integer.class, record.getCustId()));
            parameters.add(new EJStatementParameter("ID", Integer.class, record.getId()));
            parameters.add(new EJStatementParameter("INV_DATE", Date.class, record.getInvDate()));
            parameters.add(new EJStatementParameter("INV_ID", Integer.class, record.getInvId()));
            parameters.add(new EJStatementParameter("NR", String.class, record.getNr()));
            parameters.add(new EJStatementParameter("PAID", Integer.class, record.getPaid()));
            parameters.add(new EJStatementParameter("PAYINF_ID", Integer.class, record.getPayinfId()));
            parameters.add(new EJStatementParameter("PRINTED", Integer.class, record.getPrinted()));

            EJStatementCriteria criteria = new EJStatementCriteria();
            if (record.getInitialAmountExclVat() == null)
            {
                criteria.add(EJRestrictions.isNull("AMOUNT_EXCL_VAT"));
            }
            else
            {
                criteria.add(EJRestrictions.equals("AMOUNT_EXCL_VAT", record.getInitialAmountExclVat()));
            }
            if (record.getInitialAmountInclVat() == null)
            {
                criteria.add(EJRestrictions.isNull("AMOUNT_INCL_VAT"));
            }
            else
            {
                criteria.add(EJRestrictions.equals("AMOUNT_INCL_VAT", record.getInitialAmountInclVat()));
            }
            if (record.getInitialCoinfId() == null)
            {
                criteria.add(EJRestrictions.isNull("COINF_ID"));
            }
            else
            {
                criteria.add(EJRestrictions.equals("COINF_ID", record.getInitialCoinfId()));
            }
            if (record.getInitialCustId() == null)
            {
                criteria.add(EJRestrictions.isNull("CUST_ID"));
            }
            else
            {
                criteria.add(EJRestrictions.equals("CUST_ID", record.getInitialCustId()));
            }
            if (record.getInitialId() == null)
            {
                criteria.add(EJRestrictions.isNull("ID"));
            }
            else
            {
                criteria.add(EJRestrictions.equals("ID", record.getInitialId()));
            }
            if (record.getInitialInvDate() == null)
            {
                criteria.add(EJRestrictions.isNull("INV_DATE"));
            }
            else
            {
                criteria.add(EJRestrictions.equals("INV_DATE", record.getInitialInvDate()));
            }
            if (record.getInitialInvId() == null)
            {
                criteria.add(EJRestrictions.isNull("INV_ID"));
            }
            else
            {
                criteria.add(EJRestrictions.equals("INV_ID", record.getInitialInvId()));
            }
            if (record.getInitialNr() == null)
            {
                criteria.add(EJRestrictions.isNull("NR"));
            }
            else
            {
                criteria.add(EJRestrictions.equals("NR", record.getInitialNr()));
            }
            if (record.getInitialPaid() == null)
            {
                criteria.add(EJRestrictions.isNull("PAID"));
            }
            else
            {
                criteria.add(EJRestrictions.equals("PAID", record.getInitialPaid()));
            }
            if (record.getInitialPayinfId() == null)
            {
                criteria.add(EJRestrictions.isNull("PAYINF_ID"));
            }
            else
            {
                criteria.add(EJRestrictions.equals("PAYINF_ID", record.getInitialPayinfId()));
            }
            if (record.getInitialPrinted() == null)
            {
                criteria.add(EJRestrictions.isNull("PRINTED"));
            }
            else
            {
                criteria.add(EJRestrictions.equals("PRINTED", record.getInitialPrinted()));
            }
            EJStatementParameter[] paramArray = new EJStatementParameter[parameters.size()];
            recordsProcessed += _statementExecutor.executeUpdate(form, "INVOICE", criteria, parameters.toArray(paramArray));
            record.clearInitialValues();
        }
        if (recordsProcessed != updateRecords.size())
        {
            throw new EJApplicationException("Unexpected amount of records processed in update. Expected: " + updateRecords.size() + ". Updated: "
                    + recordsProcessed);
        }
    }

    @Override
    public void executeDelete(EJForm form, List<Invoice> recordsToDelete)
    {
        ArrayList<EJStatementParameter> parameters = new ArrayList<EJStatementParameter>();

        int recordsProcessed = 0;
        for (Invoice record : recordsToDelete)
        {
            parameters.clear();

            EJStatementCriteria criteria = new EJStatementCriteria();

            if (record.getInitialAmountExclVat() == null)
            {
                criteria.add(EJRestrictions.isNull("AMOUNT_EXCL_VAT"));
            }
            else
            {
                criteria.add(EJRestrictions.equals("AMOUNT_EXCL_VAT", record.getInitialAmountExclVat()));
            }
            if (record.getInitialAmountInclVat() == null)
            {
                criteria.add(EJRestrictions.isNull("AMOUNT_INCL_VAT"));
            }
            else
            {
                criteria.add(EJRestrictions.equals("AMOUNT_INCL_VAT", record.getInitialAmountInclVat()));
            }
            if (record.getInitialCoinfId() == null)
            {
                criteria.add(EJRestrictions.isNull("COINF_ID"));
            }
            else
            {
                criteria.add(EJRestrictions.equals("COINF_ID", record.getInitialCoinfId()));
            }
            if (record.getInitialCustId() == null)
            {
                criteria.add(EJRestrictions.isNull("CUST_ID"));
            }
            else
            {
                criteria.add(EJRestrictions.equals("CUST_ID", record.getInitialCustId()));
            }
            if (record.getInitialId() == null)
            {
                criteria.add(EJRestrictions.isNull("ID"));
            }
            else
            {
                criteria.add(EJRestrictions.equals("ID", record.getInitialId()));
            }
            if (record.getInitialInvDate() == null)
            {
                criteria.add(EJRestrictions.isNull("INV_DATE"));
            }
            else
            {
                criteria.add(EJRestrictions.equals("INV_DATE", record.getInitialInvDate()));
            }
            if (record.getInitialInvId() == null)
            {
                criteria.add(EJRestrictions.isNull("INV_ID"));
            }
            else
            {
                criteria.add(EJRestrictions.equals("INV_ID", record.getInitialInvId()));
            }
            if (record.getInitialNr() == null)
            {
                criteria.add(EJRestrictions.isNull("NR"));
            }
            else
            {
                criteria.add(EJRestrictions.equals("NR", record.getInitialNr()));
            }
            if (record.getInitialPaid() == null)
            {
                criteria.add(EJRestrictions.isNull("PAID"));
            }
            else
            {
                criteria.add(EJRestrictions.equals("PAID", record.getInitialPaid()));
            }
            if (record.getInitialPayinfId() == null)
            {
                criteria.add(EJRestrictions.isNull("PAYINF_ID"));
            }
            else
            {
                criteria.add(EJRestrictions.equals("PAYINF_ID", record.getInitialPayinfId()));
            }
            if (record.getInitialPrinted() == null)
            {
                criteria.add(EJRestrictions.isNull("PRINTED"));
            }
            else
            {
                criteria.add(EJRestrictions.equals("PRINTED", record.getInitialPrinted()));
            }
            EJStatementParameter[] paramArray = new EJStatementParameter[parameters.size()];
            recordsProcessed += _statementExecutor.executeDelete(form, "INVOICE", criteria, parameters.toArray(paramArray));
            record.clearInitialValues();
        }
        if (recordsProcessed != recordsToDelete.size())
        {
            throw new EJApplicationException("Unexpected amount of records processed in delete. Expected: " + recordsToDelete.size() + ". Deleted: "
                    + recordsProcessed);
        }
    }

}

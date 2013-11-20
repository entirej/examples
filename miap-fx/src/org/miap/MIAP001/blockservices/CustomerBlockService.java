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
package org.miap.MIAP001.blockservices;

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
import org.miap.MIAP001.blockservices.pojos.Customer;

public class CustomerBlockService implements EJBlockService<Customer>
{
    private final EJStatementExecutor _statementExecutor;
    private String                    _selectStatement = "SELECT ADDRESS_LINE1,ADDRESS_LINE2,ADDRESS_LINE3,ID,NAME,POST_CODE,TOWN FROM CUSTOMER";

    public CustomerBlockService()
    {
        _statementExecutor = new EJStatementExecutor();
    }

    @Override
    public boolean canQueryInPages()
    {
        return false;
    }

    @Override
    public List<Customer> executeQuery(EJForm form, EJQueryCriteria queryCriteria)
    {
        return _statementExecutor.executeQuery(Customer.class, form, _selectStatement, queryCriteria);
    }

    @Override
    public void executeInsert(EJForm form, List<Customer> newRecords)
    {
        List<EJStatementParameter> parameters = new ArrayList<EJStatementParameter>();
        int recordsProcessed = 0;
        for (Customer record : newRecords)
        {
            // Initialise the value list
            parameters.clear();
            parameters.add(new EJStatementParameter("ADDRESS_LINE1", String.class, record.getAddressLine1()));
            parameters.add(new EJStatementParameter("ADDRESS_LINE2", String.class, record.getAddressLine2()));
            parameters.add(new EJStatementParameter("ADDRESS_LINE3", String.class, record.getAddressLine3()));
            parameters.add(new EJStatementParameter("ID", Integer.class, record.getId()));
            parameters.add(new EJStatementParameter("NAME", String.class, record.getName()));
            parameters.add(new EJStatementParameter("POST_CODE", String.class, record.getPostCode()));
            parameters.add(new EJStatementParameter("TOWN", String.class, record.getTown()));
            EJStatementParameter[] paramArray = new EJStatementParameter[parameters.size()];
            recordsProcessed += _statementExecutor.executeInsert(form, "CUSTOMER", parameters.toArray(paramArray));
            record.clearInitialValues();
        }
        if (recordsProcessed != newRecords.size())
        {
            throw new EJApplicationException("Unexpected amount of records processed in insert. Expected: " + newRecords.size() + ". Inserted: "
                    + recordsProcessed);
        }
    }

    @Override
    public void executeUpdate(EJForm form, List<Customer> updateRecords)
    {
        List<EJStatementParameter> parameters = new ArrayList<EJStatementParameter>();

        int recordsProcessed = 0;
        for (Customer record : updateRecords)
        {
            parameters.clear();

            // First add the new values
            parameters.add(new EJStatementParameter("ADDRESS_LINE1", String.class, record.getAddressLine1()));
            parameters.add(new EJStatementParameter("ADDRESS_LINE2", String.class, record.getAddressLine2()));
            parameters.add(new EJStatementParameter("ADDRESS_LINE3", String.class, record.getAddressLine3()));
            parameters.add(new EJStatementParameter("ID", Integer.class, record.getId()));
            parameters.add(new EJStatementParameter("NAME", String.class, record.getName()));
            parameters.add(new EJStatementParameter("POST_CODE", String.class, record.getPostCode()));
            parameters.add(new EJStatementParameter("TOWN", String.class, record.getTown()));

            EJStatementCriteria criteria = new EJStatementCriteria();
            if (record.getInitialAddressLine1() == null)
            {
                criteria.add(EJRestrictions.isNull("ADDRESS_LINE1"));
            }
            else
            {
                criteria.add(EJRestrictions.equals("ADDRESS_LINE1", record.getInitialAddressLine1()));
            }
            if (record.getInitialAddressLine2() == null)
            {
                criteria.add(EJRestrictions.isNull("ADDRESS_LINE2"));
            }
            else
            {
                criteria.add(EJRestrictions.equals("ADDRESS_LINE2", record.getInitialAddressLine2()));
            }
            if (record.getInitialAddressLine3() == null)
            {
                criteria.add(EJRestrictions.isNull("ADDRESS_LINE3"));
            }
            else
            {
                criteria.add(EJRestrictions.equals("ADDRESS_LINE3", record.getInitialAddressLine3()));
            }
            if (record.getInitialId() == null)
            {
                criteria.add(EJRestrictions.isNull("ID"));
            }
            else
            {
                criteria.add(EJRestrictions.equals("ID", record.getInitialId()));
            }
            if (record.getInitialName() == null)
            {
                criteria.add(EJRestrictions.isNull("NAME"));
            }
            else
            {
                criteria.add(EJRestrictions.equals("NAME", record.getInitialName()));
            }
            if (record.getInitialPostCode() == null)
            {
                criteria.add(EJRestrictions.isNull("POST_CODE"));
            }
            else
            {
                criteria.add(EJRestrictions.equals("POST_CODE", record.getInitialPostCode()));
            }
            if (record.getInitialTown() == null)
            {
                criteria.add(EJRestrictions.isNull("TOWN"));
            }
            else
            {
                criteria.add(EJRestrictions.equals("TOWN", record.getInitialTown()));
            }
            EJStatementParameter[] paramArray = new EJStatementParameter[parameters.size()];
            recordsProcessed += _statementExecutor.executeUpdate(form, "CUSTOMER", criteria, parameters.toArray(paramArray));
            record.clearInitialValues();
        }
        if (recordsProcessed != updateRecords.size())
        {
            throw new EJApplicationException("Unexpected amount of records processed in update. Expected: " + updateRecords.size() + ". Updated: "
                    + recordsProcessed);
        }
    }

    @Override
    public void executeDelete(EJForm form, List<Customer> recordsToDelete)
    {
        ArrayList<EJStatementParameter> parameters = new ArrayList<EJStatementParameter>();

        int recordsProcessed = 0;
        for (Customer record : recordsToDelete)
        {
            parameters.clear();

            EJStatementCriteria criteria = new EJStatementCriteria();

            if (record.getInitialAddressLine1() == null)
            {
                criteria.add(EJRestrictions.isNull("ADDRESS_LINE1"));
            }
            else
            {
                criteria.add(EJRestrictions.equals("ADDRESS_LINE1", record.getInitialAddressLine1()));
            }
            if (record.getInitialAddressLine2() == null)
            {
                criteria.add(EJRestrictions.isNull("ADDRESS_LINE2"));
            }
            else
            {
                criteria.add(EJRestrictions.equals("ADDRESS_LINE2", record.getInitialAddressLine2()));
            }
            if (record.getInitialAddressLine3() == null)
            {
                criteria.add(EJRestrictions.isNull("ADDRESS_LINE3"));
            }
            else
            {
                criteria.add(EJRestrictions.equals("ADDRESS_LINE3", record.getInitialAddressLine3()));
            }
            if (record.getInitialId() == null)
            {
                criteria.add(EJRestrictions.isNull("ID"));
            }
            else
            {
                criteria.add(EJRestrictions.equals("ID", record.getInitialId()));
            }
            if (record.getInitialName() == null)
            {
                criteria.add(EJRestrictions.isNull("NAME"));
            }
            else
            {
                criteria.add(EJRestrictions.equals("NAME", record.getInitialName()));
            }
            if (record.getInitialPostCode() == null)
            {
                criteria.add(EJRestrictions.isNull("POST_CODE"));
            }
            else
            {
                criteria.add(EJRestrictions.equals("POST_CODE", record.getInitialPostCode()));
            }
            if (record.getInitialTown() == null)
            {
                criteria.add(EJRestrictions.isNull("TOWN"));
            }
            else
            {
                criteria.add(EJRestrictions.equals("TOWN", record.getInitialTown()));
            }
            EJStatementParameter[] paramArray = new EJStatementParameter[parameters.size()];
            recordsProcessed += _statementExecutor.executeDelete(form, "CUSTOMER", criteria, parameters.toArray(paramArray));
            record.clearInitialValues();
        }
        if (recordsProcessed != recordsToDelete.size())
        {
            throw new EJApplicationException("Unexpected amount of records processed in delete. Expected: " + recordsToDelete.size() + ". Deleted: "
                    + recordsProcessed);
        }
    }

}

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
package org.miap.MIAP009.blockservices;

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
import org.miap.MIAP009.blockservices.pojos.CustomerContact;

public class CustomerContactBlockService implements EJBlockService<CustomerContact>
{
    private final EJStatementExecutor _statementExecutor;
    private String                    _selectStatement = "SELECT CONTACT_TYPE,CUST_ID,EMAIL,FIRST_NAME,ID,LAST_NAME,MOBILE,PHONE,SALUT_ID FROM CUSTOMER_CONTACT";

    public CustomerContactBlockService()
    {
        _statementExecutor = new EJStatementExecutor();
    }

    @Override
    public boolean canQueryInPages()
    {
        return false;
    }

    @Override
    public List<CustomerContact> executeQuery(EJForm form, EJQueryCriteria queryCriteria)
    {
        return _statementExecutor.executeQuery(CustomerContact.class, form, _selectStatement, queryCriteria);
    }

    @Override
    public void executeInsert(EJForm form, List<CustomerContact> newRecords)
    {
        List<EJStatementParameter> parameters = new ArrayList<EJStatementParameter>();
        int recordsProcessed = 0;
        for (CustomerContact record : newRecords)
        {
            // Initialise the value list
            parameters.clear();
            parameters.add(new EJStatementParameter("CONTACT_TYPE", Integer.class, record.getContactType()));
            parameters.add(new EJStatementParameter("CUST_ID", Integer.class, record.getCustId()));
            parameters.add(new EJStatementParameter("EMAIL", String.class, record.getEmail()));
            parameters.add(new EJStatementParameter("FIRST_NAME", String.class, record.getFirstName()));
            parameters.add(new EJStatementParameter("ID", Integer.class, record.getId()));
            parameters.add(new EJStatementParameter("LAST_NAME", String.class, record.getLastName()));
            parameters.add(new EJStatementParameter("MOBILE", String.class, record.getMobile()));
            parameters.add(new EJStatementParameter("PHONE", String.class, record.getPhone()));
            parameters.add(new EJStatementParameter("SALUT_ID", Integer.class, record.getSalutId()));
            EJStatementParameter[] paramArray = new EJStatementParameter[parameters.size()];
            recordsProcessed += _statementExecutor.executeInsert(form, "CUSTOMER_CONTACT", parameters.toArray(paramArray));
            record.clearInitialValues();
        }
        if (recordsProcessed != newRecords.size())
        {
            throw new EJApplicationException("Unexpected amount of records processed in insert. Expected: " + newRecords.size() + ". Inserted: "
                    + recordsProcessed);
        }
    }

    @Override
    public void executeUpdate(EJForm form, List<CustomerContact> updateRecords)
    {
        List<EJStatementParameter> parameters = new ArrayList<EJStatementParameter>();

        int recordsProcessed = 0;
        for (CustomerContact record : updateRecords)
        {
            parameters.clear();

            // First add the new values
            parameters.add(new EJStatementParameter("CONTACT_TYPE", Integer.class, record.getContactType()));
            parameters.add(new EJStatementParameter("CUST_ID", Integer.class, record.getCustId()));
            parameters.add(new EJStatementParameter("EMAIL", String.class, record.getEmail()));
            parameters.add(new EJStatementParameter("FIRST_NAME", String.class, record.getFirstName()));
            parameters.add(new EJStatementParameter("ID", Integer.class, record.getId()));
            parameters.add(new EJStatementParameter("LAST_NAME", String.class, record.getLastName()));
            parameters.add(new EJStatementParameter("MOBILE", String.class, record.getMobile()));
            parameters.add(new EJStatementParameter("PHONE", String.class, record.getPhone()));
            parameters.add(new EJStatementParameter("SALUT_ID", Integer.class, record.getSalutId()));

            EJStatementCriteria criteria = new EJStatementCriteria();
            if (record.getInitialContactType() == null)
            {
                criteria.add(EJRestrictions.isNull("CONTACT_TYPE"));
            }
            else
            {
                criteria.add(EJRestrictions.equals("CONTACT_TYPE", record.getInitialContactType()));
            }
            if (record.getInitialCustId() == null)
            {
                criteria.add(EJRestrictions.isNull("CUST_ID"));
            }
            else
            {
                criteria.add(EJRestrictions.equals("CUST_ID", record.getInitialCustId()));
            }
            if (record.getInitialEmail() == null)
            {
                criteria.add(EJRestrictions.isNull("EMAIL"));
            }
            else
            {
                criteria.add(EJRestrictions.equals("EMAIL", record.getInitialEmail()));
            }
            if (record.getInitialFirstName() == null)
            {
                criteria.add(EJRestrictions.isNull("FIRST_NAME"));
            }
            else
            {
                criteria.add(EJRestrictions.equals("FIRST_NAME", record.getInitialFirstName()));
            }
            if (record.getInitialId() == null)
            {
                criteria.add(EJRestrictions.isNull("ID"));
            }
            else
            {
                criteria.add(EJRestrictions.equals("ID", record.getInitialId()));
            }
            if (record.getInitialLastName() == null)
            {
                criteria.add(EJRestrictions.isNull("LAST_NAME"));
            }
            else
            {
                criteria.add(EJRestrictions.equals("LAST_NAME", record.getInitialLastName()));
            }
            if (record.getInitialMobile() == null)
            {
                criteria.add(EJRestrictions.isNull("MOBILE"));
            }
            else
            {
                criteria.add(EJRestrictions.equals("MOBILE", record.getInitialMobile()));
            }
            if (record.getInitialPhone() == null)
            {
                criteria.add(EJRestrictions.isNull("PHONE"));
            }
            else
            {
                criteria.add(EJRestrictions.equals("PHONE", record.getInitialPhone()));
            }
            if (record.getInitialSalutId() == null)
            {
                criteria.add(EJRestrictions.isNull("SALUT_ID"));
            }
            else
            {
                criteria.add(EJRestrictions.equals("SALUT_ID", record.getInitialSalutId()));
            }
            EJStatementParameter[] paramArray = new EJStatementParameter[parameters.size()];
            recordsProcessed += _statementExecutor.executeUpdate(form, "CUSTOMER_CONTACT", criteria, parameters.toArray(paramArray));
            record.clearInitialValues();
        }
        if (recordsProcessed != updateRecords.size())
        {
            throw new EJApplicationException("Unexpected amount of records processed in update. Expected: " + updateRecords.size() + ". Updated: "
                    + recordsProcessed);
        }
    }

    @Override
    public void executeDelete(EJForm form, List<CustomerContact> recordsToDelete)
    {
        ArrayList<EJStatementParameter> parameters = new ArrayList<EJStatementParameter>();

        int recordsProcessed = 0;
        for (CustomerContact record : recordsToDelete)
        {
            parameters.clear();

            EJStatementCriteria criteria = new EJStatementCriteria();

            if (record.getInitialContactType() == null)
            {
                criteria.add(EJRestrictions.isNull("CONTACT_TYPE"));
            }
            else
            {
                criteria.add(EJRestrictions.equals("CONTACT_TYPE", record.getInitialContactType()));
            }
            if (record.getInitialCustId() == null)
            {
                criteria.add(EJRestrictions.isNull("CUST_ID"));
            }
            else
            {
                criteria.add(EJRestrictions.equals("CUST_ID", record.getInitialCustId()));
            }
            if (record.getInitialEmail() == null)
            {
                criteria.add(EJRestrictions.isNull("EMAIL"));
            }
            else
            {
                criteria.add(EJRestrictions.equals("EMAIL", record.getInitialEmail()));
            }
            if (record.getInitialFirstName() == null)
            {
                criteria.add(EJRestrictions.isNull("FIRST_NAME"));
            }
            else
            {
                criteria.add(EJRestrictions.equals("FIRST_NAME", record.getInitialFirstName()));
            }
            if (record.getInitialId() == null)
            {
                criteria.add(EJRestrictions.isNull("ID"));
            }
            else
            {
                criteria.add(EJRestrictions.equals("ID", record.getInitialId()));
            }
            if (record.getInitialLastName() == null)
            {
                criteria.add(EJRestrictions.isNull("LAST_NAME"));
            }
            else
            {
                criteria.add(EJRestrictions.equals("LAST_NAME", record.getInitialLastName()));
            }
            if (record.getInitialMobile() == null)
            {
                criteria.add(EJRestrictions.isNull("MOBILE"));
            }
            else
            {
                criteria.add(EJRestrictions.equals("MOBILE", record.getInitialMobile()));
            }
            if (record.getInitialPhone() == null)
            {
                criteria.add(EJRestrictions.isNull("PHONE"));
            }
            else
            {
                criteria.add(EJRestrictions.equals("PHONE", record.getInitialPhone()));
            }
            if (record.getInitialSalutId() == null)
            {
                criteria.add(EJRestrictions.isNull("SALUT_ID"));
            }
            else
            {
                criteria.add(EJRestrictions.equals("SALUT_ID", record.getInitialSalutId()));
            }
            EJStatementParameter[] paramArray = new EJStatementParameter[parameters.size()];
            recordsProcessed += _statementExecutor.executeDelete(form, "CUSTOMER_CONTACT", criteria, parameters.toArray(paramArray));
            record.clearInitialValues();
        }
        if (recordsProcessed != recordsToDelete.size())
        {
            throw new EJApplicationException("Unexpected amount of records processed in delete. Expected: " + recordsToDelete.size() + ". Deleted: "
                    + recordsProcessed);
        }
    }

}

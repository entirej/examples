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

import java.util.ArrayList;
import java.util.List;

import org.entirej.framework.core.EJForm;
import org.entirej.framework.core.service.EJBlockService;
import org.entirej.framework.core.service.EJQueryCriteria;
import org.entirej.framework.core.service.EJRestrictions;
import org.entirej.framework.core.service.EJStatementExecutor;
import org.miap.MIAP002.blockservices.pojos.InvInvPos;
import org.miap.MIAP002.blockservices.pojos.Invoice;
import org.miap.MIAP003.blockservices.pojos.InvoicePositions;

public class InvInvPosBlockService implements EJBlockService<InvInvPos>
{
    private String                    _invSelectStatement = "SELECT AMOUNT_EXCL_VAT,AMOUNT_INCL_VAT,COINF_ID,CUST_ID,ID,INV_DATE,INV_ID,NR,PAID,PAYINF_ID,PRINTED FROM INVOICE";
    private String                    _inpSelectStatement = "SELECT CUPR_ID,DESCRIPTION,HOURS_WORKED,ID,INV_ID,POSITION,VAT_ID FROM INVOICE_POSITIONS";
    
    private final EJStatementExecutor _statementExecutor;

    public InvInvPosBlockService()
    {
        _statementExecutor = new EJStatementExecutor();
    }

    @Override
    public boolean canQueryInPages()
    {
        return false;
    }

    @Override
    public List<InvInvPos> executeQuery(EJForm form, EJQueryCriteria queryCriteria)
    {
        ArrayList<InvInvPos> results = new ArrayList<InvInvPos>();
        
        List<Invoice> invoices = _statementExecutor.executeQuery(Invoice.class, form, _invSelectStatement, queryCriteria);
        
        for (Invoice invoice : invoices)
        {
            InvInvPos pos = new InvInvPos();
            pos.setId(invoice.getId());
            pos.setAmountExclVat(invoice.getAmountExclVat());
            pos.setAmountInclVat(invoice.getAmountInclVat());
            pos.setCoinfId(invoice.getCoinfId());
            pos.setCustId(invoice.getCustId());
            pos.setInvDate(invoice.getInvDate());
            pos.setNr(invoice.getNr());
            pos.setPaid(invoice.getPaid());
            pos.setPayinfId(invoice.getPayinfId());
            pos.setPrinted(invoice.getPrinted());
            
            results.add(pos);
            
            EJQueryCriteria posQueryCrit = new EJQueryCriteria();
            posQueryCrit.add(EJRestrictions.equals("INV_ID", invoice.getId()));
            
            List<InvoicePositions> positions = _statementExecutor.executeQuery(InvoicePositions.class, form, _inpSelectStatement, posQueryCrit);
            for (InvoicePositions position : positions)
            {
                System.out.println("Get Position");
                pos = new InvInvPos();
                
                pos.setId(position.getId());
                pos.setInvId(invoice.getId());
                pos.setCuprId(position.getCuprId());
                pos.setDescription(position.getDescription());
                pos.setHoursWorked(position.getHoursWorked());
                pos.setInpId(position.getId());
                pos.setPosition(position.getPosition());
                pos.setVatId(position.getVatId());
                
                results.add(pos);
            }
        }
        
        return results;
    }

    @Override
    public void executeInsert(EJForm form, List<InvInvPos> newRecords)
    {

    }

    @Override
    public void executeUpdate(EJForm form, List<InvInvPos> updateRecords)
    {

    }

    @Override
    public void executeDelete(EJForm form, List<InvInvPos> recordsToDelete)
    {
    }

}

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
package org.miap.MIAP002.blockservices.pojos;

import java.sql.Date;

import org.entirej.framework.core.EJFieldName;
import org.entirej.framework.core.service.EJPojoProperty;

public class InvInvPos
{
    private EJPojoProperty<Integer> _id;
    private EJPojoProperty<Integer> _paid;
    private EJPojoProperty<Integer> _custId;
    private EJPojoProperty<Integer> _printed;
    private EJPojoProperty<String>  _nr;
    private EJPojoProperty<Double>  _amountInclVat;
    private EJPojoProperty<Integer> _coinfId;
    private EJPojoProperty<Date>    _invDate;
    private EJPojoProperty<Integer> _payinfId;
    private EJPojoProperty<Double>  _amountExclVat;
    private EJPojoProperty<Double>  _hoursWorked;
    private EJPojoProperty<Integer> _cuprId;
    private EJPojoProperty<Integer> _inpId;
    private EJPojoProperty<Integer> _position;
    private EJPojoProperty<Integer> _vatId;
    private EJPojoProperty<Integer> _invId;
    private EJPojoProperty<String>  _description;

    @EJFieldName("ID")
    public Integer getId()
    {
        return EJPojoProperty.getPropertyValue(_id);
    }

    @EJFieldName("ID")
    public void setId(Integer id)
    {
        _id = EJPojoProperty.setPropertyValue(_id, id);
    }

    @EJFieldName("ID")
    public Integer getInitialId()
    {
        return EJPojoProperty.getPropertyInitialValue(_id);
    }
    
    @EJFieldName("PAID")
    public Integer getPaid()
    {
        return EJPojoProperty.getPropertyValue(_paid);
    }

    @EJFieldName("PAID")
    public void setPaid(Integer paid)
    {
        _paid = EJPojoProperty.setPropertyValue(_paid, paid);
    }

    @EJFieldName("PAID")
    public Integer getInitialPaid()
    {
        return EJPojoProperty.getPropertyInitialValue(_paid);
    }

    @EJFieldName("CUST_ID")
    public Integer getCustId()
    {
        return EJPojoProperty.getPropertyValue(_custId);
    }

    @EJFieldName("CUST_ID")
    public void setCustId(Integer custId)
    {
        _custId = EJPojoProperty.setPropertyValue(_custId, custId);
    }

    @EJFieldName("CUST_ID")
    public Integer getInitialCustId()
    {
        return EJPojoProperty.getPropertyInitialValue(_custId);
    }

    @EJFieldName("PRINTED")
    public Integer getPrinted()
    {
        return EJPojoProperty.getPropertyValue(_printed);
    }

    @EJFieldName("PRINTED")
    public void setPrinted(Integer printed)
    {
        _printed = EJPojoProperty.setPropertyValue(_printed, printed);
    }

    @EJFieldName("PRINTED")
    public Integer getInitialPrinted()
    {
        return EJPojoProperty.getPropertyInitialValue(_printed);
    }

    @EJFieldName("NR")
    public String getNr()
    {
        return EJPojoProperty.getPropertyValue(_nr);
    }

    @EJFieldName("NR")
    public void setNr(String nr)
    {
        _nr = EJPojoProperty.setPropertyValue(_nr, nr);
    }

    @EJFieldName("NR")
    public String getInitialNr()
    {
        return EJPojoProperty.getPropertyInitialValue(_nr);
    }

    @EJFieldName("INV_ID")
    public Integer getInvId()
    {
        return EJPojoProperty.getPropertyValue(_invId);
    }

    @EJFieldName("INV_ID")
    public void setInvId(Integer invId)
    {
        _invId = EJPojoProperty.setPropertyValue(_invId, invId);
    }

    @EJFieldName("INV_ID")
    public Integer getInitialInvId()
    {
        return EJPojoProperty.getPropertyInitialValue(_invId);
    }



    @EJFieldName("INP_ID")
    public Integer getInpId()
    {
        return EJPojoProperty.getPropertyValue(_inpId);
    }

    @EJFieldName("INP_ID")
    public void setInpId(Integer inpId)
    {
        _inpId = EJPojoProperty.setPropertyValue(_inpId, inpId);
    }

    @EJFieldName("INP_ID")
    public Integer getInitialInpId()
    {
        return EJPojoProperty.getPropertyInitialValue(_inpId);
    }

    
    
    @EJFieldName("AMOUNT_INCL_VAT")
    public Double getAmountInclVat()
    {
        return EJPojoProperty.getPropertyValue(_amountInclVat);
    }

    @EJFieldName("AMOUNT_INCL_VAT")
    public void setAmountInclVat(Double amountInclVat)
    {
        _amountInclVat = EJPojoProperty.setPropertyValue(_amountInclVat, amountInclVat);
    }

    @EJFieldName("AMOUNT_INCL_VAT")
    public Double getInitialAmountInclVat()
    {
        return EJPojoProperty.getPropertyInitialValue(_amountInclVat);
    }

    @EJFieldName("COINF_ID")
    public Integer getCoinfId()
    {
        return EJPojoProperty.getPropertyValue(_coinfId);
    }

    @EJFieldName("COINF_ID")
    public void setCoinfId(Integer coinfId)
    {
        _coinfId = EJPojoProperty.setPropertyValue(_coinfId, coinfId);
    }

    @EJFieldName("COINF_ID")
    public Integer getInitialCoinfId()
    {
        return EJPojoProperty.getPropertyInitialValue(_coinfId);
    }

    @EJFieldName("INV_DATE")
    public Date getInvDate()
    {
        return EJPojoProperty.getPropertyValue(_invDate);
    }

    @EJFieldName("INV_DATE")
    public void setInvDate(Date invDate)
    {
        _invDate = EJPojoProperty.setPropertyValue(_invDate, invDate);
    }

    @EJFieldName("INV_DATE")
    public Date getInitialInvDate()
    {
        return EJPojoProperty.getPropertyInitialValue(_invDate);
    }
    @EJFieldName("PAYINF_ID")
    public Integer getPayinfId()
    {
        return EJPojoProperty.getPropertyValue(_payinfId);
    }

    @EJFieldName("PAYINF_ID")
    public void setPayinfId(Integer payinfId)
    {
        _payinfId = EJPojoProperty.setPropertyValue(_payinfId, payinfId);
    }

    @EJFieldName("PAYINF_ID")
    public Integer getInitialPayinfId()
    {
        return EJPojoProperty.getPropertyInitialValue(_payinfId);
    }

    @EJFieldName("AMOUNT_EXCL_VAT")
    public Double getAmountExclVat()
    {
        return EJPojoProperty.getPropertyValue(_amountExclVat);
    }

    @EJFieldName("AMOUNT_EXCL_VAT")
    public void setAmountExclVat(Double amountExclVat)
    {
        _amountExclVat = EJPojoProperty.setPropertyValue(_amountExclVat, amountExclVat);
    }

    @EJFieldName("AMOUNT_EXCL_VAT")
    public Double getInitialAmountExclVat()
    {
        return EJPojoProperty.getPropertyInitialValue(_amountExclVat);
    }
    
    @EJFieldName("HOURS_WORKED")
    public Double getHoursWorked()
    {
        return EJPojoProperty.getPropertyValue(_hoursWorked);
    }

    @EJFieldName("HOURS_WORKED")
    public void setHoursWorked(Double hoursWorked)
    {
        _hoursWorked = EJPojoProperty.setPropertyValue(_hoursWorked, hoursWorked);
    }

    @EJFieldName("HOURS_WORKED")
    public Double getInitialHoursWorked()
    {
        return EJPojoProperty.getPropertyInitialValue(_hoursWorked);
    }

    @EJFieldName("CUPR_ID")
    public Integer getCuprId()
    {
        return EJPojoProperty.getPropertyValue(_cuprId);
    }

    @EJFieldName("CUPR_ID")
    public void setCuprId(Integer cuprId)
    {
        _cuprId = EJPojoProperty.setPropertyValue(_cuprId, cuprId);
    }

    @EJFieldName("CUPR_ID")
    public Integer getInitialCuprId()
    {
        return EJPojoProperty.getPropertyInitialValue(_cuprId);
    }

    @EJFieldName("POSITION")
    public Integer getPosition()
    {
        return EJPojoProperty.getPropertyValue(_position);
    }

    @EJFieldName("POSITION")
    public void setPosition(Integer position)
    {
        _position = EJPojoProperty.setPropertyValue(_position, position);
    }

    @EJFieldName("POSITION")
    public Integer getInitialPosition()
    {
        return EJPojoProperty.getPropertyInitialValue(_position);
    }

    @EJFieldName("VAT_ID")
    public Integer getVatId()
    {
        return EJPojoProperty.getPropertyValue(_vatId);
    }

    @EJFieldName("VAT_ID")
    public void setVatId(Integer vatId)
    {
        _vatId = EJPojoProperty.setPropertyValue(_vatId, vatId);
    }

    @EJFieldName("VAT_ID")
    public Integer getInitialVatId()
    {
        return EJPojoProperty.getPropertyInitialValue(_vatId);
    }

    @EJFieldName("DESCRIPTION")
    public String getDescription()
    {
        return EJPojoProperty.getPropertyValue(_description);
    }

    @EJFieldName("DESCRIPTION")
    public void setDescription(String description)
    {
        _description = EJPojoProperty.setPropertyValue(_description, description);
    }

    @EJFieldName("DESCRIPTION")
    public String getInitialDescription()
    {
        return EJPojoProperty.getPropertyInitialValue(_description);
    }


    public void clearInitialValues()
    {
        EJPojoProperty.clearInitialValue(_id);
        EJPojoProperty.clearInitialValue(_paid);
        EJPojoProperty.clearInitialValue(_custId);
        EJPojoProperty.clearInitialValue(_printed);
        EJPojoProperty.clearInitialValue(_nr);
        EJPojoProperty.clearInitialValue(_amountInclVat);
        EJPojoProperty.clearInitialValue(_coinfId);
        EJPojoProperty.clearInitialValue(_invDate);
        EJPojoProperty.clearInitialValue(_payinfId);
        EJPojoProperty.clearInitialValue(_amountExclVat);
        EJPojoProperty.clearInitialValue(_hoursWorked);
        EJPojoProperty.clearInitialValue(_cuprId);
        EJPojoProperty.clearInitialValue(_position);
        EJPojoProperty.clearInitialValue(_vatId);
        EJPojoProperty.clearInitialValue(_invId);
        EJPojoProperty.clearInitialValue(_inpId);
        EJPojoProperty.clearInitialValue(_description);
    }

}

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
package org.miap.MIAP003.blockservices.pojos;

import org.entirej.framework.core.EJFieldName;
import org.entirej.framework.core.service.EJPojoProperty;

public class InvoicePositions
{
    private EJPojoProperty<Double>  _hoursWorked;
    private EJPojoProperty<Integer> _cuprId;
    private EJPojoProperty<Integer> _id;
    private EJPojoProperty<Integer> _position;
    private EJPojoProperty<Integer> _vatId;
    private EJPojoProperty<Integer> _invId;
    private EJPojoProperty<String>  _description;

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
        EJPojoProperty.clearInitialValue(_hoursWorked);
        EJPojoProperty.clearInitialValue(_cuprId);
        EJPojoProperty.clearInitialValue(_id);
        EJPojoProperty.clearInitialValue(_position);
        EJPojoProperty.clearInitialValue(_vatId);
        EJPojoProperty.clearInitialValue(_invId);
        EJPojoProperty.clearInitialValue(_description);
    }

}

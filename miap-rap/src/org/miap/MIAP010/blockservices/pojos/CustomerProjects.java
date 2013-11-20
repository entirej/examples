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
package org.miap.MIAP010.blockservices.pojos;

import org.entirej.framework.core.EJFieldName;
import org.entirej.framework.core.service.EJPojoProperty;

public class CustomerProjects
{
    private EJPojoProperty<String>  _name;
    private EJPojoProperty<Integer> _id;
    private EJPojoProperty<Double>  _payRate;
    private EJPojoProperty<Integer> _custId;
    private EJPojoProperty<String>  _description;

    @EJFieldName("NAME")
    public String getName()
    {
        return EJPojoProperty.getPropertyValue(_name);
    }

    @EJFieldName("NAME")
    public void setName(String name)
    {
        _name = EJPojoProperty.setPropertyValue(_name, name);
    }

    @EJFieldName("NAME")
    public String getInitialName()
    {
        return EJPojoProperty.getPropertyInitialValue(_name);
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

    @EJFieldName("PAY_RATE")
    public Double getPayRate()
    {
        return EJPojoProperty.getPropertyValue(_payRate);
    }

    @EJFieldName("PAY_RATE")
    public void setPayRate(Double payRate)
    {
        _payRate = EJPojoProperty.setPropertyValue(_payRate, payRate);
    }

    @EJFieldName("PAY_RATE")
    public Double getInitialPayRate()
    {
        return EJPojoProperty.getPropertyInitialValue(_payRate);
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
        EJPojoProperty.clearInitialValue(_name);
        EJPojoProperty.clearInitialValue(_id);
        EJPojoProperty.clearInitialValue(_payRate);
        EJPojoProperty.clearInitialValue(_custId);
        EJPojoProperty.clearInitialValue(_description);
    }

}

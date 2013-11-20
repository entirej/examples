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
package org.miap.MIAP001.blockservices.pojos;

import org.entirej.framework.core.EJFieldName;
import org.entirej.framework.core.service.EJPojoProperty;

public class Customer
{
    private EJPojoProperty<Integer> _id;
    private EJPojoProperty<String>  _addressLine1;
    private EJPojoProperty<String>  _addressLine2;
    private EJPojoProperty<String>  _name;
    private EJPojoProperty<String>  _postCode;
    private EJPojoProperty<String>  _addressLine3;
    private EJPojoProperty<String>  _town;

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

    @EJFieldName("ADDRESS_LINE1")
    public String getAddressLine1()
    {
        return EJPojoProperty.getPropertyValue(_addressLine1);
    }

    @EJFieldName("ADDRESS_LINE1")
    public void setAddressLine1(String addressLine1)
    {
        _addressLine1 = EJPojoProperty.setPropertyValue(_addressLine1, addressLine1);
    }

    @EJFieldName("ADDRESS_LINE1")
    public String getInitialAddressLine1()
    {
        return EJPojoProperty.getPropertyInitialValue(_addressLine1);
    }

    @EJFieldName("ADDRESS_LINE2")
    public String getAddressLine2()
    {
        return EJPojoProperty.getPropertyValue(_addressLine2);
    }

    @EJFieldName("ADDRESS_LINE2")
    public void setAddressLine2(String addressLine2)
    {
        _addressLine2 = EJPojoProperty.setPropertyValue(_addressLine2, addressLine2);
    }

    @EJFieldName("ADDRESS_LINE2")
    public String getInitialAddressLine2()
    {
        return EJPojoProperty.getPropertyInitialValue(_addressLine2);
    }

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

    @EJFieldName("POST_CODE")
    public String getPostCode()
    {
        return EJPojoProperty.getPropertyValue(_postCode);
    }

    @EJFieldName("POST_CODE")
    public void setPostCode(String postCode)
    {
        _postCode = EJPojoProperty.setPropertyValue(_postCode, postCode);
    }

    @EJFieldName("POST_CODE")
    public String getInitialPostCode()
    {
        return EJPojoProperty.getPropertyInitialValue(_postCode);
    }

    @EJFieldName("ADDRESS_LINE3")
    public String getAddressLine3()
    {
        return EJPojoProperty.getPropertyValue(_addressLine3);
    }

    @EJFieldName("ADDRESS_LINE3")
    public void setAddressLine3(String addressLine3)
    {
        _addressLine3 = EJPojoProperty.setPropertyValue(_addressLine3, addressLine3);
    }

    @EJFieldName("ADDRESS_LINE3")
    public String getInitialAddressLine3()
    {
        return EJPojoProperty.getPropertyInitialValue(_addressLine3);
    }

    @EJFieldName("TOWN")
    public String getTown()
    {
        return EJPojoProperty.getPropertyValue(_town);
    }

    @EJFieldName("TOWN")
    public void setTown(String town)
    {
        _town = EJPojoProperty.setPropertyValue(_town, town);
    }

    @EJFieldName("TOWN")
    public String getInitialTown()
    {
        return EJPojoProperty.getPropertyInitialValue(_town);
    }

    public void clearInitialValues()
    {
        EJPojoProperty.clearInitialValue(_id);
        EJPojoProperty.clearInitialValue(_addressLine1);
        EJPojoProperty.clearInitialValue(_addressLine2);
        EJPojoProperty.clearInitialValue(_name);
        EJPojoProperty.clearInitialValue(_postCode);
        EJPojoProperty.clearInitialValue(_addressLine3);
        EJPojoProperty.clearInitialValue(_town);
    }

}

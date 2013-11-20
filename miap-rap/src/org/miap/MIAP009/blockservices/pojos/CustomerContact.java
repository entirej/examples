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
package org.miap.MIAP009.blockservices.pojos;

import org.entirej.framework.core.EJFieldName;
import org.entirej.framework.core.service.EJPojoProperty;

public class CustomerContact
{
    private EJPojoProperty<String>  _firstName;
    private EJPojoProperty<Integer> _id;
    private EJPojoProperty<String>  _lastName;
    private EJPojoProperty<String>  _phone;
    private EJPojoProperty<String>  _email;
    private EJPojoProperty<String>  _mobile;
    private EJPojoProperty<Integer> _contactType;
    private EJPojoProperty<Integer> _custId;
    private EJPojoProperty<Integer> _salutId;

    @EJFieldName("FIRST_NAME")
    public String getFirstName()
    {
        return EJPojoProperty.getPropertyValue(_firstName);
    }

    @EJFieldName("FIRST_NAME")
    public void setFirstName(String firstName)
    {
        _firstName = EJPojoProperty.setPropertyValue(_firstName, firstName);
    }

    @EJFieldName("FIRST_NAME")
    public String getInitialFirstName()
    {
        return EJPojoProperty.getPropertyInitialValue(_firstName);
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

    @EJFieldName("LAST_NAME")
    public String getLastName()
    {
        return EJPojoProperty.getPropertyValue(_lastName);
    }

    @EJFieldName("LAST_NAME")
    public void setLastName(String lastName)
    {
        _lastName = EJPojoProperty.setPropertyValue(_lastName, lastName);
    }

    @EJFieldName("LAST_NAME")
    public String getInitialLastName()
    {
        return EJPojoProperty.getPropertyInitialValue(_lastName);
    }

    @EJFieldName("PHONE")
    public String getPhone()
    {
        return EJPojoProperty.getPropertyValue(_phone);
    }

    @EJFieldName("PHONE")
    public void setPhone(String phone)
    {
        _phone = EJPojoProperty.setPropertyValue(_phone, phone);
    }

    @EJFieldName("PHONE")
    public String getInitialPhone()
    {
        return EJPojoProperty.getPropertyInitialValue(_phone);
    }

    @EJFieldName("EMAIL")
    public String getEmail()
    {
        return EJPojoProperty.getPropertyValue(_email);
    }

    @EJFieldName("EMAIL")
    public void setEmail(String email)
    {
        _email = EJPojoProperty.setPropertyValue(_email, email);
    }

    @EJFieldName("EMAIL")
    public String getInitialEmail()
    {
        return EJPojoProperty.getPropertyInitialValue(_email);
    }

    @EJFieldName("MOBILE")
    public String getMobile()
    {
        return EJPojoProperty.getPropertyValue(_mobile);
    }

    @EJFieldName("MOBILE")
    public void setMobile(String mobile)
    {
        _mobile = EJPojoProperty.setPropertyValue(_mobile, mobile);
    }

    @EJFieldName("MOBILE")
    public String getInitialMobile()
    {
        return EJPojoProperty.getPropertyInitialValue(_mobile);
    }

    @EJFieldName("CONTACT_TYPE")
    public Integer getContactType()
    {
        return EJPojoProperty.getPropertyValue(_contactType);
    }

    @EJFieldName("CONTACT_TYPE")
    public void setContactType(Integer contactType)
    {
        _contactType = EJPojoProperty.setPropertyValue(_contactType, contactType);
    }

    @EJFieldName("CONTACT_TYPE")
    public Integer getInitialContactType()
    {
        return EJPojoProperty.getPropertyInitialValue(_contactType);
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

    @EJFieldName("SALUT_ID")
    public Integer getSalutId()
    {
        return EJPojoProperty.getPropertyValue(_salutId);
    }

    @EJFieldName("SALUT_ID")
    public void setSalutId(Integer salutId)
    {
        _salutId = EJPojoProperty.setPropertyValue(_salutId, salutId);
    }

    @EJFieldName("SALUT_ID")
    public Integer getInitialSalutId()
    {
        return EJPojoProperty.getPropertyInitialValue(_salutId);
    }

    public void clearInitialValues()
    {
        EJPojoProperty.clearInitialValue(_firstName);
        EJPojoProperty.clearInitialValue(_id);
        EJPojoProperty.clearInitialValue(_lastName);
        EJPojoProperty.clearInitialValue(_phone);
        EJPojoProperty.clearInitialValue(_email);
        EJPojoProperty.clearInitialValue(_mobile);
        EJPojoProperty.clearInitialValue(_contactType);
        EJPojoProperty.clearInitialValue(_custId);
        EJPojoProperty.clearInitialValue(_salutId);
    }

}

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
package org.miap.MIAP007.blockservices.pojos;

import org.entirej.framework.core.EJFieldName;
import org.entirej.framework.core.service.EJPojoProperty;

public class VatRates
{
    private EJPojoProperty<String>  _notes;
    private EJPojoProperty<Double>  _rate;
    private EJPojoProperty<String>  _name;
    private EJPojoProperty<Integer> _id;

    @EJFieldName("NOTES")
    public String getNotes()
    {
        return EJPojoProperty.getPropertyValue(_notes);
    }

    @EJFieldName("NOTES")
    public void setNotes(String notes)
    {
        _notes = EJPojoProperty.setPropertyValue(_notes, notes);
    }

    @EJFieldName("NOTES")
    public String getInitialNotes()
    {
        return EJPojoProperty.getPropertyInitialValue(_notes);
    }

    @EJFieldName("RATE")
    public Double getRate()
    {
        return EJPojoProperty.getPropertyValue(_rate);
    }

    @EJFieldName("RATE")
    public void setRate(Double rate)
    {
        _rate = EJPojoProperty.setPropertyValue(_rate, rate);
    }

    @EJFieldName("RATE")
    public Double getInitialRate()
    {
        return EJPojoProperty.getPropertyInitialValue(_rate);
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

    public void clearInitialValues()
    {
        EJPojoProperty.clearInitialValue(_notes);
        EJPojoProperty.clearInitialValue(_rate);
        EJPojoProperty.clearInitialValue(_name);
        EJPojoProperty.clearInitialValue(_id);
    }

}

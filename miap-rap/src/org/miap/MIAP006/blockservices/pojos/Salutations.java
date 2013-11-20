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
package org.miap.MIAP006.blockservices.pojos;

import org.entirej.framework.core.EJFieldName;
import org.entirej.framework.core.service.EJPojoProperty;

public class Salutations
{
    private EJPojoProperty<String>  _value;
    private EJPojoProperty<Integer> _id;

    @EJFieldName("VALUE")
    public String getValue()
    {
        return EJPojoProperty.getPropertyValue(_value);
    }

    @EJFieldName("VALUE")
    public void setValue(String value)
    {
        _value = EJPojoProperty.setPropertyValue(_value, value);
    }

    @EJFieldName("VALUE")
    public String getInitialValue()
    {
        return EJPojoProperty.getPropertyInitialValue(_value);
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
        EJPojoProperty.clearInitialValue(_value);
        EJPojoProperty.clearInitialValue(_id);
    }

}

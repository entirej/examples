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
package org.miap.MIAP008.blockservices.pojos;

import org.entirej.framework.core.EJFieldName;
import org.entirej.framework.core.service.EJPojoProperty;

public class ContactTypes
{
    private EJPojoProperty<String>  _type;
    private EJPojoProperty<Integer> _id;
    private EJPojoProperty<String>  _description;

    @EJFieldName("TYPE")
    public String getType()
    {
        return EJPojoProperty.getPropertyValue(_type);
    }

    @EJFieldName("TYPE")
    public void setType(String type)
    {
        _type = EJPojoProperty.setPropertyValue(_type, type);
    }

    @EJFieldName("TYPE")
    public String getInitialType()
    {
        return EJPojoProperty.getPropertyInitialValue(_type);
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
        EJPojoProperty.clearInitialValue(_type);
        EJPojoProperty.clearInitialValue(_id);
        EJPojoProperty.clearInitialValue(_description);
    }

}

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
package org.miap.MIAP005.blockservices.pojos;

import org.entirej.framework.core.EJFieldName;
import org.entirej.framework.core.service.EJPojoProperty;

public class PaymentInformation
{
    private EJPojoProperty<Integer> _id;
    private EJPojoProperty<String>  _paymentTerms;

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

    @EJFieldName("PAYMENT_TERMS")
    public String getPaymentTerms()
    {
        return EJPojoProperty.getPropertyValue(_paymentTerms);
    }

    @EJFieldName("PAYMENT_TERMS")
    public void setPaymentTerms(String paymentTerms)
    {
        _paymentTerms = EJPojoProperty.setPropertyValue(_paymentTerms, paymentTerms);
    }

    @EJFieldName("PAYMENT_TERMS")
    public String getInitialPaymentTerms()
    {
        return EJPojoProperty.getPropertyInitialValue(_paymentTerms);
    }

    public void clearInitialValues()
    {
        EJPojoProperty.clearInitialValue(_id);
        EJPojoProperty.clearInitialValue(_paymentTerms);
    }

}

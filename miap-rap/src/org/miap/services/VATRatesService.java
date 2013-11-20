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
/**
 * 
 */
package org.miap.services;

import java.util.List;

import org.entirej.framework.core.interfaces.EJFrameworkConnection;
import org.entirej.framework.core.service.EJQueryCriteria;
import org.entirej.framework.core.service.EJRestrictions;
import org.entirej.framework.core.service.EJSelectResult;
import org.entirej.framework.core.service.EJStatementExecutor;

/**
 * This class is used as a service to retrieve business functionalities related to
 * <code>VAT_RATES</code> table
 */
public class VATRatesService
{

    public final static String VAT_RATES_EXIST_STMT = "SELECT NAME FROM VAT_RATES";

    /**
     * Checks to see if there is already an entry in the "VAT_RATES" table with the given name
     * 
     * @param name
     *            The vat name to check for
     * @param id
     *            The id to check for
     * @return <code>true</code> if there is already a vat name value in "VAT_RATES" table with this
     *         vat name, otherwise <code>false</code>
     */
    public static boolean isVatRateNameExists(EJFrameworkConnection con, String name, Integer id)
    {
        if (name == null)
        {
            throw new NullPointerException("The name passed to isVatRateNameExists canot be null");
        }

        EJQueryCriteria criteria = new EJQueryCriteria();
        criteria.add(EJRestrictions.equalsIgnoreCase("NAME", name));
        if (id != null)
        {
            criteria.add(EJRestrictions.notEquals("ID", id));
        }
        EJStatementExecutor executor = new EJStatementExecutor();
        List<EJSelectResult> results = executor.executeQuery(con, VAT_RATES_EXIST_STMT, criteria);

        return !results.isEmpty();
    }
}

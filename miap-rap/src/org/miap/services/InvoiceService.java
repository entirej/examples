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
 * <code>INVOICE</code> table
 */
public class InvoiceService
{

    private final static String INVOICE_NR_EXIST_STMT = "SELECT NR FROM INVOICE";

    /**
     * Checks to see if there is already an entry in the "INVOICE" table with the given invoice
     * number
     * 
     * @param nr
     *            The invoice number to check for
     * @return <code>true</code> if there is already a invoice number in "INVOICE" table with this
     *         number, otherwise <code>false</code>
     */
    public static boolean isNrExists(EJFrameworkConnection con, String nr)
    {
        if (nr == null)
        {
            throw new NullPointerException("The nr passed to isNrExists canot be null");
        }

        EJQueryCriteria criteria = new EJQueryCriteria();
        criteria.add(EJRestrictions.equalsIgnoreCase("NR", nr));

        EJStatementExecutor executor = new EJStatementExecutor();
        List<EJSelectResult> results = executor.executeQuery(con, INVOICE_NR_EXIST_STMT, criteria);

        return results.size() > 0;
    }

}

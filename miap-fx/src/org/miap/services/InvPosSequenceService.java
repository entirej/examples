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

import org.entirej.framework.core.EJApplicationException;
import org.entirej.framework.core.interfaces.EJFrameworkConnection;
import org.entirej.framework.core.service.EJSelectResult;
import org.entirej.framework.core.service.EJStatementExecutor;

/**
 * This class is used to query and select the next value from a database
 * sequence for invoice position.
 */
public class InvPosSequenceService
{

    private static final String INV_POS_SEQUENCE_SELECT = "SELECT INV_POS_SEQUENCE.NEXTVAL AS NEXTVAL";

    /**
     * Perform a DB query to "INV_POS_SEQUENCE" sequence and select and return
     * the next <code>java.lang.Integer</code> value
     * 
     * @param connection
     * 
     * @return next </code>java.lang.Integer</code> value from
     *         "INV_POS_SEQUENCE" H2 sequence
     */
    public static int getInvPosSequence(EJFrameworkConnection connection)
    {
        EJStatementExecutor executor = new EJStatementExecutor();

        List<EJSelectResult> results = executor.executeQuery(connection, INV_POS_SEQUENCE_SELECT);

        if (results == null || results.size() == 0)
        {
            throw new EJApplicationException("Unable to select next value from the INV_POS_SEQUENCE");
        }

        Object nextVal = results.get(0).getItemValue("NEXTVAL");

        return ((Long) nextVal).intValue();
    }

}

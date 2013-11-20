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

import java.util.ArrayList;
import java.util.List;

import org.entirej.framework.core.interfaces.EJFrameworkConnection;
import org.entirej.framework.core.service.EJQueryCriteria;
import org.entirej.framework.core.service.EJRestrictions;
import org.entirej.framework.core.service.EJSelectResult;
import org.entirej.framework.core.service.EJStatementExecutor;
import org.entirej.framework.core.service.EJStatementParameter;
import org.miap.MIAP008.blockservices.pojos.ContactTypes;

/**
 * This class is used as a service to retrieve business functionalities related to
 * <code>CONTACT_TYPES</code> table
 */
public class ContactTypesService
{

    public final static String MAIN                     = "MAIN";

    public final static String CONTACT_TYPES_EXIST_STMT = "SELECT TYPE FROM CONTACT_TYPES";
    public final static String MAIN_CONTACT_TYPE_STMT   = "SELECT ID,TYPE,DESCRIPTION FROM CONTACT_TYPES";

    /**
     * Checks to see if there is already an entry in the "CONTACT_TYPES" table with the given type
     * 
     * @param name
     *            The type to check for
     * @param id
     *            The id to check for
     * @return <code>true</code> if there is already a type value in "CONTACT_TYPES" table with this
     *         type, otherwise <code>false</code>
     */
    public static boolean isContactTypeExists(EJFrameworkConnection con, String type, Integer id)
    {
        if (type == null)
        {
            throw new NullPointerException("The type passed to isContactTypeExists canot be null");
        }

        EJQueryCriteria criteria = new EJQueryCriteria();
        criteria.add(EJRestrictions.equals("TYPE", type));
        if (id != null)
        {
            criteria.add(EJRestrictions.notEquals("ID", id));
        }
        EJStatementExecutor executor = new EJStatementExecutor();
        List<EJSelectResult> results = executor.executeQuery(con, CONTACT_TYPES_EXIST_STMT, criteria);

        return !results.isEmpty();
    }

    /**
     * Query and retrieve an entry in the "CONTACT_TYPES" table that matches
     * ContactTypesService.MAIN else it will create a new record with ContactTypesService.MAIN type
     * and re-query
     * 
     * @param con
     *            An EJ database connection
     * @return <code>ContactTypes</code>
     * 
     */
    public static ContactTypes getMainContactType(EJFrameworkConnection con)
    {
        EJQueryCriteria criteria = new EJQueryCriteria();
        criteria.add(EJRestrictions.equalsIgnoreCase("TYPE", ContactTypesService.MAIN));

        EJStatementExecutor executor = new EJStatementExecutor();
        List<ContactTypes> results = executor.executeQuery(ContactTypes.class, con, MAIN_CONTACT_TYPE_STMT, criteria);

        // if not found create one
        if (results.isEmpty())
        {
            createMainContactType(con);
            results = executor.executeQuery(ContactTypes.class, con, MAIN_CONTACT_TYPE_STMT, criteria);

        }
        return results.get(0);
    }

    /**
     * Creates a new contact type with the type ContactTypesService.MAIN in the database
     * 
     * @param con
     *            An EJ database connection
     */
    private static void createMainContactType(EJFrameworkConnection con)
    {
        EJStatementExecutor statementExecutor = new EJStatementExecutor();
        List<EJStatementParameter> contactTypeParams = new ArrayList<EJStatementParameter>();
        contactTypeParams.clear();
        contactTypeParams.add(new EJStatementParameter("ID", Integer.class, PKSequenceService.getPKSequence(con)));
        contactTypeParams.add(new EJStatementParameter("TYPE", String.class, ContactTypesService.MAIN));
        contactTypeParams.add(new EJStatementParameter("DESCRIPTION", String.class, ContactTypesService.MAIN));

        statementExecutor.executeInsert(con, "CONTACT_TYPES", contactTypeParams.toArray(new EJStatementParameter[contactTypeParams.size()]));
    }
}

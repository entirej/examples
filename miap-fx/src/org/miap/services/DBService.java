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

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.entirej.framework.core.EJActionProcessorException;
import org.entirej.framework.core.EJRecord;
import org.entirej.framework.core.service.EJQueryCriteria;
import org.entirej.framework.core.service.EJRestrictions;
import org.entirej.framework.core.service.EJSelectResult;
import org.entirej.framework.core.service.EJStatementExecutor;

/**
 * This class is used as a service to validate delete record usage from
 * constraints violations
 */
public class DBService
{
    private final EJContextProvider contextProvider;

    public DBService(EJContextProvider contextProvider)
    {
        this.contextProvider = contextProvider;
    }

    /**
     * Checks and validate if a given record is clear from database constraints
     * violation and if caught throws an exception
     * 
     * @param focusedRecord
     *            - record to be deleted
     * @param tableName
     *            - database table name of the selected record
     * 
     * @throws EJActionProcessorException
     */
    public void validateDeleteRecordUsage(final EJRecord focusedRecord, final String tableName) throws EJActionProcessorException
    {
        if (focusedRecord == null)
        {
            throw new EJActionProcessorException("Focused Record Cannot be null !!!");
        }

        if (tableName == null || tableName.isEmpty())
        {
            throw new EJActionProcessorException("Table Name Cannot be null or empty !!!");
        }

        final Object conObject = contextProvider.getConnection().getConnectionObject();

        if (conObject instanceof java.sql.Connection)
        {
            // cast the ej database connection into sql
            final java.sql.Connection connection = (Connection) conObject;

            try
            {
                // get database meta information
                final DatabaseMetaData metaData = connection.getMetaData();

                // get the FK's related to a given table name
                final ResultSet exportedKeys = metaData.getExportedKeys("MIAP", "MIAP", tableName);

                Map<String, String> keyRegistry = new HashMap<String, String>();
                while (exportedKeys.next())
                {
                    String fkTable = exportedKeys.getString(7);
                    String fkField = exportedKeys.getString(8);
                    if (!fkTable.equalsIgnoreCase(tableName))
                    {
                        // place the FK tables and fields in a registry
                        keyRegistry.put(fkTable, fkField);
                    }
                }

                // use the registry values with the delete record values and
                // query the db to find any record usage
                for (String key : keyRegistry.keySet())
                {
                    EJQueryCriteria queryCriteria = new EJQueryCriteria();
                    queryCriteria.add(EJRestrictions.equals(keyRegistry.get(key), focusedRecord.getValue("ID")));
                    EJStatementExecutor executor = new EJStatementExecutor();
                    List<EJSelectResult> results = executor.executeQuery(contextProvider.getConnection(), "SELECT * FROM " + key, queryCriteria);
                    if (!results.isEmpty())
                    {
                        throw new EJActionProcessorException("Cannot Delete Selected Record.\n Record usage found in : " + key);
                    }
                }
            }
            catch (SQLException e)
            {
                e.printStackTrace();
            }
        }

    }
}

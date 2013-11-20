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
package org.entirej.db.connection;

import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLDecoder;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import org.entirej.framework.core.EJFrameworkManager;
import org.entirej.framework.core.interfaces.EJConnectionFactory;
import org.entirej.framework.core.interfaces.EJFrameworkConnection;

public class EmbeddedConnectionFactory implements EJConnectionFactory
{
    String dbPath;

    public EmbeddedConnectionFactory() throws UnsupportedEncodingException
    {
        // access to current app root
        URL r = this.getClass().getResource("/");

        String decoded = URLDecoder.decode(r.getFile(), "UTF-8");

        dbPath = decoded;
    }

    @Override
    public EJFrameworkConnection createConnection(EJFrameworkManager arg0)
    {

        try
        {

            // create embedded db in class path
            Class.forName("org.h2.Driver");

            // create a java.util.Properties file and add the db authentication
            // & schema values.
            Properties prop = new Properties();
            prop.put("user", "SA");
            prop.put("password", "");
            prop.put("schema", "miap");

            final Connection connection = DriverManager.getConnection(String.format("jdbc:h2:%sdb/miap", dbPath), prop);

            return new EJFrameworkConnection()
            {

                @Override
                public void rollback()
                {
                    try
                    {
                        connection.rollback();
                    }
                    catch (SQLException e)
                    {
                        e.printStackTrace();
                    }

                }

                @Override
                public Object getConnectionObject()
                {
                    return connection;
                }

                @Override
                public void commit()
                {
                    try
                    {
                        connection.commit();
                    }
                    catch (SQLException e)
                    {

                        e.printStackTrace();
                    }

                }

                @Override
                public void close()
                {
                    try
                    {
                        connection.close();
                    }
                    catch (SQLException e)
                    {
                        e.printStackTrace();
                    }

                }
            };
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        catch (ClassNotFoundException e)
        {
            e.printStackTrace();
        }
        return null;

    }

}

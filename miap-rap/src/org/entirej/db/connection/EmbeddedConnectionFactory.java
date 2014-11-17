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

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLDecoder;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import javax.servlet.http.HttpSession;

import org.eclipse.rap.rwt.RWT;
import org.entirej.framework.core.EJFrameworkManager;
import org.entirej.framework.core.interfaces.EJConnectionFactory;
import org.entirej.framework.core.interfaces.EJFrameworkConnection;

public class EmbeddedConnectionFactory implements EJConnectionFactory
{
    private static final boolean USE_PER_SESSION_DB = Boolean.TRUE;

    String                       dbPath;

    public EmbeddedConnectionFactory() throws UnsupportedEncodingException
    {
        // access to current application root
        URL r = this.getClass().getResource("/");

        String decoded = URLDecoder.decode(r.getFile(), "UTF-8");

        dbPath = decoded + "db/miap";
    }

    private String getDBPath()
    {
        if (USE_PER_SESSION_DB)
        {
            return getSessionDBPath();
        }
        else
        {
            return dbPath;
        }
    }

    private String getSessionDBPath()
    {
        HttpSession httpSession = RWT.getUISession().getHttpSession();
        if (httpSession != null)
        {
            String id = httpSession.getId();

            if (id != null)
            {
                id += ".h2.db";
                final File tmpDir = new File(System.getProperty("java.io.tmpdir"));
                final File sessionDB = new File(tmpDir, id);
                if (sessionDB.exists())
                {
                    return "/" + sessionDB.getAbsolutePath().replaceAll(".h2.db", "").replace("\\", "/");
                }
                try
                {
                    copyFile(sessionDB);
                }
                catch (IOException e)
                {
                    // ERROR
                    throw new IllegalStateException("Sesion DB not found.");
                }
                return "/" + sessionDB.getAbsolutePath().replaceAll(".h2.db", "").replace("\\", "/");
            }
        }

        return dbPath;
    }

    private static void copyFile(File destFile) throws IOException
    {

        InputStream in = null;
        OutputStream out = null;

        try
        {
            // read this file into InputStream
            in = EmbeddedConnectionFactory.class.getClassLoader().getResourceAsStream("db/miap.h2.db");

            out = new FileOutputStream(destFile);
            byte[] buf = new byte[1024];
            int len;
            while ((len = in.read(buf)) > 0)
            {
                out.write(buf, 0, len);
            }

        }
        finally
        {
            if (in != null)
            {
                in.close();
            }
            if (out != null)
            {
                out.close();

            }
        }
    }

    @Override
    public EJFrameworkConnection createConnection(EJFrameworkManager fwkManager)
    {
        return new FrameworkConnection(fwkManager, this);
    }

    Connection getConnection(EJFrameworkManager fwkManager)
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

            final Connection connection = DriverManager.getConnection(String.format("jdbc:h2:%s", getDBPath()), prop);

            return connection;
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

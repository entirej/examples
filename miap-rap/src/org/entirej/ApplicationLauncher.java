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
package org.entirej;

import org.eclipse.rap.rwt.application.Application;
import org.entirej.applicationframework.rwt.application.launcher.EJRWTApplicationLauncher;
import org.entirej.framework.core.EJFrameworkHelper;

public class ApplicationLauncher extends EJRWTApplicationLauncher
{

    @Override
    public void configure(Application configuration)
    {
        super.configure(configuration);
        MobileApplicationLauncher mobileLauncher = new MobileApplicationLauncher();
        mobileLauncher.configure(configuration);
    }
    
    @Override
    public void postApplicationBuild(EJFrameworkHelper frameworkHelper)
    {

    }

    @Override
    protected String getLoadingMessage()
    {
        return "Loading EntireJ Example Application (MIAP)";
    }

}

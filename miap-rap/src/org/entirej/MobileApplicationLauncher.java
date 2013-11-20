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

import org.entirej.applicationframework.rwt.application.launcher.EJRWTContext;
import org.entirej.applicationframework.rwt.application.launcher.EJRWTMobileApplicationLauncher;
import org.entirej.framework.core.EJFrameworkHelper;
import org.miap.forms.constants.F_MIAP002M;

public class MobileApplicationLauncher extends EJRWTMobileApplicationLauncher
{

    @Override
    protected String getMenuID()
    {
        return "EJ.MOBILE";
    }

    @Override
    protected String getWebPathContext()
    {
        return "ej.m";
    }

    @Override
    public void postApplicationBuild(EJFrameworkHelper frameworkHelper)
    {
        EJRWTContext.getEJRWTApplicationManager().getFrameworkManager().openForm(F_MIAP002M.ID, null, false);
    }

    @Override
    protected String getLoadingMessage()
    {
        return "Loading Mojave Invoicing Test Application (MIAP) Mobile";
    }

}

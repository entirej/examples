package org.entirej;

import org.entirej.applicationframework.rwt.application.launcher.EJRWTApplicationLauncher;
import org.entirej.framework.core.EJFrameworkHelper;
import org.entirej.login.LoginAuthenticateProvider;
import org.entirej.login.LoginPage;

public class ApplicationLauncher extends EJRWTApplicationLauncher implements LoginAuthenticateProvider
{

    @Override
    public void postApplicationBuild(EJFrameworkHelper frameworkHelper)
    {

    }

    @Override
    protected String getLoadingMessage()
    {
        return "Loading Login Screen Example";
    }

    @Override
    public void preApplicationBuild(EJFrameworkHelper frameworkHelper)
    {
        LoginPage loginPage = new LoginPage();
        loginPage.create(frameworkHelper, this);
        loginPage.open();
    }

    @Override
    public String authenticate(EJFrameworkHelper appManager, String user, String pwd)
    {
        return null;
    }
}

package org.entirej.login;

import org.entirej.framework.core.EJFrameworkHelper;

public interface LoginAuthenticateProvider
{
    public String authenticate(EJFrameworkHelper appManager, String user, String pwd);
}

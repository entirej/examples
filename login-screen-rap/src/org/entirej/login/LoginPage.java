package org.entirej.login;

import java.io.IOException;

import org.eclipse.jface.fieldassist.ControlDecoration;
import org.eclipse.jface.fieldassist.FieldDecorationRegistry;
import org.eclipse.rap.rwt.RWT;
import org.eclipse.rap.rwt.client.service.UrlLauncher;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Link;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.entirej.applicationframework.rwt.application.EJRwtImageRetriever;
import org.entirej.applicationframework.rwt.application.launcher.EJRWTApplicationLauncher;
import org.entirej.applicationframework.rwt.utils.EJRwtVisualAttributeUtils;
import org.entirej.framework.core.EJFrameworkHelper;
import org.entirej.framework.core.properties.EJCoreProperties;
import org.entirej.framework.core.properties.EJCoreVisualAttributeContainer;
import org.entirej.framework.core.properties.EJCoreVisualAttributeProperties;

public class LoginPage
{

    private static final String VA_LOGIN_HEADER    = "va_login_header";
    private static final String VA_LOGIN_BODY      = "va_login_body";
    private static final String VA_LOGIN_BUTTON    = "va_login_button";
    private static final String VA_LOGIN_ERROR_MSG = "va_login_error_msg";
    private static final String PREF_USER_NAME     = "EJ_USER_NAME";
    
    private Shell               _shell;
    private Composite           _body;
    private Composite           _parentBody;

    public void create(final EJFrameworkHelper frameworkHelper, final LoginAuthenticateProvider authenticateProvider)
    {

        EJCoreVisualAttributeContainer vaContainer = EJCoreProperties.getInstance().getVisualAttributesContainer();
        Display display = Display.getDefault();
        _shell = new Shell(display, SWT.NO_TRIM | SWT.APPLICATION_MODAL);
        GridLayout glShell = new GridLayout(1, false);
        _shell.setLayout(glShell);
        glShell.marginTop = 15;
        glShell.marginLeft = 30;
        glShell.marginRight = 30;

        Color color = EJRwtVisualAttributeUtils.INSTANCE.getBackground(vaContainer.getVisualAttributeProperties(VA_LOGIN_BODY));
        if (color == null)
        {
            color = display.getSystemColor(SWT.COLOR_WHITE);
        }

        _shell.setBackground(color);

        Label headerLabel = new Label(_shell, SWT.NONE);
        headerLabel.setImage(EJRwtImageRetriever.get("org/entirej/login/images/ej-logo.png"));
        headerLabel.setLayoutData(new GridData(SWT.LEFT, SWT.TOP, true, false));

        new Label(_shell, SWT.NONE);
        new Label(_shell, SWT.SEPARATOR | SWT.HORIZONTAL).setLayoutData(new GridData(SWT.FILL, SWT.TOP, true, false));
        new Label(_shell, SWT.NONE);

        _parentBody = new Composite(_shell, SWT.NONE);
        _parentBody.setLayout(new FillLayout());
        GridData gdParentBody = new GridData(SWT.CENTER, SWT.CENTER, false, false);
        _parentBody.setLayoutData(gdParentBody);
        createLoginBody(frameworkHelper, authenticateProvider, vaContainer, display, _parentBody);

    }

    private void createLoginBody(final EJFrameworkHelper frameworkHelper, final LoginAuthenticateProvider authenticateProvider,
            EJCoreVisualAttributeContainer vaContainer, Display display, Composite parentBody)
    {
        _body = new Composite(parentBody, SWT.NONE);
        Color color = EJRwtVisualAttributeUtils.INSTANCE.getBackground(vaContainer.getVisualAttributeProperties(VA_LOGIN_BODY));
        if (color == null)
        {
            color = display.getSystemColor(SWT.COLOR_WHITE);
        }
        _body.setBackground(color);

        GridLayout bodyLayout = new GridLayout(1, true);
        _body.setLayout(bodyLayout);

        new Label(_body, SWT.NONE);
        Label lblHeader = new Label(_body, SWT.NONE);
        lblHeader.setText("Log in to entirej");
        lblHeader.setFont(EJRwtVisualAttributeUtils.INSTANCE.getFont(vaContainer.getVisualAttributeProperties(VA_LOGIN_HEADER), lblHeader.getFont()));
        lblHeader.setLayoutData(new GridData(SWT.CENTER, SWT.FILL, false, false));

        new Label(_body, SWT.NONE);

        Composite loginBody = new Composite(_body, SWT.BORDER);
        GridLayout loginGroupLayout = new GridLayout(1, false);
        loginBody.setLayout(loginGroupLayout);
        loginGroupLayout.marginRight = loginGroupLayout.marginLeft = 75;
        loginGroupLayout.marginTop = loginGroupLayout.marginBottom = 15;

        GridData gdLoginBody = new GridData(SWT.CENTER, SWT.CENTER, true, true);
        gdLoginBody.heightHint = 220;
        gdLoginBody.widthHint = 375;
        loginBody.setLayoutData(gdLoginBody);

        final GridData gdError = new GridData(SWT.FILL, SWT.TOP, true, false);
        final GridData gdErrorHide = new GridData();
        final Label lblError = new Label(loginBody, SWT.WRAP)
        {
            @Override
            public void setText(String text)
            {
                super.setText(text);

                if (text.length() == 0)
                {
                    setLayoutData(gdErrorHide);
                }
                else
                {
                    setLayoutData(gdError);
                }
                getParent().layout();
            }
        };
        lblError.setLayoutData(gdErrorHide);
        lblError.setFont(EJRwtVisualAttributeUtils.INSTANCE.getFont(vaContainer.getVisualAttributeProperties(VA_LOGIN_ERROR_MSG), lblError.getFont()));
        lblError.setForeground(EJRwtVisualAttributeUtils.INSTANCE.getForeground(vaContainer.getVisualAttributeProperties(VA_LOGIN_ERROR_MSG)));

        final Text txtUsername = new Text(loginBody, SWT.BORDER);
        txtUsername.setToolTipText("username");
        GridData gdUsername = new GridData(SWT.FILL, SWT.CENTER, true, false);
        txtUsername.setMessage("username");
        txtUsername.setLayoutData(gdUsername);
        final ControlDecoration decoUsername = new ControlDecoration(txtUsername, SWT.TOP | SWT.LEFT);
        decoUsername.hide();
        decoUsername.setImage(getDecorationImage(FieldDecorationRegistry.DEC_ERROR));
        decoUsername.setMarginWidth(2);

        final Text txtPwd = new Text(loginBody, SWT.BORDER | SWT.PASSWORD);
        txtPwd.setToolTipText("password");
        txtPwd.setMessage("password");
        txtPwd.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
        final ControlDecoration decoPwd = new ControlDecoration(txtPwd, SWT.TOP | SWT.LEFT);
        decoPwd.hide();
        decoPwd.setImage(getDecorationImage(FieldDecorationRegistry.DEC_ERROR));
        decoPwd.setMarginWidth(2);
        String attribute = RWT.getSettingStore().getAttribute(PREF_USER_NAME);
        if (attribute != null && attribute.length() > 0)
        {
            txtUsername.setText(attribute);
            txtPwd.forceFocus();
        }

        final Button btnLogin = new Button(loginBody, SWT.PUSH);
        btnLogin.setText("Sign in");
        btnLogin.addSelectionListener(new SelectionAdapter()
        {

            private static final long serialVersionUID = 1L;

            @Override
            public void widgetSelected(SelectionEvent e)
            {
                decoUsername.hide();
                decoPwd.hide();
                lblError.setText("");
                try
                {
                    txtUsername.setEnabled(false);
                    txtPwd.setEnabled(false);
                    btnLogin.setEnabled(false);
                    String usernameStr = txtUsername.getText();
                    String passwordStr = txtPwd.getText();
                    try
                    {
                        RWT.getSettingStore().setAttribute(PREF_USER_NAME, usernameStr);
                    }
                    catch (IOException e1)
                    {
                        // ignore
                    }
                    performLogin(frameworkHelper, authenticateProvider, lblError, decoUsername, decoPwd, usernameStr, passwordStr);
                }
                finally
                {
                    if (!_body.isDisposed() && (!txtUsername.isDisposed() && !txtPwd.isDisposed() && !btnLogin.isDisposed()))
                    {
                        txtUsername.setEnabled(true);
                        txtPwd.setEnabled(true);
                        btnLogin.setEnabled(true);
                    }
                }
            }
        });
        txtPwd.addKeyListener(new KeyAdapter()
        {

            @Override
            public void keyReleased(KeyEvent arg0)
            {

                decoUsername.hide();
                decoPwd.hide();
                lblError.setText("");
                try
                {
                    txtUsername.setEnabled(false);
                    txtPwd.setEnabled(false);
                    btnLogin.setEnabled(false);
                    String usernameStr = txtUsername.getText();
                    String passwordStr = txtPwd.getText();
                    try
                    {
                        RWT.getSettingStore().setAttribute(PREF_USER_NAME, usernameStr);
                    }
                    catch (IOException e1)
                    {
                        // ignore
                    }
                    performLogin(frameworkHelper, authenticateProvider, lblError, decoUsername, decoPwd, usernameStr, passwordStr);
                }
                finally
                {
                    if (!_body.isDisposed() && (!txtUsername.isDisposed() && !txtPwd.isDisposed() && !btnLogin.isDisposed()))
                    {
                        txtUsername.setEnabled(true);
                        txtPwd.setEnabled(true);
                        btnLogin.setEnabled(true);
                    }
                }
            }
        });
        txtPwd.setData(RWT.ACTIVE_KEYS, new String[] { "ENTER" });
        EJCoreVisualAttributeProperties vaLoginButton = vaContainer.getVisualAttributeProperties(VA_LOGIN_BUTTON);
        if (vaLoginButton != null)
        {
            btnLogin.setFont((EJRwtVisualAttributeUtils.INSTANCE.getFont(vaLoginButton, btnLogin.getFont())));
        }

        new Label(loginBody, SWT.NONE);

        Link lblForgotPwd = new Link(loginBody, SWT.NONE);
        lblForgotPwd.setText("<a>Forgot my password</a>");
        lblForgotPwd.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
        lblForgotPwd.addSelectionListener(new SelectionAdapter()
        {
            @Override
            public void widgetSelected(SelectionEvent e)
            {
                decoUsername.hide();
                decoPwd.hide();
                lblError.setText("");
                forgotPassword();
            }
        });
    }

    public void open()
    {
        if (_shell != null)
        {
            _shell.layout();
            _shell.setMaximized(true);
            EJRWTApplicationLauncher.openShell(_shell.getDisplay(), _shell);
        }
    }

    private Image getDecorationImage(String image)
    {
        FieldDecorationRegistry registry = FieldDecorationRegistry.getDefault();
        return registry.getFieldDecoration(image).getImage();
    }

    private void performLogin(final EJFrameworkHelper frameworkHelper, final LoginAuthenticateProvider authenticateProvider, final Label lblError,
            final ControlDecoration decoUsername, final ControlDecoration decoPwd, String usernameStr, String passwordStr)
    {
        if (usernameStr == null || usernameStr.length() == 0)
        {
            decoUsername.setDescriptionText("Enter your username.");
            decoUsername.show();
            lblError.setText(decoUsername.getDescriptionText());
            return;
        }

        if (passwordStr == null || passwordStr.length() == 0)
        {
            decoPwd.setDescriptionText("Enter your password.");
            decoPwd.show();
            lblError.setText(decoPwd.getDescriptionText());
            return;
        }
        try
        {
            String authenticateError = authenticateProvider.authenticate(frameworkHelper, usernameStr, passwordStr);
            if (authenticateError != null)
            {
                lblError.setText(authenticateError);
                return;
            }
        }
        catch (Exception e)
        {
            frameworkHelper.handleException(e);
            lblError.setText("Internal Error occured on Authentication.");
            return;
        }
        _shell.dispose();
    }

    private void forgotPassword()
    {
        UrlLauncher launcher = RWT.getClient().getService(UrlLauncher.class);
        launcher.openURL("mailto:someone@somewhere.com");
    }

}

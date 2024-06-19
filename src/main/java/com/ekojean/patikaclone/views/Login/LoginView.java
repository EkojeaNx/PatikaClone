package com.ekojean.patikaclone.Views.Login;

import com.ekojean.patikaclone.Configuration.BaseConfig;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.login.LoginI18n;
import com.vaadin.flow.component.login.LoginOverlay;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.lumo.LumoUtility;

@PageTitle("Giriş Paneli")
@Route("login")
public class LoginView extends VerticalLayout {
    public LoginView() {

        // Form
        LoginOverlay loginOverlay = new LoginOverlay();

        // i18n ( Tasarım )
        LoginI18n i18n = LoginI18n.createDefault();

        // Header
        LoginI18n.Header i18nHeader = new LoginI18n.Header();
        i18nHeader.setTitle(BaseConfig.PROJECT_TITLE);
        i18nHeader.setDescription("Üye Giriş Paneli");
        i18n.setHeader(i18nHeader);

        // Body
        LoginI18n.Form i18nForm = i18n.getForm();
        i18nForm.setTitle("Giriş İşlemi");
        i18nForm.setUsername("Kullanıcı Adı");
        i18nForm.setPassword("Parola");
        i18nForm.setSubmit("Giriş Yap");
        i18nForm.setForgotPassword("");
        i18n.setForm(i18nForm);

        // Footer
        Paragraph footerTitle = new Paragraph("© EkojeaN 2024");
        footerTitle.addClassName(LumoUtility.TextAlignment.CENTER);
        loginOverlay.getFooter().add(footerTitle);

        // Error Message
        LoginI18n.ErrorMessage i18nErrorMessage = i18n.getErrorMessage();
        i18nErrorMessage.setUsername("Kullanıcı Adı boş bırakılamaz!");
        i18nErrorMessage.setPassword("Parola boş bırakılamaz!");
        i18n.setErrorMessage(i18nErrorMessage);

        // Tasarımı Forma ekleme
        loginOverlay.setI18n(i18n);

        loginOverlay.setAction("login");
        loginOverlay.setOpened(true);

        add(
                loginOverlay
        );
    }
}

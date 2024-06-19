package com.ekojean.patikaclone;

import com.ekojean.patikaclone.Configuration.BaseConfig;
import com.vaadin.flow.component.page.AppShellConfigurator;
import com.vaadin.flow.server.PWA;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.lumo.Lumo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * The entry point of the Spring Boot application.
 *
 * Use the @PWA annotation make the application installable on phones, tablets
 * and some desktop browsers.
 *
 */
@SpringBootApplication
@Theme(value = "patikaclone", variant = Lumo.DARK)
@PWA(
        name = BaseConfig.PROJECT_TITLE,
        shortName = BaseConfig.PROJECT_SHORT_TITLE,
        iconPath = BaseConfig.PROJECT_FAVICON_URL
)
public class Application implements AppShellConfigurator {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}

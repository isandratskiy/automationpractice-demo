package core;

import io.qameta.allure.selenide.AllureSelenide;

import static com.codeborne.selenide.Configuration.*;
import static com.codeborne.selenide.logevents.SelenideLogger.addListener;
import static io.qameta.allure.selenide.LogType.BROWSER;
import static java.lang.System.getProperty;
import static java.util.logging.Level.ALL;
import static org.aeonbits.owner.ConfigFactory.setProperty;

public final class Configuration {
    private static final String SITE_LOCALE_PROP = "site.locale";

    private Configuration() {
    }

    public static void buildConfig() {
        setSiteLocaleConfig();
        setSelenideConfiguration();
    }

    private static void setSiteLocaleConfig() {
        setProperty(SITE_LOCALE_PROP, getSiteLocaleProperty());
    }

    private static String getSiteLocaleProperty() {
        return getProperty(SITE_LOCALE_PROP, "en");
    }

    private static void setSelenideConfiguration() {
        addListener("AllureSelenide", new AllureSelenide().screenshots(true).savePageSource(false).enableLogs(BROWSER, ALL));
        timeout = 20000;
        browserSize = "1920x1080";
        baseUrl = "http://automationpractice.com/index.php";
        fastSetValue = true;
        screenshots = false;
        savePageSource = false;
    }
}

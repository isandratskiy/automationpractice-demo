package driver;

import static com.codeborne.selenide.Configuration.*;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static driver.WebDriverFactory.Browser.*;
import static java.lang.System.getProperty;
import static org.apache.commons.lang3.StringUtils.*;

public class WebDriverFactory {
    private static final String BROWSER_PROPERTY = "browser";

    public static void createDriverInstance() {
        switch (getBrowserProperty()) {
            case "chrome.remote":
                REMOTE_CHROME.start();
                break;
            case "firefox.remote":
                REMOTE_FIREFOX.start();
                break;
            case "firefox.local":
                LOCAL_FIREFOX.start();
                break;
            case "chrome.local":
                LOCAL_CHROME.start();
                break;
            default: LOCAL_CHROME.start();
        }
    }

    public static void shutdownDriverInstance() {
        getWebDriver().quit();
    }

    enum Browser {
        REMOTE_CHROME {
            @Override
            void start() {
                setRemoteCapabilities();
                setRemoteInstance();
                browser = "chrome";
            }
        },
        REMOTE_FIREFOX {
            @Override
            void start() {
                setRemoteCapabilities();
                setRemoteInstance();
                browser = "firefox";
            }
        },
        LOCAL_CHROME {
            @Override
            void start() {
                browser = ChromeDriverProvider.class.getName();
            }
        },
        LOCAL_FIREFOX {
            @Override
            void start() {
                browser = FirefoxDriverProvider.class.getName();
            }
        };

        abstract void start();
    }

    private static String getBrowserProperty() {
        return getProperty(BROWSER_PROPERTY) == null
                ? EMPTY
                : getProperty(BROWSER_PROPERTY);
    }

    private static void setRemoteCapabilities() {
        browserCapabilities.acceptInsecureCerts();
        browserCapabilities.setCapability("noProxy", true);
        browserCapabilities.setCapability("enableVNC", true);
        browserCapabilities.setCapability("enableVideo", false);
    }

    private static void setRemoteInstance() {
        remote = "http://0.0.0.0:4444/wd/hub";
    }
}
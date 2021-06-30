package org.systems.utils;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.remote.AutomationName;
import io.appium.java_client.remote.MobileCapabilityType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.systems.businessLayer.implementation;
import org.testng.Assert;

import java.io.IOException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class pageFactory {
    public AppiumDriver<MobileElement> driver;
    public WebDriver driverWeb;
    public DesiredCapabilities caps;
    public int getBrowserWidth, getBrowserHeight, getWidthAfterMaximize, getHeightAfterMaximize;
    public propertyReader objPR = new propertyReader();
    public final Logger log = LogManager.getLogger(implementation.class);
    public static ThreadLocal<AppiumDriver<MobileElement>> tdriver = new ThreadLocal<AppiumDriver<MobileElement>>();
    public static ThreadLocal<WebDriver> tWdriver = new ThreadLocal<>();

    public AppiumDriver<MobileElement> launchMainDriver() {
        caps = new DesiredCapabilities();
        try {
            caps.setCapability(MobileCapabilityType.PLATFORM_NAME, objPR.getValue("platformName"));
            caps.setCapability(MobileCapabilityType.AUTOMATION_NAME, AutomationName.ANDROID_UIAUTOMATOR2);
            caps.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 1000);
            caps.setCapability("appPackage", objPR.getValue("appPackage"));
            caps.setCapability("appActivity", objPR.getValue("appActivity"));
            caps.setCapability("noReset", false);
            driver = new AppiumDriver<MobileElement>(new URL(objPR.getValue("launchUrl") + ":" + objPR.getValue("port") + objPR.getValue("completeUrl")), caps);
            driver.manage().timeouts().implicitlyWait(Integer.parseInt(objPR.getValue("implicitWait")), TimeUnit.SECONDS);
            TimeUnit.SECONDS.sleep(5);
            tdriver.set(driver);
        } catch (NullPointerException ex) {
            log.info("Error when launching mobile driver " + ex.getMessage());
            Assert.fail();
        } catch (IOException ex) {
            log.info("Error when launching mobile driver " + ex.getMessage());
            Assert.fail();
        } catch (Exception ex) {
            log.info("Error when launching mobile driver " + ex.getMessage());
            Assert.fail();
        }
        return getDriver();
    }

    public static synchronized AppiumDriver<MobileElement> getDriver() {
        return tdriver.get();
    }

    public WebDriver browserCalling() {
        try {
            if (objPR.getValue("browser").equalsIgnoreCase(commonStrings.chromeValue)) {
                System.setProperty("webdriver.chrome.driver", "chromeDriver_Win32/chromedriver.exe");
                driverWeb = new ChromeDriver();
                driverWeb.navigate().to(objPR.getValue("accessUrl"));
                getBrowserWidth = driverWeb.manage().window().getSize().getWidth();
                getBrowserHeight = driverWeb.manage().window().getSize().getHeight();
                driverWeb.manage().timeouts().pageLoadTimeout(Integer.parseInt(objPR.getValue("webPageLoad")), TimeUnit.SECONDS);
                driverWeb.manage().timeouts().implicitlyWait(Integer.parseInt(objPR.getValue("implicitWait")), TimeUnit.SECONDS);
            }
            tWdriver.set(driverWeb);
        } catch (NullPointerException ex) {
            log.info("Error when launching mobile driver " + ex.getMessage());
            Assert.fail();
        } catch (Exception ex) {
            log.info("Error when launching mobile driver " + ex.getMessage());
            Assert.fail();
        }
        return driverWeb;

    }

    public static synchronized WebDriver getWebDriver() {
        return tWdriver.get();
    }
}

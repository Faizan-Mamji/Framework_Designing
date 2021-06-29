package org.systems.businessLayer;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.qameta.allure.Step;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.systems.mainPageObjects.mobileElements.splashPage;
import org.systems.mainPageObjects.mobileElements.supportPage;
import org.systems.utils.commonFunctions;
import org.systems.utils.commonStrings;
import org.systems.utils.pageFactory;
import org.systems.utils.propertyReader;
import org.testng.Assert;

import java.util.concurrent.TimeUnit;

public class implementation extends pageFactory {
    public splashPage objSP;
    public commonFunctions objCF;
    public supportPage objSUP;
    public propertyReader objPR = new propertyReader();
    public final Logger log = LogManager.getLogger(implementation.class);

    public implementation(AppiumDriver<MobileElement> driver) {
        this.driver = driver;
        objSP = new splashPage(driver);
        objSUP = new supportPage(driver);
        objCF = new commonFunctions(driver);
        TouchAction tc = new TouchAction(driver);
    }

    @Step("Login method executes here")
    public void login_App_FullAccess(String username, String password, int OtpCode) {
        try {
            objSP = new splashPage(driver);
            log.info("Login Page Opens successfully!");
            objSP.txtUsername().setValue(username);
            log.info("Enter Username in field");
            objSP.txtPassword().setValue(password);
            log.info("Enter Password in field");
            TimeUnit.SECONDS.sleep(5);
            objSP.btnLogin().click();
            log.info("clicked on login button");
            objSP.btnAlertOTP().click();
            log.info("clicked on login button");
            objSP.txtboxOTP().click();
            log.info("OTP Field clear successfully!");
            objSP.txtboxOTP().setValue(String.valueOf(OtpCode));
            log.info("OTP value entered successfully!");
            objSP.loginBtn().click();
            log.info("Button clicked successfully!");
            TimeUnit.SECONDS.sleep(2);
            objCF.screenshotCapturing();
        } catch (Exception ex) {
            log.info("Method: login_App_FullAccess Full Access Login failed in implementation class " + ex.getMessage());
            Assert.fail(ex.getMessage());
        }
    }

    @Step("Navigate to support screen & see the transaction")
    public void test_Navigation_Scrollview() {
        try {
            objSUP.btnSupport().click();
            log.info("Support icon clicked successfully!");
            TimeUnit.SECONDS.sleep(2);
            objCF.screenshotCapturing();
            objSUP.btnHome().click();
            log.info("Home icon clicked successfully & Navigate to home page!");
            objSUP.btnHamburger().click();
            log.info("Hamburger icon click successfully!");
            objSUP.rechargeNav().click();
            log.info("Recharge navigation click successfully!");
            objCF.scrollToText(commonStrings.getAccountNavigation);
            log.info("Scroll till my account in navigation!");
            objCF.screenshotCapturing();
        } catch (Exception ex) {
            log.info("Method: navigate_SupportPage Support page navaigation issue in implementation class " + ex.getMessage());
            Assert.fail(ex.getMessage());
        }
    }

    public void login_App_QuickAccess(String accountCode, String password) {
    }
}

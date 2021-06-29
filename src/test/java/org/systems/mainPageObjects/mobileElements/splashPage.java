package org.systems.mainPageObjects.mobileElements;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.systems.pages.commonPageMobileElements;
import org.systems.utils.pageFactory;
import org.systems.utils.propertyReader;

public class splashPage extends pageFactory {
    public commonPageMobileElements objCPE;
    public propertyReader objPR = new propertyReader();

    public splashPage(AppiumDriver<MobileElement> driver) {
        this.driver = driver;
        objCPE = new commonPageMobileElements(driver);
    }

    public MobileElement txtUsername() {
        return objCPE.elem_Xpath(objPR.getValue("userName"));
    }

    public MobileElement txtPassword() {
        return objCPE.elem_Xpath(objPR.getValue("passWord"));
    }

    public MobileElement btnLogin() {
        return objCPE.elem_Uiautomator(objPR.getValue("loginButton"));
    }

    public MobileElement btnAlertOTP() {
        return objCPE.elem_Uiautomator(objPR.getValue("successAlert"));
    }

    public MobileElement txtboxOTP() {
        return objCPE.elem_Xpath(objPR.getValue("txtOTP"));
    }

    public MobileElement loginBtn() {
        return objCPE.elem_Uiautomator(objPR.getValue("loginBtn"));
    }
}

package org.systems.mainPageObjects.mobileElements;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.systems.pages.commonPageMobileElements;
import org.systems.utils.pageFactory;
import org.systems.utils.propertyReader;

public class supportPage extends pageFactory {
    public commonPageMobileElements objCPE;
    public propertyReader objPR = new propertyReader();

    public supportPage(AppiumDriver<MobileElement> driver) {
        this.driver = driver;
        objCPE = new commonPageMobileElements(driver);
    }

    public MobileElement btnSupport() {
        return objCPE.elem_Uiautomator(objPR.getValue("supportIcon"));
    }

    public MobileElement btnHome(){
        return objCPE.elem_Uiautomator(objPR.getValue("homeIcon"));
    }

    public MobileElement btnHamburger(){
        return objCPE.elem_Id(objPR.getValue("hamburger"));
    }

    public MobileElement rechargeNav(){
        return objCPE.elem_Uiautomator(objPR.getValue("rechargeNavigation"));
    }
}

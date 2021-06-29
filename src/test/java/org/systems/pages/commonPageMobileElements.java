package org.systems.pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import org.systems.utils.pageFactory;

public class commonPageMobileElements extends pageFactory {
    public commonPageMobileElements(AppiumDriver<MobileElement> driver) {
        this.driver = driver;
    }

    public MobileElement elem_Accessibility(String elem) {
        return driver.findElement(MobileBy.AccessibilityId(elem));
    }

    public MobileElement elem_Id(String elem) {
        return driver.findElement(MobileBy.id(elem));
    }

    public MobileElement elem_Xpath(String elem) {
        return driver.findElement(MobileBy.xpath(elem));
    }

    public MobileElement elem_Uiautomator(String elem) {
        return driver.findElement(MobileBy.AndroidUIAutomator(elem));
    }
}

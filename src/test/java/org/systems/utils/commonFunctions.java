package org.systems.utils;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class commonFunctions extends pageFactory {
    public commonFunctions(AppiumDriver<MobileElement> driver, WebDriver driverWebMain) {
        this.driver = driver;
        this.driverWeb = driverWebMain;
    }

    public void screenshotCapturing() {
        TakesScreenshot ts = (TakesScreenshot) driver;
        File source = ts.getScreenshotAs(OutputType.FILE);
        String dateName = new SimpleDateFormat("dd-MM-yyyy_hh_mm_ss").format(new Date());
        try {
            FileUtils.copyFile(source, new File("./Screenshot_Mobile_POTs/" + dateName + ".png"));

        } catch (IOException e) {
            e.getMessage();
        }
        System.out.println("the userStatus screenshot is taken");
    }

    public void screenshotCapturingWeb() {
        TakesScreenshot ts = (TakesScreenshot) driverWeb;
        File source = ts.getScreenshotAs(OutputType.FILE);
        String dateName = new SimpleDateFormat("dd-MM-yyyy_hh_mm_ss").format(new Date());
        try {
            FileUtils.copyFile(source, new File("./Screenshot_Web_POTs/" + dateName + ".png"));

        } catch (IOException e) {
            e.getMessage();
        }
        System.out.println("the userStatus screenshot is taken");
    }

    public MobileElement scrollToText(String text) {
        return driver.findElement(MobileBy.AndroidUIAutomator(
                "new UiScrollable(new UiSelector().scrollable(true).instance(0))." +
                        "scrollIntoView(new UiSelector().textContains(\"" + text + "\").instance(0))"));
    }
}

package org.systems.listeners;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.qameta.allure.Attachment;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.systems.utils.pageFactory;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class AllureListener implements ITestListener {

    public int getBrowserWidth, getBrowserHeight, getWidthAfterMaximize, getHeightAfterMaximize;

    private static String getTestMethodName(ITestResult iTestResult) {
        return iTestResult.getMethod().getConstructorOrMethod().getName();
    }

    // Text attachments for Allure
    @Attachment(value = "Page screenshot", type = "image/png")
    public byte[] saveScreenshotPNG(AppiumDriver<MobileElement> driver) {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }

    @Attachment(value = "Page screenshot Web", type = "image/png")
    public byte[] saveScreenshotPNGWeb(WebDriver driverweb) {
        return ((TakesScreenshot) driverweb).getScreenshotAs(OutputType.BYTES);
    }

    // Text attachments for Allure
    @Attachment(value = "{0}", type = "text/plain")
    public static String saveTextLog(String message) {
        return message;
    }

    // HTML attachments for Allure
    @Attachment(value = "{0}", type = "text/html")
    public static String attachHtml(String html) {
        return html;
    }

    @Override
    public void onStart(ITestContext iTestContext) {
        System.out.println("I am in onStart method " + iTestContext.getName());
        iTestContext.setAttribute("MobileDriver", pageFactory.getDriver());
    }

    @Override
    public void onFinish(ITestContext iTestContext) {
        System.out.println("I am in onFinish method " + iTestContext.getName());
    }

    @Override
    public void onTestStart(ITestResult iTestResult) {
        System.out.println("I am in onTestStart method " + getTestMethodName(iTestResult) + " start");
    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        System.out.println("I am in onTestSuccess method " + getTestMethodName(iTestResult) + " succeed");
        Object testClass = iTestResult.getInstance();
        AppiumDriver<MobileElement> driver = pageFactory.getDriver();
        WebDriver browserDriver = pageFactory.getWebDriver();
        getBrowserHeight = 0;
        getBrowserWidth = 0;
        getBrowserWidth = browserDriver.manage().window().getSize().getWidth();
        getBrowserHeight = browserDriver.manage().window().getSize().getHeight();
        if (getBrowserWidth < 1060) {
            if (driver instanceof AppiumDriver) {
                System.out.println("Screenshot captured for test case:" + getTestMethodName(iTestResult));
                saveScreenshotPNG(driver);
                saveTextLog(getTestMethodName(iTestResult) + " Passed and screenshot taken!");
            }
        } else if (getBrowserWidth > 1500) {
            if (browserDriver instanceof WebDriver) {
                System.out.println("Screenshot captured for test case:" + getTestMethodName(iTestResult));
                saveScreenshotPNGWeb(browserDriver);
                saveTextLog(getTestMethodName(iTestResult) + " failed and screenshot taken!");
            }
        }
    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        System.out.println("I am in onTestFailure method " + getTestMethodName(iTestResult) + " failed");
        Object testClass = iTestResult.getInstance();
        AppiumDriver<MobileElement> driver = pageFactory.getDriver();
        WebDriver browserDriver = pageFactory.getWebDriver();

        // Allure ScreenShotRobot and SaveTestLog

        if (driver instanceof AppiumDriver) {
            System.out.println("Screenshot captured for test case:" + getTestMethodName(iTestResult));
            saveScreenshotPNG(driver);
            saveTextLog(getTestMethodName(iTestResult) + " failed and screenshot taken!");
        }
        // Save a log on allure.
        if (browserDriver instanceof WebDriver) {
            System.out.println("Screenshot captured for test case:" + getTestMethodName(iTestResult));
            saveScreenshotPNGWeb(browserDriver);
            saveTextLog(getTestMethodName(iTestResult) + " failed and screenshot taken!");
        }
//        saveTextLog(getTestMethodName(iTestResult) + " failed and screenshot taken!");
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {
        System.out.println("I am in onTestSkipped method " + getTestMethodName(iTestResult) + " skipped");
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
        System.out.println("Test failed but it is in defined success ratio " + getTestMethodName(iTestResult));
    }
}
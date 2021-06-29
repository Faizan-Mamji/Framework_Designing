package org.systems.testExecution;

import io.qameta.allure.*;
import org.systems.businessLayer.implementation;
import org.systems.listeners.AllureListener;
import org.systems.utils.pageFactory;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners({AllureListener.class})
public class smokeTest extends pageFactory {
    public implementation objImp;

    @BeforeSuite
    public void launchDriver() {
        try {
            browserCalling();
            launchMainDriver();
            objImp = new implementation(driver);
        } catch (Exception ex) {
            ex.getMessage();
        }
    }


    @Test(priority = 1)
    @Epic("Login-Epic")
    @Feature("Login Feature")
    @Description("Etisalat application login")
    @Story("Login Application")
    @Severity(SeverityLevel.NORMAL)
    public void login_Full_Access() {
        try {
            objImp.login_App_FullAccess(objPR.getValue("textUserName"), objPR.getValue("textPassword"), 1234);
        } catch (Exception ex) {
            ex.getMessage();
        }
    }

    @Test(priority = 2)
    @Epic("Help&Support-Epic")
    @Description("Help & Support Implementation")
    @Story("Help & Support")
    @Severity(SeverityLevel.BLOCKER)
    public void navigate_Support() {
        try {
            System.out.println("testcase passed login");
            // objImp.test_Navigation_Scrollview();
        } catch (Exception ex) {
            ex.getMessage();
        }
    }

    @AfterSuite
    public void tearDown() {
        driver.quit();
        driverWeb.quit();
    }
}

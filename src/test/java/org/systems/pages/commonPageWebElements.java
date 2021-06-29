package org.systems.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.systems.utils.pageFactory;

public class commonPageWebElements extends pageFactory {

    public commonPageWebElements(WebDriver webDriver) {
        this.driverWeb = webDriver;
    }

    public WebElement elem_ID(String elem) {
        return driverWeb.findElement(By.id(elem));
    }

    public WebElement elem_Xpath(String elem) {
        return driverWeb.findElement(By.xpath(elem));
    }

    public WebElement elem_LinkText(String elem) {
        return driverWeb.findElement(By.linkText(elem));
    }

    public WebElement elem_ClassName(String elem) {
        return driverWeb.findElement(By.className(elem));
    }

    public WebElement elem_cssSelector(String elem) {
        return driverWeb.findElement(By.cssSelector(elem));
    }

    public WebElement elem_PartialLinktext(String elem) {
        return driverWeb.findElement(By.partialLinkText(elem));
    }
}

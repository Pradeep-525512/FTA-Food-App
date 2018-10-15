package org.d3op.pages;


import org.apache.commons.io.FileUtils;
import org.d3op.exceptions.PageInteractionException;
import org.openqa.selenium.*;

import java.io.File;
import java.io.IOException;

public class CreateMenuItemPage implements Page {

    private WebDriver driver;

    CreateMenuItemPage(WebDriver driver) {
        this.driver = driver;
    }

    public void enterName(String name) {
        driver.findElement(By.id("item-name")).sendKeys(name);
    }

    public void enterDescription(String description) {
        driver.findElement(By.id("description")).sendKeys(description);
    }

    public void enterRestaurantName(String restaurantName) {
        driver.findElement(By.id("restaurant-name")).sendKeys(restaurantName);
    }

    public MenuItemListPage clickSaveButton() {
        try {
            driver.findElement(By.id("save-btn")).click();
        } catch (Exception e) {
           takeApplicationScreenshot();
           throw new PageInteractionException("Unable to click the save button.  Unable to save the review.", e);
        }
        return new MenuItemListPage(driver);
    }





    private void takeApplicationScreenshot() {
        File src= ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(src, new File("target/screenshots"));
        } catch (IOException e1) {
            throw new RuntimeException(e1);
        }
    }

    public void quit() {
        driver.quit();
    }
}

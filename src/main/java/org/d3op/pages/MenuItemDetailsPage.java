package org.d3op.pages;

import org.d3op.models.MenuItem;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MenuItemDetailsPage implements Page {

    private WebDriver driver;

    MenuItemDetailsPage(WebDriver driver) {
        this.driver = driver;
    }


    public MenuItem readMenuItem() {
        MenuItem menuItem = new MenuItem();

        String name = driver.findElement(By.id("item-name")).getText();
        menuItem.setName(name);

        String description = driver.findElement(By.id("description")).getText();
        menuItem.setDescription(description);

        String restaurantName = driver.findElement(By.id("restaurant-name")).getText();
        menuItem.setRestaurantName(restaurantName);

        return menuItem;
    }

    public MenuItemListPage clickViewAllMenuItemsBtn() {
        driver.findElement(By.id("view-all-items-btn")).click();

        return new MenuItemListPage(driver);
    }

    public void quit() {
        driver.quit();
    }
}

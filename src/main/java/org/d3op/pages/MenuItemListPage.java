package org.d3op.pages;

import org.d3op.exceptions.PageInteractionException;
import org.d3op.models.MenuItem;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.ArrayList;
import java.util.List;

public class MenuItemListPage implements Page {

    private WebDriver driver;

    public MenuItemListPage() {

    }

    MenuItemListPage(WebDriver driver) {
        this.driver = driver;
    }

    public CreateMenuItemPage clickCreateNewLink() {
        driver.findElement(By.id("create-new-btn")).click();

        return new CreateMenuItemPage(driver);
    }

    public void launch() {
        this.driver = new FirefoxDriver();
        driver.navigate().to("http://localhost:4200/view-list");
    }

    public MenuItemDetailsPage clickMenuItem(MenuItem inputMenuItem) {
        driver.findElement(By.xpath("//td[text()='" + inputMenuItem.getDescription()
                    + "']/../td[text()='" + inputMenuItem.getRestaurantName()
                    + "']/../td/a[text()='" + inputMenuItem.getName() + "']"))
                    .click();

        return new MenuItemDetailsPage(driver);
    }

    public MenuItem readMenuItem(MenuItem inputMenuItem) {
        List<MenuItem> menuItemList = readMenuItemList();
        for (MenuItem menuItem : menuItemList) {
            if (inputMenuItem.getName().equals(menuItem.getName()) &&
                    inputMenuItem.getDescription().equals(menuItem.getDescription()) &&
                    inputMenuItem.getRestaurantName().equals(menuItem.getRestaurantName())
                    ) {
                return menuItem;
            }
        }
        return null;
    }

    public void quit() {
        driver.quit();
    }

    private List<MenuItem> readMenuItemList() {
        List<MenuItem> menuItemList = new ArrayList<>();

        List<WebElement> rows = driver.findElements(By.xpath("//table/tbody/tr"));

        for (WebElement row : rows) {
            MenuItem menuItem = new MenuItem();

            menuItem.setName(row.findElement(By.xpath("./td[1]/a")).getText());
            menuItem.setDescription(row.findElement(By.xpath("./td[2]")).getText());
            menuItem.setRestaurantName(row.findElement(By.xpath("./td[3]")).getText());

            menuItemList.add(menuItem);
        }

        return menuItemList;
    }
}

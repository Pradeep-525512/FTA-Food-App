package org.d3op.e2e;

import org.d3op.PropertiesConfig;
import org.d3op.clients.MenuItemServiceClient;
import org.d3op.models.MenuItem;
import org.d3op.pages.CreateMenuItemPage;
import org.d3op.pages.MenuItemDetailsPage;
import org.d3op.pages.MenuItemListPage;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriverException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=PropertiesConfig.class, loader=AnnotationConfigContextLoader.class)
public class MenuItemTests {

    @Autowired
    private MenuItemServiceClient menuItemServiceClient;

    @Test
    public void createMenuItemWithAllInputs() {
        //setup
        MenuItem inputMenuItem = new MenuItem();
        inputMenuItem.setName("Pizza " + UUID.randomUUID().toString());
        inputMenuItem.setDescription("Yum! " + UUID.randomUUID().toString());
        inputMenuItem.setRestaurantName("Pizza Palace " + UUID.randomUUID().toString());

        MenuItemListPage menuItemListPage = new MenuItemListPage();
        menuItemListPage.launch();

        //execute
        CreateMenuItemPage createMenuItemPage = menuItemListPage.clickCreateNewLink();

        createMenuItemPage.enterName(inputMenuItem.getName());
        createMenuItemPage.enterDescription(inputMenuItem.getDescription());
        createMenuItemPage.enterRestaurantName(inputMenuItem.getRestaurantName());

        menuItemListPage = createMenuItemPage.clickSaveButton();

        //evaluate the list page content
        MenuItem menuItemFromScreen = menuItemListPage.readMenuItem(inputMenuItem);
        assertThat(menuItemFromScreen).isEqualToComparingFieldByField(inputMenuItem);

        //tear down
        menuItemListPage.quit();

    }
    
    @Test
    public void createMenuItemWithOnlyNameAndDescription() {
    	MenuItem inputMenuItem = new MenuItem();
        inputMenuItem.setName("Pizza " + UUID.randomUUID().toString());
        inputMenuItem.setDescription("Yum! " + UUID.randomUUID().toString());
        inputMenuItem.setRestaurantName("");
        
        MenuItemListPage menuItemListPage = new MenuItemListPage();
        menuItemListPage.launch();
        
        //execute
        CreateMenuItemPage createMenuItemPage = menuItemListPage.clickCreateNewLink();

        createMenuItemPage.enterName(inputMenuItem.getName());
        createMenuItemPage.enterDescription(inputMenuItem.getDescription());
        
        menuItemListPage = createMenuItemPage.clickSaveButton();
        
        //evaluate the list page content
        MenuItem menuItemFromScreen = menuItemListPage.readMenuItem(inputMenuItem);
        assertThat(menuItemFromScreen).isNotEqualTo(inputMenuItem);
        
        //tear down
        menuItemListPage.quit();
        
    }

}



package org.d3op.clients;

import org.d3op.models.MenuItem;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;


public class MenuItemServiceClient {

    private RestTemplate restTemplate = new RestTemplate();
    private String menuItemServiceUrl;

    public MenuItemServiceClient(String menuItemServiceUrl) {
        this.menuItemServiceUrl = menuItemServiceUrl;
    }

    public ResponseEntity<MenuItem> createMenuItem(MenuItem menuItem) {
        HttpEntity<MenuItem> request = new HttpEntity<>(menuItem);
        return restTemplate
                .postForEntity(menuItemServiceUrl, request, MenuItem.class);
    }

    public ResponseEntity<MenuItem> readMenuItem(String id) {
        return restTemplate
                .getForEntity(menuItemServiceUrl + "/" + id, MenuItem.class);
    }

    public ResponseEntity<MenuItem[]> readMenuItemList() {
        return restTemplate
                .getForEntity(menuItemServiceUrl, MenuItem[].class);
    }

}

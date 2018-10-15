package org.d3op;

import org.d3op.clients.MenuItemServiceClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

@Configuration
@PropertySource("classpath:config.properties")
public class PropertiesConfig {

    @Value("${service.url}")
    private String menuItemServiceUrl;

    @Bean(name = "menuItemServiceUrl")
    public MenuItemServiceClient getMenuItemServiceClient() {
        System.err.println("url is " + menuItemServiceUrl);
        return new MenuItemServiceClient(menuItemServiceUrl);
    }

    @Bean
    public static PropertySourcesPlaceholderConfigurer
    propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

}

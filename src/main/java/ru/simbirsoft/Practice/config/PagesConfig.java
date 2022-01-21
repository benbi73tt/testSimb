package ru.simbirsoft.Practice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
@ComponentScan("ru.simbirsoft.Practice.page")
public class PagesConfig {
    @Bean("PageName")
    @Primary
    public String getPageName(){
        return "WebSite";
    }
}

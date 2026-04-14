package com.microsoft.japan.ddd.catalog.application.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.microsoft.japan.ddd.catalog.domain.service.BookDomainService;

@Configuration
public class DomainServiceConfig {

    @Bean
    public BookDomainService bookDomainService() {
        return new BookDomainService();
    }

}

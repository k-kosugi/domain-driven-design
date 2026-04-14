package com.microsoft.japan.ddd.catalog.adapter.in.web.config;

import org.springframework.context.annotation.Bean;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

/**
 * OpenAPIConfig クラスは、OpenAPI 仕様を定義するためのクラスです。
 * このクラスは、OpenAPI 仕様をカスタマイズするために使用されます。
 * 
 * @author Kenta Kosugi
 */
public class OpenAPIConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Catalog API")
                        .version("1.0.0")
                        .description("書籍カタログサービス"));
    }

}

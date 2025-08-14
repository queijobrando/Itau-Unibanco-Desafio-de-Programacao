package com.example.demo.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customAPI(){
        return new OpenAPI().info(new Info().title("Itaú Unibanco - Desafio de Programação").version("1.0.0")
                .description("Desafio proposto pelo Banco Itaú para desenvolvedor júnior, realizado por Tiago Azevedo"));

    }

}

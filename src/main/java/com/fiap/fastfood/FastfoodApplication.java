package com.fiap.fastfood;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.config.EnableMongoAuditing;

@EnableMongoAuditing
@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Fast Food FIAP", description = "Projeto de um sistema para lanchonete realizado para a Pós-Graduação de Arquitetura de Sistemas da FIAP", version = "v1"))
public class FastfoodApplication {

    public static void main(String[] args) {
        SpringApplication.run(FastfoodApplication.class, args);
    }

}

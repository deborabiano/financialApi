package management.financialAPI.infra;

import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class configuracaoDoc {

    @Bean
    public OpenAPI customOpenAPI() {
        OpenAPI info = new OpenAPI()
                .components(new Components()
                        .addSecuritySchemes("bearer-key",
                                new SecurityScheme()
                                        .type(SecurityScheme.Type.HTTP)
                                        .scheme("bearer")
                                        .bearerFormat("JWT")))
                .info(new Info()
                        .title("Financial API")
                        .description("API Rest da aplicação Financial API, contendo as funcionalidades de CRUD de usuários e de transações, além de relatórios e cadastro em massa")
                        .contact(new Contact()
                                .name("Time Backend")
                                .email("backend@financialapi"))
                        .license(new License()
                                .name("Apache 2.0")
                                .url("http://")));
        return info;
    }
}
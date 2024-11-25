package kg.bbekenov.projecttest.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAPIConfiguration {
    @Bean
    public OpenAPI openAPI() {
        Info information = new Info()
                .title("Payment Test")
                .version("1.0")
                .description("Payment test - test task for technical interview")
                .contact(new Contact().email("conovalov.elizar@gmail.com").name("Elizar"));
        return new OpenAPI().info(information);
    }
}

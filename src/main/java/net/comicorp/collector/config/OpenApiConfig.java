package net.comicorp.collector.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.parser.OpenAPIV3Parser;
import io.swagger.v3.parser.core.models.SwaggerParseResult;
import org.springdoc.core.customizers.OpenApiCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenApiCustomizer yamlOpenApiCustomizer() {
        return openApi -> {
            SwaggerParseResult parseResult = new OpenAPIV3Parser().readLocation("openapi.yml", null, null);
            OpenAPI yamlOpenApi = parseResult.getOpenAPI();

            if (yamlOpenApi == null) {
                throw new RuntimeException("Failed to parse OpenAPI YAML file: " +
                        String.join(", ", parseResult.getMessages()));
            }

            openApi.info(yamlOpenApi.getInfo());
            openApi.setServers(yamlOpenApi.getServers());
            openApi.setTags(yamlOpenApi.getTags());
            openApi.setPaths(yamlOpenApi.getPaths());
            openApi.setComponents(yamlOpenApi.getComponents());
        };
    }
}

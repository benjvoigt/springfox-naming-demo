package com.example.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.plugin.core.config.EnablePluginRegistries;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.ExpandedParameterBuilderPlugin;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.ApiKeyVehicle;
import springfox.documentation.swagger.web.SecurityConfiguration;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.*;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static java.util.Collections.singletonList;

@Configuration
@EnableSwagger2WebMvc
@EnablePluginRegistries({ExpandedParameterBuilderPlugin.class})
public class SwaggerConfig {

    public static final String API_TOKEN_SECURITY_KEY = "api_token_sec_key";

    @Bean(name = "globalOperationParameters")
    public List<Parameter> globalOperationParameters() {
        return Arrays.asList(
                new ParameterBuilder()
                        .name(RequestConstants.HEADER_AUTH_TOKEN)
                        .description("Authorization Token")
                        .parameterType("header")
                        .modelRef(new ModelRef("string"))
                        .required(false)
                        .build()
        );
    }

    @Bean
    public Docket api(List<Parameter> globalOperationParameters) {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.example"))
                .paths(PathSelectors.any())
                .paths(PathSelectors.regex("/error|/").negate())
                .build()
                .apiInfo(apiInfo())
                .produces(Collections.singleton(MediaType.APPLICATION_JSON_VALUE))
                .consumes(Collections.singleton(MediaType.APPLICATION_JSON_VALUE))
                .globalOperationParameters(globalOperationParameters)
                .useDefaultResponseMessages(false)
                .directModelSubstitute(LocalDate.class, Date.class)
                .directModelSubstitute(LocalTime.class, Time.class)
                .directModelSubstitute(LocalDateTime.class, Date.class)
                .directModelSubstitute(OffsetDateTime.class, Date.class)
                .directModelSubstitute(OffsetTime.class, Time.class)
                .directModelSubstitute(ZonedDateTime.class, Date.class)
                .directModelSubstitute(Timestamp.class, String.class)
                .securitySchemes(singletonList(apiKey()))
                .securityContexts(singletonList(securityContext()));
    }

    @Bean
    public SecurityConfiguration security() {
        return new SecurityConfiguration(
                null,
                null,
                null,
                null,
                null,
                ApiKeyVehicle.HEADER,
                RequestConstants.HEADER_API_TOKEN,
                ","
        );
    }

    @Bean
    public ApiKey apiKey() {
        return new ApiKey(API_TOKEN_SECURITY_KEY, RequestConstants.HEADER_API_TOKEN, "header");
    }

    @Bean
    public SecurityContext securityContext() {
        return SecurityContext.builder()
                .securityReferences(defaultAuth())
                .forPaths(PathSelectors.any())
                .build();
    }

    @Bean
    public List<SecurityReference> defaultAuth() {
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[]{};
        return singletonList(new SecurityReference(API_TOKEN_SECURITY_KEY, authorizationScopes));
    }

    private ApiInfo apiInfo() {

        String applicationName = "demo-service";
        return new ApiInfoBuilder()
                .title(applicationName)
                .description(applicationName + " API definitions")
                .license("")
                .licenseUrl("")
                .termsOfServiceUrl("termsOfServiceURL")
                .version("1.0")
                .contact(new Contact("Demo", "https://www.example.com/", null))
                .build();
    }

}

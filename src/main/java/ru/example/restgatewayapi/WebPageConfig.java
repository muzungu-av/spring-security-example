package ru.example.restgatewayapi;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * прикручиваем кастомные даректории к ресурсам.
 */
@Configuration
public class WebPageConfig implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/private/**")
                .addResourceLocations("classpath:/private/");
    }
}
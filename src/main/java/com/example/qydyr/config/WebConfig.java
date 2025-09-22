package com.example.qydyr.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Value("${upload.path}")
    private String uploadPath;

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new LocalDateTimeConverter());
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/css/**")
                .addResourceLocations("classpath:/static/css/");
        registry.addResourceHandler("/js/**")
                .addResourceLocations("classpath:/static/js/");
        registry.addResourceHandler("/images/**")
                .addResourceLocations("classpath:/static/images/");
        
        // Обработчик для загруженных изображений афиш
        System.out.println("=== DEBUG: WebConfig Resource Handler Setup ===");
        System.out.println("Upload path configured: " + uploadPath);
        
        // Проверяем существование папки
        java.io.File uploadDir = new java.io.File(uploadPath);
        System.out.println("Upload directory exists: " + uploadDir.exists());
        System.out.println("Upload directory is directory: " + uploadDir.isDirectory());
        System.out.println("Upload directory can read: " + uploadDir.canRead());
        
        // Проверяем папку afisha
        java.io.File afishaDir = new java.io.File(uploadPath + "/afisha");
        System.out.println("Afisha directory exists: " + afishaDir.exists());
        if (afishaDir.exists()) {
            System.out.println("Afisha directory files: " + java.util.Arrays.toString(afishaDir.list()));
        }
        
        // Используем file: с правильным экранированием для путей с пробелами
        String fileUrl = "file:" + uploadPath.replace("\\", "/") + "/";
        System.out.println("File URL: " + fileUrl);
        
        registry.addResourceHandler("/uploads/**")
                .addResourceLocations(fileUrl)
                .setCachePeriod(3600)
                .resourceChain(true)
                .addResolver(new org.springframework.web.servlet.resource.PathResourceResolver() {
                    @Override
                    protected org.springframework.core.io.Resource getResource(String resourcePath, org.springframework.core.io.Resource location) throws java.io.IOException {
                        System.out.println("=== DEBUG: Resource Request ===");
                        System.out.println("Requested resource path: " + resourcePath);
                        System.out.println("Location: " + location);
                        
                        org.springframework.core.io.Resource resource = super.getResource(resourcePath, location);
                        if (resource != null && resource.exists()) {
                            System.out.println("Resource found: " + resource.getURI());
                        } else {
                            System.out.println("Resource not found: " + resourcePath);
                        }
                        return resource;
                    }
                });
        
        System.out.println("=== DEBUG: Resource handlers configured ===");
        registry.addResourceHandler("/**")
                .addResourceLocations("classpath:/static/");
    }
}

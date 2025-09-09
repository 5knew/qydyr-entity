package com.example.qydyr.config;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class LocalDateTimeConverter implements Converter<String, LocalDateTime> {
    
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
    
    @Override
    public LocalDateTime convert(String source) {
        if (source == null || source.isEmpty()) {
            return null;
        }
        
        try {
            // Handle datetime-local input format (yyyy-MM-ddTHH:mm)
            if (source.matches("\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}")) {
                return LocalDateTime.parse(source, FORMATTER);
            }
            // Handle full datetime format (yyyy-MM-ddTHH:mm:ss)
            else if (source.matches("\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}")) {
                return LocalDateTime.parse(source, DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss"));
            }
            // Handle other common formats
            else {
                return LocalDateTime.parse(source);
            }
        } catch (Exception e) {
            throw new IllegalArgumentException("Unable to parse date: " + source, e);
        }
    }
}

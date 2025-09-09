package com.example.qydyr.config;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@RequiredArgsConstructor
public class SessionAuthenticationFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain) throws ServletException, IOException {

        // Восстанавливаем аутентификацию из сессии для веб-запросов
        if (!request.getRequestURI().startsWith("/api/")) {
            // Проверяем, есть ли аутентификация в контексте
            if (SecurityContextHolder.getContext().getAuthentication() == null) {
                // Проверяем сессию
                Object securityContext = request.getSession().getAttribute("SPRING_SECURITY_CONTEXT");
                if (securityContext != null) {
                    SecurityContextHolder.setContext((org.springframework.security.core.context.SecurityContext) securityContext);
                }
            }
        }

        filterChain.doFilter(request, response);
    }
}

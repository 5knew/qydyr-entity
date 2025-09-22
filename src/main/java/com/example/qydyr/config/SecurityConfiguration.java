package com.example.qydyr.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.SecurityFilterChain;
import javax.servlet.http.HttpServletResponse;
import com.example.qydyr.config.JwtService;
import com.example.qydyr.model.User;
import com.example.qydyr.service.UserService;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {

    private final AuthenticationProvider authenticationProvider;
    private final SessionAuthenticationFilter sessionAuthenticationFilter;
    private final JwtService jwtService;
    private final UserService userService;
    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    private static final String[] list = {
    "/api/auth/**"
    };

    @Bean
    @Order(2)
    public SecurityFilterChain webSecurityFilterChain(HttpSecurity httpSecurity) throws Exception{
        httpSecurity
                .requestMatchers().antMatchers("/**")
                .and()
                .csrf()
                .disable()
                .exceptionHandling()
                .authenticationEntryPoint((request, response, authException) -> {
                    // Для веб-запросов перенаправляем на страницу логина
                    if (request.getRequestURI().startsWith("/api/")) {
                        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
                    } else {
                        response.sendRedirect("/login");
                    }
                })
                .accessDeniedHandler((request, response, accessDeniedException) -> {
                    if (request.getRequestURI().startsWith("/api/")) {
                        response.sendError(HttpServletResponse.SC_FORBIDDEN, "Forbidden");
                    } else {
                        response.sendRedirect("/login?error=access_denied");
                    }
                })
                .and()
                .authorizeHttpRequests()
                .antMatchers("/swagger-ui/**").permitAll()
                .antMatchers("/v3/api-docs/**").permitAll()
                .antMatchers("/", "/login", "/register", "/logout", "/events", "/events/**", "/venues", "/events/new", "/venues/new").permitAll()
                .antMatchers("/favourites", "/profile", "/orders").authenticated()
                .antMatchers("/css/**", "/js/**", "/images/**", "/uploads/**").permitAll()
                .antMatchers("/admin/login").permitAll()
                .antMatchers("/admin/**").permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .loginProcessingUrl("/login")
                .defaultSuccessUrl("/", true)
                .failureUrl("/login?error=true")
                .usernameParameter("email")
                .passwordParameter("password")
                .successHandler((request, response, authentication) -> {
                    System.err.println("=== Login Success Handler ===");
                    System.err.println("Login success handler: " + authentication.getName());
                    System.err.println("Authentication authorities: " + authentication.getAuthorities());
                    System.err.println("Authentication is authenticated: " + authentication.isAuthenticated());
                    System.err.println("Principal: " + authentication.getPrincipal());
                    System.err.println("Details: " + authentication.getDetails());
                    System.err.println("Request URI: " + request.getRequestURI());
                    System.err.println("Request method: " + request.getMethod());
                    System.err.println("Session ID before: " + request.getSession().getId());
                    
                    // Устанавливаем контекст аутентификации
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                    
                    // Генерируем JWT токен для API запросов
                    try {
                        User user = userService.getUserByEmail(authentication.getName());
                        String jwtToken = jwtService.generateToken(user);
                        request.getSession().setAttribute("JWT_TOKEN", jwtToken);
                        System.err.println("Generated JWT token for user: " + user.getEmail());
                    } catch (Exception e) {
                        System.err.println("Error generating JWT token: " + e.getMessage());
                    }
                    
                    // Принудительно сохраняем сессию
                    request.getSession().setAttribute("SPRING_SECURITY_CONTEXT", SecurityContextHolder.getContext());
                    request.getSession().setMaxInactiveInterval(1800); // 30 минут
                    
                    System.err.println("After setting context - Authentication: " + SecurityContextHolder.getContext().getAuthentication());
                    System.err.println("Session ID after: " + request.getSession().getId());
                    System.err.println("Session is new: " + request.getSession().isNew());
                    System.err.println("Session max inactive interval: " + request.getSession().getMaxInactiveInterval());
                    System.err.println("=== End Login Success Handler ===");
                    response.sendRedirect("/");
                })
                .permitAll()
                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login?logout=true")
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID", "remember-me")
                .clearAuthentication(true)
                .logoutSuccessHandler((request, response, authentication) -> {
                    // Очищаем JWT токен из сессии
                    request.getSession().removeAttribute("JWT_TOKEN");
                    request.getSession().removeAttribute("SPRING_SECURITY_CONTEXT");
                    SecurityContextHolder.clearContext();
                    response.sendRedirect("/login?logout=true");
                })
                .permitAll()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                .maximumSessions(1)
                .maxSessionsPreventsLogin(false)
                .and()
                .sessionFixation().migrateSession()
                .and()
                .addFilterBefore(sessionAuthenticationFilter, org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(jwtAuthenticationFilter, org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter.class)
                .authenticationProvider(authenticationProvider);

                return httpSecurity.build();
    }
}

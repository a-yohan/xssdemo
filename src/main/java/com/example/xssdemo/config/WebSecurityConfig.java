package com.example.xssdemo.config;

import org.apache.tomcat.util.http.Rfc6265CookieProcessor;
import org.apache.tomcat.util.http.SameSiteCookies;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.embedded.tomcat.TomcatContextCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.session.HttpSessionEventPublisher;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig {

    @Value("${spring.h2.console.path}")
    private String h2consolePath;

    @Bean
    SecurityFilterChain webSecurityFilterChain(HttpSecurity http) throws Exception {
        // @formatter:off
        http
        .sessionManagement(session -> session
            .maximumSessions(1)
            .maxSessionsPreventsLogin(true)
            .expiredUrl("/login?error=10")
        )
        .csrf(csrf-> csrf.ignoringAntMatchers(h2consolePath + "/**"))
        .headers(header-> header 
            .xssProtection()
            .and()
            .contentSecurityPolicy(
                String.join("; ",
                    "default-src 'self'",
                    "script-src 'self' https://cdnjs.cloudflare.com",
                    "style-src 'self' https://cdnjs.cloudflare.com",
                    "font-src https://cdnjs.cloudflare.com"
                    )
                ).and()
            .frameOptions().sameOrigin())
        .formLogin(form -> form.loginPage("/login").failureUrl("/login?error=1").permitAll())
        .authorizeRequests().anyRequest().permitAll();
        // @formatter:on
        return http.build();
    }

    @Bean
    PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder(11);
    }

    @Bean
    public HttpSessionEventPublisher httpSessionEventPublisher() {
        return new HttpSessionEventPublisher();
    }
    
    @Bean
    TomcatContextCustomizer sameSiteCookieConfig() {
        return context -> {
            final Rfc6265CookieProcessor cookieProcessor = new Rfc6265CookieProcessor();
            cookieProcessor.setSameSiteCookies(SameSiteCookies.LAX.getValue());
            context.setCookieProcessor(cookieProcessor);
        };
    }
}

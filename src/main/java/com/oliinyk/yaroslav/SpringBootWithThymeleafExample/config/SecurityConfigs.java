package com.oliinyk.yaroslav.SpringBootWithThymeleafExample.config;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfigs {

    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity httpSecurity) throws Exception {

        httpSecurity.csrf(
                (csrf) -> csrf
                    .ignoringRequestMatchers(PathRequest.toH2Console())
            )
            .authorizeHttpRequests(
                (request) -> request
                    .requestMatchers(PathRequest.toH2Console()).permitAll()
            )
            .httpBasic(Customizer.withDefaults());

        httpSecurity.headers(
            headersConfigurer -> headersConfigurer
                .frameOptions(
                    HeadersConfigurer.FrameOptionsConfig::disable
                )
        );

        return httpSecurity.build();
    }
}

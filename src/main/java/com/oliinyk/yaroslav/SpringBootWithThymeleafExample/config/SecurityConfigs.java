package com.oliinyk.yaroslav.SpringBootWithThymeleafExample.config;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfigs {

    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity httpSecurity) throws Exception {

        httpSecurity.csrf(
                (csrf) -> csrf
                    .ignoringRequestMatchers(PathRequest.toH2Console())
                    .ignoringRequestMatchers("/saveMsg")
                    .ignoringRequestMatchers("/public/**")
                    .ignoringRequestMatchers("/api/**")
                    .ignoringRequestMatchers("/data-api/**")
                    .ignoringRequestMatchers("/monitoring/actuator/**")
            )
            .authorizeHttpRequests(
                (request) -> request
                    .requestMatchers(PathRequest.toH2Console()).permitAll()
                    .requestMatchers("/dashboard").authenticated()
                    .requestMatchers("/api/**").authenticated()
                    .requestMatchers("/data-api/**").authenticated()
                    .requestMatchers("/displayProfile").authenticated()
                    .requestMatchers("/updateProfile").authenticated()
                    .requestMatchers("/displayMessages/**").hasRole("ADMIN")
                    .requestMatchers("/closeMsg/**").hasRole("ADMIN")
                    .requestMatchers("/admin/**").hasRole("ADMIN")
                    .requestMatchers("/monitoring/actuator/**").hasRole("ADMIN")
                    .requestMatchers("/student/**").hasRole("STUDENT")
                    .requestMatchers("/", "/home").permitAll()
                    .requestMatchers("/holidays/**").permitAll()
                    .requestMatchers("/contact").permitAll()
                    .requestMatchers("/saveMsg").permitAll()
                    .requestMatchers("/courses").permitAll()
                    .requestMatchers("/about").permitAll()
                    .requestMatchers("/assets/**").permitAll()
                    .requestMatchers("/login").permitAll()
                    .requestMatchers("/logout").permitAll()
                    .requestMatchers("/public/**").permitAll()
            )
            .formLogin(
                loginConfigurer -> loginConfigurer.loginPage("/login")
                    .defaultSuccessUrl("/dashboard").failureUrl("/login?error=true").permitAll()
            )
            .logout(
                logoutConfigurer -> logoutConfigurer.logoutSuccessUrl("/login?logout=true")
                    .invalidateHttpSession(true).permitAll()
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

    @Bean
    public InMemoryUserDetailsManager userDetailsService() {

        UserDetails user = User.withDefaultPasswordEncoder()
            .username("user")
            .password("12345")
            .roles("USER")
            .build();
        UserDetails admin = User.withDefaultPasswordEncoder()
            .username("admin")
            .password("54321")
            .roles("ADMIN")
            .build();
        return new InMemoryUserDetailsManager(user, admin);
    }
}

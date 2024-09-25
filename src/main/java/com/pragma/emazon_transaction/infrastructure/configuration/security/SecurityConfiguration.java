package com.pragma.emazon_transaction.infrastructure.configuration.security;

import com.pragma.emazon_transaction.domain.utils.Constants;
import com.pragma.emazon_transaction.infrastructure.configuration.security.exceptionhandler.CustomAuthenticationEntryPoint;
import com.pragma.emazon_transaction.infrastructure.configuration.security.filter.JwtValidatorFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {

    private final JwtValidatorFilter jwtValidatorFilter;
    private final CustomAuthenticationEntryPoint customAuthenticationEntryPoint;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

        return httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(session -> session.sessionCreationPolicy(STATELESS))
                .authorizeHttpRequests(http -> {
                    http.requestMatchers(
                            Constants.OPEN_API_SWAGGER_UI_HTML,
                            Constants.OPEN_API_SWAGGER_UI,
                            Constants.OPEN_API_V3_API_DOCS
                    ).permitAll();
                    http.requestMatchers(HttpMethod.POST, Constants.ADD_SUPPLY_URL)
                            .hasRole(Constants.ROLE_WAREHOUSE_ASSISTANT);
                    http.requestMatchers(HttpMethod.POST, Constants.ADD_NEW_REGISTER_URL)
                            .hasRole(Constants.ROLE_ADMIN);
                    http.requestMatchers(HttpMethod.GET).permitAll();
                    http.anyRequest().authenticated();
                })
                .addFilterBefore(jwtValidatorFilter, BasicAuthenticationFilter.class)
                .exceptionHandling(exceptionHandling ->
                        exceptionHandling.authenticationEntryPoint(customAuthenticationEntryPoint)
                )
                .build();
    }

}

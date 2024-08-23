package jpabook.jpashop.config;

import jpabook.jpashop.domain.service.CustomOAuth2UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final CustomOAuth2UserService customOAuth2UserService;

    public SecurityConfig(CustomOAuth2UserService customOAuth2UserService) {
        this.customOAuth2UserService = customOAuth2UserService;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http
                .csrf(csrf -> csrf.disable())
                .httpBasic(httpBasic -> httpBasic.disable())
                .formLogin((auth) -> auth.disable())

                .oauth2Login((oauth) -> oauth
                        .loginPage("/login")
                        .defaultSuccessUrl("/", true)
                        .userInfoEndpoint(userInfoEndpointConfig ->
                                userInfoEndpointConfig.userService(customOAuth2UserService))
                )
                .authorizeHttpRequests((auth) -> auth
                        .requestMatchers("/css/**", "/js/**").permitAll()
                        .requestMatchers("/", "/home", "/members/new", "/items", "/login").permitAll()
                        .requestMatchers("/members", "/items/new", "/orders/**").hasRole("USER")
                        .requestMatchers("/members/**", "/items/**", "/orders/**").hasRole("ADMIN")
                        .anyRequest().authenticated()
                );


        return http.build();
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

}


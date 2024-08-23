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
                .csrf(csrf -> csrf.disable());

        http
                .httpBasic(httpBasic -> httpBasic.disable());

        http
                .formLogin((formLogin) -> formLogin.loginPage("/login/login").permitAll());

//        http
//                .formLogin((login) -> login.disable());

        http
                .oauth2Login((oauth) -> oauth
                        .userInfoEndpoint(userInfoEndpointConfig ->
                                userInfoEndpointConfig.userService(customOAuth2UserService)));

        http
                .authorizeHttpRequests((auth) -> auth
                        .requestMatchers("/css/**", "/js/**").permitAll()
                        .requestMatchers("/","/login/login", "/home", "/items/createItemForm","/items/itemList").permitAll()
                        .requestMatchers("/member/createMemberForm,/items/updateItemForm", "/order/orderForm").hasRole("USER")
                        .requestMatchers("/order/orderList", "/member/memberList").hasRole("ADMIN")
                        .anyRequest().permitAll());


        return http.build();
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

}


package jpabook.jpashop.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{

        http
                .csrf(csrf -> csrf.disable());

        http
                .httpBasic(httpBasic -> httpBasic.disable());

        http
                .formLogin((formLogin) -> formLogin.loginPage("/login")
                        .loginProcessingUrl("login").permitAll());

        http
                .authorizeHttpRequests((auth) -> auth
                        .requestMatchers("/css/**", "/js/**").permitAll()
                        .requestMatchers("/","/login", "/home", "/items/createItemForm","/items/itemList").permitAll()
                        .requestMatchers("/member/createMemberForm,/items/updateItemForm", "/order/orderForm").hasRole("USER")
                        .requestMatchers("/order/orderList", "/member/memberList").hasRole("ADMIN")
                        .anyRequest().authenticated());


        return http.build();
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

}


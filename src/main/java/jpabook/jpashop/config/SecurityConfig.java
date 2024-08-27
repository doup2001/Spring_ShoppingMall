package jpabook.jpashop.config;

import jpabook.jpashop.domain.user.service.CustomOAuth2UserService;
import jpabook.jpashop.domain.user.service.CustomUserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final CustomOAuth2UserService customOAuth2UserService;
    private final CustomUserService customUserService;

    public SecurityConfig(CustomOAuth2UserService customOAuth2UserService,CustomUserService customUserService) {
        this.customOAuth2UserService = customOAuth2UserService;
        this.customUserService = customUserService;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http
                .csrf(csrf -> csrf.disable())
                .httpBasic(httpBasic -> httpBasic.disable())

//                .formLogin((auth) -> auth.disable())

                .formLogin(formLogin -> formLogin
                        .loginPage("/login") // 커스텀 로그인 페이지 경로
                        .defaultSuccessUrl("/", true) // 로그인 성공 시 리다이렉트할 기본 페이지
                        .permitAll()
                )

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
        http
                .logout((logout) -> logout
                        .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                        .logoutSuccessUrl("/")
                        .invalidateHttpSession(true));


        return http.build();
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
}


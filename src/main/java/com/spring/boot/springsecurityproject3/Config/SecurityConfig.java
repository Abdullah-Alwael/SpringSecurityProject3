package com.spring.boot.springsecurityproject3.Config;

import com.spring.boot.springsecurityproject3.Service.MyUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final MyUserDetailsService myUserDetailsService;

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider(){
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();

        authenticationProvider.setUserDetailsService(myUserDetailsService);
        authenticationProvider.setPasswordEncoder(new BCryptPasswordEncoder());

        return authenticationProvider;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity,
                                                   AuthenticationProvider authenticationProvider) throws Exception{
        httpSecurity
                .csrf()
                .disable()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                .and()
                .authenticationProvider(daoAuthenticationProvider())
                .authorizeHttpRequests()
                .requestMatchers("api/v1/customer/register").permitAll()
                .requestMatchers("api/v1/user/**",
                        "api/v1/employee/**",
                        "api/v1/customer/list",
                        "api/v1/account/list").hasAuthority("ADMIN")
                .requestMatchers("api/v1/customer/update/**",
                        "api/v1/customer/delete/**",
                        "api/v1/account/create",
                        "api/v1/account/delete/**",
                        "api/v1/account/details/**",
                        "api/v1/account/my-accounts/**",
                        "api/v1/account/deposit-withdraw/**",
                        "api/v1/account/transfer/**").hasAuthority("CUSTOMER")
                .requestMatchers("api/v1/account/update/**",
                        "api/v1/account/activate/**",
                        "api/v1/account/block/**",
                        "api/v1/account/details/**").hasAuthority("EMPLOYEE")
                .anyRequest().authenticated()
                .and()
                .logout().logoutUrl("api/v1/user/logout")
                .deleteCookies("JSESSIONID")
                .invalidateHttpSession(true)
                .and()
                .httpBasic();

        return httpSecurity.build();

    }
}

package com.cineverse.erpc.employee.security;

import com.cineverse.erpc.employee.service.EmployeeService;
import jakarta.servlet.Filter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
public class WebSecurity {
    private final Environment env;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final EmployeeService employeeService;

    @Autowired
    public WebSecurity(Environment env,
                       BCryptPasswordEncoder bCryptPasswordEncoder,
                       EmployeeService employeeService) {
        this.env = env;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.employeeService = employeeService;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder =
                http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.userDetailsService(employeeService).passwordEncoder(bCryptPasswordEncoder);
        AuthenticationManager authenticationManager = authenticationManagerBuilder.build();

        // 로그인 이전
        http.csrf((csrf) -> csrf.disable());  // 토큰으로 처리할 예정이기 때문에 필요가 없음

        http.cors().and().authorizeHttpRequests((auth) -> auth
                .requestMatchers(new AntPathRequestMatcher("/login")).permitAll()  // /login 엔드포인트 허용
                .requestMatchers(new AntPathRequestMatcher("/employees/**")).permitAll()
                .requestMatchers(new AntPathRequestMatcher("/notice_board/**")).permitAll()
                .requestMatchers(new AntPathRequestMatcher("/notice_comment/**")).permitAll()
                .requestMatchers(new AntPathRequestMatcher("/sales_opportunity/**")).permitAll()
                .requestMatchers(new AntPathRequestMatcher("/sales_opp_note/**")).permitAll()
                .requestMatchers(new AntPathRequestMatcher("/warehouse/**")).permitAll()
                .requestMatchers(new AntPathRequestMatcher("/product/**")).permitAll()
                .requestMatchers(new AntPathRequestMatcher("/target/**")).permitAll()
                .requestMatchers(new AntPathRequestMatcher("/account/**")).permitAll()
                .requestMatchers(new AntPathRequestMatcher("/account_note/**")).permitAll()
                .requestMatchers(new AntPathRequestMatcher("/contract/**")).permitAll()
                .requestMatchers(new AntPathRequestMatcher("/quotation/**")).permitAll()
                .requestMatchers(new AntPathRequestMatcher("/quotation_note/**")).permitAll()
                .requestMatchers(new AntPathRequestMatcher("/order/**")).permitAll()
                .requestMatchers(new AntPathRequestMatcher("/order_note/**")).permitAll()
                .requestMatchers(new AntPathRequestMatcher("/collection/**")).permitAll()
                .requestMatchers(new AntPathRequestMatcher("/access/**")).permitAll()
                .requestMatchers(new AntPathRequestMatcher("/tax_invoice/**")).permitAll()
                .requestMatchers(new AntPathRequestMatcher("/sales/**")).permitAll()
                .requestMatchers(new AntPathRequestMatcher("/approval/**")).permitAll()
                .requestMatchers(new AntPathRequestMatcher("/delete/**")).permitAll()
                .requestMatchers(new AntPathRequestMatcher("/shipment/**")).permitAll()
                .requestMatchers(new AntPathRequestMatcher("/excel/**")).permitAll()
                .requestMatchers(new AntPathRequestMatcher("/issue/**")).permitAll()
                .anyRequest().authenticated()  // 나머지 요청은 인증 필요
        ).authenticationManager(authenticationManager);

        http.addFilter(getAuthenticationFilter(authenticationManager));
        http.logout(auth -> auth.logoutUrl("/logout"));

        return http.build();
    }

    private Filter getAuthenticationFilter(AuthenticationManager authenticationManager) {
        return new AuthenticationFilter(authenticationManager, employeeService, env);
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("http://localhost:5173"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "PATCH"));
        configuration.setAllowedHeaders(Arrays.asList("*"));
        configuration.setExposedHeaders(Arrays.asList("token", "userId"));  // 노출할 헤더 설정
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

}

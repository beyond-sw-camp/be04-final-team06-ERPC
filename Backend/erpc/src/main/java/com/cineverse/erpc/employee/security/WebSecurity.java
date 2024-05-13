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


@Configuration
@EnableWebSecurity
public class WebSecurity {
    private Environment env;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private EmployeeService employeeService;

    @Autowired
    public WebSecurity(Environment env,
                       BCryptPasswordEncoder bCryptPasswordEncoder,
                       EmployeeService employeeService) {
        this.env = env;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.employeeService = employeeService;
    }

    /* 인가 */
    @Bean
    protected SecurityFilterChain configure(HttpSecurity http) throws Exception {

        /* 로그인 시 추가할 내용 */
        AuthenticationManagerBuilder authenticationManagerBuilder =
                http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.userDetailsService(employeeService).passwordEncoder(bCryptPasswordEncoder);

        AuthenticationManager authenticationManager = authenticationManagerBuilder.build();

        /* 로그인 이전 */
        http.csrf((csrf) -> csrf.disable());        // 토큰으로 처리할 예정이기 때문에 필요가 없음

        http.authorizeHttpRequests((auth) -> auth
                        .requestMatchers(new AntPathRequestMatcher("/employees/**")).permitAll()
                        .requestMatchers(new AntPathRequestMatcher("/notice_board/**")).permitAll()
                        .requestMatchers(new AntPathRequestMatcher("/notice_comment/**")).permitAll()
                        .requestMatchers(new AntPathRequestMatcher("/sales_opportunity/**")).permitAll()
                        .requestMatchers(new AntPathRequestMatcher("/sales_opp_note/**")).permitAll()
                        .requestMatchers(new AntPathRequestMatcher("/warehouse/**")).permitAll()
                        .requestMatchers(new AntPathRequestMatcher("/product/**")).permitAll()
                        .requestMatchers(new AntPathRequestMatcher("/account/**")).permitAll()
                        .requestMatchers(new AntPathRequestMatcher("/account_note/**")).permitAll()
                        .requestMatchers(new AntPathRequestMatcher("/contract/**")).permitAll()
                        .requestMatchers(new AntPathRequestMatcher("/quotation/**")).permitAll()
                        .requestMatchers(new AntPathRequestMatcher("/quotation_note/**")).permitAll()
                )

                .authenticationManager(authenticationManager);

        http.addFilter(getAuthenticationFilter(authenticationManager));

        return http.build();
    }

    private Filter getAuthenticationFilter(AuthenticationManager authenticationManager) {
        return new AuthenticationFilter(authenticationManager, employeeService, env);
    }

    /* 인증 */

}

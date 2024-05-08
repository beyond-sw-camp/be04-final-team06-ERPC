package com.cineverse.erpc.employee.security;

import com.cineverse.erpc.employee.dto.RequestLoginDTO;
import com.cineverse.erpc.employee.service.EmployeeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;
import java.util.ArrayList;

public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private EmployeeService employeeService;
    private Environment environment;

    public AuthenticationFilter(AuthenticationManager authenticationManager,
                                EmployeeService employeeService,
                                Environment environment) {
        super(authenticationManager);
        this.employeeService = employeeService;
        this.environment = environment;
    }

    /* 로그인 시도 시 동작하는 기능(Post 방식의 /login 요청) -> request body에 담겨 온다.(stream 활용) */
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {
        try {
            RequestLoginDTO requestLogin =
                    new ObjectMapper().readValue(request.getInputStream(), RequestLoginDTO.class);

            return getAuthenticationManager().authenticate(
                    new UsernamePasswordAuthenticationToken(
                            requestLogin.getEmployeeCode(), requestLogin.getEmployeePassword(), new ArrayList<>())
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /* 로그인 성공 시 JWT 토큰 생성하는 기능 */
    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {
        System.out.println("authResult = " + authResult);
    }
}

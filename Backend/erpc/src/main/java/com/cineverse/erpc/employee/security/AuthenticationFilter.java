package com.cineverse.erpc.employee.security;

import com.cineverse.erpc.employee.dto.EmployeeDTO;
import com.cineverse.erpc.employee.dto.RequestLoginDTO;
import com.cineverse.erpc.employee.service.EmployeeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final EmployeeService employeeService;
    private final Environment environment;

    public AuthenticationFilter(AuthenticationManager authenticationManager,
                                EmployeeService employeeService,
                                Environment environment) {
        super(authenticationManager);
        this.employeeService = employeeService;
        this.environment = environment;
    }

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

    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {
        String employeeCode = ((User) authResult.getPrincipal()).getUsername();

        System.out.println("시크릿 키: " + environment.getProperty("token.secret"));
        System.out.println("employeeCode = " + employeeCode);

        EmployeeDTO employeeDetails = employeeService.getEmployeeDetailsByEmployeeCode(employeeCode);
        String employeeId = Long.toString(employeeDetails.getEmployeeId());

        String token = Jwts.builder()
                .setSubject(employeeId)
                .setExpiration(new Date(System.currentTimeMillis() +
                        Long.parseLong(environment.getProperty("token.expiration_time"))))
                .signWith(SignatureAlgorithm.HS512, environment.getProperty("token.secret"))
                .compact();

        // Access-Control-Expose-Headers 헤더 추가
        response.setHeader("Access-Control-Expose-Headers", "token, userId");
        response.setHeader("token", token);
        response.setHeader("userId", employeeId);
    }
}

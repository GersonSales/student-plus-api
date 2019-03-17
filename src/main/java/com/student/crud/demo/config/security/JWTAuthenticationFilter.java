package com.student.crud.demo.config.security;

import com.auth0.jwt.JWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.student.crud.demo.property.Credentials;
import com.student.crud.demo.student.StudentModel;
import com.student.crud.demo.student.StudentPayload;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

import static com.auth0.jwt.algorithms.Algorithm.HMAC512;
import static com.student.crud.demo.config.security.SecurityConstants.*;


public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
  private AuthenticationManager authenticationManager;

  public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
    this.authenticationManager = authenticationManager;
  }

  @Override
  public Authentication attemptAuthentication(HttpServletRequest req,
                                              HttpServletResponse res) throws AuthenticationException {
    try {
      Credentials creds = new ObjectMapper()
          .readValue(req.getInputStream(), Credentials.class);

      return authenticationManager.authenticate(
          new UsernamePasswordAuthenticationToken(
              creds.getEmail(),
              creds.getPassword(),
              new ArrayList<>())
      );
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  protected void successfulAuthentication(HttpServletRequest req,
                                          HttpServletResponse res,
                                          FilterChain chain,
                                          Authentication auth) throws IOException, ServletException {
    final StudentPayload principal = (StudentPayload) auth.getPrincipal();
    String token = JWT.create()
        .withSubject(principal.getUsername())
        .withClaim("id", principal.getId())
        .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
        .sign(HMAC512(SECRET.getBytes()));
    res.addHeader(HEADER_STRING, token);
    res.addHeader("Pragma", token);
  }


}
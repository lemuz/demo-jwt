package com.company.demo.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.company.demo.entity.MyUserDetails;
import com.company.demo.entity.Usuario;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
	
	private AuthenticationManager authenticationManager;

	public JwtAuthenticationFilter(AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		try {			
			Usuario credenciales = new ObjectMapper().readValue(request.getInputStream(), Usuario.class);
			System.out.println(credenciales);
			UsernamePasswordAuthenticationToken autenticationToken = new UsernamePasswordAuthenticationToken(credenciales.getUsername(), credenciales.getPassword(), new ArrayList<>());
			Authentication auth = authenticationManager.authenticate(autenticationToken);
			return auth;
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication auth) throws IOException, ServletException {
		MyUserDetails u = (MyUserDetails)auth.getPrincipal();
		String token = JWT.create()
				.withSubject(u.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + Constants.TOKEN_EXPIRATION_TIME))
                .sign(Algorithm.HMAC512(Constants.SUPER_SECRET_KEY.getBytes()));
		response.addHeader(Constants.HEADER_AUTHORIZACION_KEY, Constants.TOKEN_BEARER_PREFIX + token);
	}
}

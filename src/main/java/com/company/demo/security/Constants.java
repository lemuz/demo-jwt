package com.company.demo.security;

public class Constants {
	
	// Spring Security
	public static final String LOGIN_URL = "/login";
	public static final String HEADER_AUTHORIZACION_KEY = "Authorization";
	public static final String TOKEN_BEARER_PREFIX = "Bearer ";

	// JWT
	public static final String ISSUER_INFO = "https://192.168.100.254:8080/";
	public static final String SUPER_SECRET_KEY = "Clave123!";
	public static final long TOKEN_EXPIRATION_TIME = 7_200_000; // 4 horas
}

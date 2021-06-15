package com.company.demo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.company.demo.repository.OpcionMenuRepository;
import com.company.demo.repository.UsuarioRepository;
import com.company.demo.service.MyDetailsService;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{	
	
	private MyDetailsService adminService;
    private UsuarioRepository userRepository;
    private OpcionMenuRepository rolRepository;
	
	public WebSecurityConfig(MyDetailsService adminService, UsuarioRepository userRepository, OpcionMenuRepository rolRepository) {
		super();
		this.adminService = adminService;
		this.userRepository = userRepository;
		this.rolRepository = rolRepository;
	}
     
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
     
    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(this.adminService);
        authProvider.setPasswordEncoder(passwordEncoder());
         
        return authProvider;
    }
 
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }
	
	@Override
	protected void configure(HttpSecurity http) throws Exception{
		/*
		 * 1. Se desactiva el uso de cookies
		 * 2. Se activa la configuración CORS con los valores por defecto
		 * 3. Se desactiva el filtro CSRF
		 * 4. Se indica que el login no requiere autenticación
		 * 5. Se indica que el resto de URLs esten securizadas
		 */
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)		
		.and()
		.cors()		
		.and()
		.csrf().disable()
		.addFilter(new JwtAuthenticationFilter(authenticationManager()))
		.addFilter(new JwtAuthorizationFilter(authenticationManager() , this.userRepository, this.rolRepository))
        .authorizeRequests()
        .antMatchers(HttpMethod.POST,"/login").permitAll()
        .antMatchers("/usuarios/**").hasAuthority("Mantenimiento de Usuarios")
        .antMatchers("/opciones/**").hasAuthority("Mantenimiento de Opciones")
        .antMatchers("/roles/**").hasAuthority("Mantenimiento de Roles")
        .antMatchers("/permisos/**").hasAuthority("Mantenimiento de Permisos")
        .anyRequest().authenticated()
        .and()
        .httpBasic()
        .and()
        .exceptionHandling().accessDeniedPage("/error/403");
	}
	
	@Bean
	CorsConfigurationSource corsConfigurationSource() {
		final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		CorsConfiguration config =new CorsConfiguration().applyPermitDefaultValues();
		config.setAllowCredentials(true);
        config.addAllowedOrigin("*");
        config.addAllowedHeader("*");
        config.addExposedHeader("*");
        source.registerCorsConfiguration("/**", config);
//		source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
		return source;
	}
}

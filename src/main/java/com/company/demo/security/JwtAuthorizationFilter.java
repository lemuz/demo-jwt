package com.company.demo.security;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.company.demo.entity.MyUserDetails;
import com.company.demo.entity.OpcionMenu;
import com.company.demo.entity.Usuario;
import com.company.demo.repository.OpcionMenuRepository;
import com.company.demo.repository.UsuarioRepository;


public class JwtAuthorizationFilter extends BasicAuthenticationFilter {	
	
	private UsuarioRepository userRepo;
	
	private OpcionMenuRepository rolRepo;
	
	public JwtAuthorizationFilter(AuthenticationManager authManager, UsuarioRepository userRepo, OpcionMenuRepository rolRepo) {
		super(authManager);
		this.userRepo = userRepo;
		this.rolRepo = rolRepo;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		String header = req.getHeader(Constants.HEADER_AUTHORIZACION_KEY);
		if (header == null || !header.startsWith(Constants.TOKEN_BEARER_PREFIX)) {
			chain.doFilter(req, res);
			return;
		}
		Authentication authentication = getAuthentication(req);
		SecurityContextHolder.getContext().setAuthentication(authentication);
		chain.doFilter(req, res);
	}

	private Authentication getAuthentication(HttpServletRequest request) {
		String token = request.getHeader(Constants.HEADER_AUTHORIZACION_KEY).replace(Constants.TOKEN_BEARER_PREFIX,"");;
		if (token != null) {
			String userName = null;
			try {
			// Se procesa el token y se recupera el usuario.
					userName = JWT.require(Algorithm.HMAC512(Constants.SUPER_SECRET_KEY.getBytes()))
							.build()
							.verify(token)
							.getSubject();
			}catch(TokenExpiredException tokenE) {
				System.out.println("token vencido: " + tokenE.getMessage());
				
			}
			catch(Exception e) {
				e.printStackTrace();
			}
			if (userName != null) {
				Usuario usuario = userRepo.findByUsername(userName);
				List<OpcionMenu> roles = rolRepo.findByUsuarios(usuario.getId());
				Set<OpcionMenu> setRoles = new HashSet<OpcionMenu>(roles);
				usuario.setRoles(setRoles);
				MyUserDetails credenciales = new MyUserDetails(usuario);
				UsernamePasswordAuthenticationToken autenticado = new UsernamePasswordAuthenticationToken(userName, null, credenciales.getAuthorities());				
				return autenticado;
			}
			return null;
		}
		return null;
	}
}

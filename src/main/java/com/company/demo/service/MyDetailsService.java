package com.company.demo.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.company.demo.entity.MyUserDetails;
import com.company.demo.entity.OpcionMenu;
import com.company.demo.entity.Usuario;
import com.company.demo.repository.OpcionMenuRepository;
import com.company.demo.repository.UsuarioRepository;

@Service
public class MyDetailsService implements UserDetailsService{
	
	@Autowired
	private UsuarioRepository userRepo;
	
	@Autowired
	private OpcionMenuRepository rolRepo;

	@Override
	public UserDetails loadUserByUsername(String nickname) throws UsernameNotFoundException {
		final Usuario u = loadUserAndRolByUserName(nickname);
		MyUserDetails userDet = new MyUserDetails(u);
		return userDet;
	}
	
	public Usuario loadUserAndRolByUserName(String nickname)throws UsernameNotFoundException {
		Usuario u = userRepo.findByUsername(nickname);
		if (u == null) {
			throw new UsernameNotFoundException("Invalid username or password");
		}
		List<OpcionMenu> roles = rolRepo.findByUsuarios(u.getId());
		Set<OpcionMenu> setRoles = new HashSet<OpcionMenu>(roles);
		u.setRoles(setRoles);
		return u;
	} 
	
	public List<Usuario> loadAllUsersAndRols(){
		List<Usuario> usuarios = userRepo.findAll();
		for(Usuario u : usuarios) {
			List<OpcionMenu> roles = rolRepo.findByUsuarios(u.getId());
			Set<OpcionMenu> setRoles = new HashSet<OpcionMenu>(roles);
			u.setRoles(setRoles);
			u.setPassword("");
		}
		return usuarios;
	}
	
	public Usuario saveUser(Usuario usuario){
		Usuario u = userRepo.save(usuario);
//		if (u == null) {
//			throw new UsernameNotFoundException("Invalid username or password");
//		}
		List<OpcionMenu> roles = rolRepo.findByUsuarios(usuario.getId());
		Set<OpcionMenu> setRoles = new HashSet<OpcionMenu>(roles);
		u.setRoles(setRoles);
		return u;
	}
}
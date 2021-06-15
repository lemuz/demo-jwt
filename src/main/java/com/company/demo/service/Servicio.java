package com.company.demo.service;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.company.demo.entity.OpcionMenu;
import com.company.demo.entity.Usuario;
import com.company.demo.repository.AccesoRepository;
import com.company.demo.repository.OpcionMenuRepository;
import com.company.demo.repository.UsuarioRepository;

@Service
public class Servicio {

	@Autowired
	private UsuarioRepository userRepo;
	
	@Autowired
	private OpcionMenuRepository opcionRepo;
	
	@Autowired
	private AccesoRepository accesoRepo;
	
	public Usuario login(Usuario user) {
		Usuario u = userRepo.login(user.getUsername(), user.getPassword());
		return u;
	}
	
	public List<OpcionMenu> listarOpciones(Integer idUsuario) {
		List<OpcionMenu> opciones = opcionRepo.findByUsuarios(idUsuario);
		return opciones;
	}

	public List<Usuario> listarUsuarios() {
		List<Usuario> usuarios = userRepo.findAll();
		return usuarios;
	}

	public Usuario obtenerUsuario(Integer idUsuario) {
		Usuario u = userRepo.findById(idUsuario).get();
		return u;
	}

	public boolean guardarActualizar(Usuario user) {
		user.setUsername(user.getUsername().toUpperCase());
		Usuario u = userRepo.save(user);
		return (u!=null)? true: false;
	}

	public List<OpcionMenu> listarOpcionesNo(Integer idUsuario) {
		List<OpcionMenu> opcionesUsuario = listarOpciones(idUsuario);
		List<OpcionMenu> opciones = opcionRepo.findAll();
		for(OpcionMenu o : opcionesUsuario) {
			for(int index = 0 ; index< opciones.size(); index++) {
				if(o.getId()== opciones.get(index).getId()) {
					opciones.remove(index);
				}
			}
		}		
		return opciones;
	}

	public boolean agregarOpcion(String codigoUser, Integer idOpcion) {
		Usuario u = accesoRepo.saveByUser(codigoUser, idOpcion);
		return (u!=null)? true: false;
	}

	public Usuario borrarOpcion(Integer idUser, Integer idOpcion) {
		Usuario u = accesoRepo.deleteForUser(idUser, idOpcion);
		return u;
	}

	public boolean borrarUsuario(Integer idUser) {
		userRepo.deleteById(idUser);
		return true;
	}

}
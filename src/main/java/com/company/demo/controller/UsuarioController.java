package com.company.demo.controller;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.company.demo.service.Servicio;
import com.company.demo.entity.Usuario;

import ch.qos.logback.classic.Level;

@RestController
@RequestMapping("usuarios")
public class UsuarioController {

	@Autowired
	private Servicio service;
	
	@GetMapping("/")
    public List<Usuario> obtenerUsuarios() {
        try {
            return service.listarUsuarios();
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }
	
	@GetMapping("/{id}")
    public Usuario obtenerUsuario(@PathVariable("id") Integer idUsuario) {
        try {
            return service.obtenerUsuario(idUsuario);
        } catch (Exception e) {
            e.printStackTrace();
            return new Usuario();
        }
    }
	
	@PostMapping("/")
    public boolean guardarActualizar(@RequestBody Usuario user) {
        try {
            return !service.guardarActualizar(user);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
	
	@PostMapping("/opciones/{codigoUsuario}/{idOpcion}")
    public boolean agregarOpcion(@PathVariable("codigoUsuario") String codigoUser, @PathVariable("idOpcion") Integer idOpcion) {
        try {
            return service.agregarOpcion(codigoUser, idOpcion);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
	
	@DeleteMapping("/opciones/{idUsuario}/{idOpcion}")
    public Usuario borrarOpcion(@PathVariable("idUsuario") Integer idUser, @PathVariable("idOpcion") Integer idOpcion) {
        try {
            return service.borrarOpcion(idUser, idOpcion);
        } catch (Exception e) {
            e.printStackTrace();
            return service.obtenerUsuario(idUser);
        }
    }
	
	@DeleteMapping("/{idUsuario}")
    public boolean borrarUsuario(@PathVariable("idUsuario") Integer idUser) {
        try {
            return service.borrarUsuario(idUser);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
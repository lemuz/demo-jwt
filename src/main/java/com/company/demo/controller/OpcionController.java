package com.company.demo.controller;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.company.demo.entity.OpcionMenu;
import com.company.demo.service.Servicio;

import ch.qos.logback.classic.Level;

@RestController
@RequestMapping("opciones")
public class OpcionController {
	
	@Autowired
	private Servicio service;

	@GetMapping("/usuario/{id}")
    public List<OpcionMenu> obtenerOpciones(@PathVariable("id") Integer idUsuario) {
        try {
            return service.listarOpciones(idUsuario);
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }
	
	//?????????
	@GetMapping("/usuario/no/{idUsuario}")
    public List<OpcionMenu> obtenerOpcionesNo(@PathVariable("idUsuario") Integer idUsuario) {
        try {
            return service.listarOpcionesNo(idUsuario);
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }
}

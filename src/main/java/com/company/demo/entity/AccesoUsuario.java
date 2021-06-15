package com.company.demo.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name ="accesos_usuario")
public class AccesoUsuario implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy =GenerationType.IDENTITY)
	@Column(name="idacc")
	private Integer idAcc;
	
	@Column(name="idopc")
	private Integer idOpc;
	
	@Column(name="idusr")
	private Integer idUsr;

	public Integer getIdAcc() {
		return idAcc;
	}

	public void setIdAcc(Integer idAcc) {
		this.idAcc = idAcc;
	}

	public Integer getIdOpc() {
		return idOpc;
	}

	public void setIdOpc(Integer idOpc) {
		this.idOpc = idOpc;
	}

	public Integer getIdUsr() {
		return idUsr;
	}

	public void setIdUsr(Integer idUsr) {
		this.idUsr = idUsr;
	}

	public AccesoUsuario(Integer idAcc, Integer idOpc, Integer idUsr) {
		super();
		this.idAcc = idAcc;
		this.idOpc = idOpc;
		this.idUsr = idUsr;
	}

	public AccesoUsuario() {
	}

	@Override
	public String toString() {
		return "AccesoUsuario [idAcc=" + idAcc + ", idOpc=" + idOpc + ", idUsr=" + idUsr + "]";
	}
	
	
}

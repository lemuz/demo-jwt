package com.company.demo.entity;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="opciones_menu")
public class OpcionMenu{

	/**
	 * Alex Lemus
	 */

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	//oracle auto_increment
//	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "id_Sequence2")
//	@SequenceGenerator(name = "id_Sequence2", sequenceName = "ID_SEQ2")
	@Column(name="idopc")
	private Integer id;
	
	@Column(name="etiqueta")
	private String nombre;
	
	@Column(name="comando")
	private String comando;
	
	@Column(name="estado")
	private String estado;
	
	@ManyToMany
    @JoinTable( 
        name = "accesos_usuario", 
        joinColumns = @JoinColumn(
          name = "idopc", referencedColumnName = "idopc"), 
        inverseJoinColumns = @JoinColumn(
          name = "idusr", referencedColumnName = "idusr"),schema="db_user1") 
    private Collection<Usuario> usuarios;

	public OpcionMenu() {
		
	}

	public OpcionMenu(Integer id, String nombre, String comando, String estado, Collection<Usuario> usuarios) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.comando = comando;
		this.estado = estado;
		this.usuarios = usuarios;
	}

	public OpcionMenu(String nombre) {
		this.nombre = nombre;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getComando() {
		return comando;
	}

	public void setComando(String comando) {
		this.comando = comando;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OpcionMenu other = (OpcionMenu) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Rol [id=" + id + ", nombre=" + nombre +"]";
	}
}
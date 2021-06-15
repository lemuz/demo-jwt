package com.company.demo.entity;

import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;


@Entity
@Table(name="usuarios")
public class Usuario {

	/**
	 * 
	 */
//	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	//oracle auto increment
//	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "id_Sequence1")
//	@SequenceGenerator(name = "id_Sequence1", sequenceName = "ID_SEQ1")
    @Column(name = "idusr")
	private Integer id;
	
	@Column(name="codigo")
	private String username;
	
	@Column(name="nombre")
	private String nombre;
	
	@Column(name="clave")
	private String password;
	
	@ManyToMany
    @JoinTable( 
        name = "accesos_usuario", 
        joinColumns = @JoinColumn(
          name = "idusr", referencedColumnName = "idusr"), 
        inverseJoinColumns = @JoinColumn(
          name = "idopc", referencedColumnName = "idopc")) 
    private Set<OpcionMenu> roles;
	
	@Transient
	private boolean enabled;
	
	public Usuario() {
		super();
	}

	public Usuario(Integer id, String username, String nombre, String password, Set<OpcionMenu> roles) {
		super();
		this.id = id;
		this.username = username;
		this.nombre = nombre;
		this.password = password;
		this.roles = roles;
		this.enabled = true;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String nickname) {
		this.username = nickname;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Set<OpcionMenu> getRoles() {
		return roles;
	}

	public void setRoles(Set<OpcionMenu> roles) {
		this.roles = roles;
	}

	public boolean isEnabled() {
		return true;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
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
		Usuario other = (Usuario) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Usuario [id=" + id + ", username=" + username + ", nombre=" + nombre + ", password=" + password
				+ ", roles=" + roles + ", enabled=" + enabled + "]";
	}

}
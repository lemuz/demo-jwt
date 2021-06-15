package com.company.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.company.demo.entity.OpcionMenu;
import com.company.demo.entity.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer>{

	@Query(value="SELECT * FROM USUARIOS WHERE codigo = UPPER(:codigo) AND clave = :clave LIMIT 1", nativeQuery = true)
	Usuario login(@Param("codigo") String nickname, @Param("clave") String password);
	
	@Query(value="SELECT om.* FROM opciones_menu AS om INNER JOIN accesos_usuario AS au ON om.idopc = au.idopc INNER JOIN usuarios AS u ON au.idusr = u.idusr WHERE u.idusr = :id", nativeQuery = true)
	List<OpcionMenu> findRoles(@Param("id") Integer idUsuario);
	
	Usuario findByUsername(String nickname);

	Usuario findByRoles(OpcionMenu rol) throws Exception;
	
//oracle
//	@Query(value="SELECT u.* FROM usuario u WHERE rownum=1 AND u.codigo = :nickname AND u.clave = :password", nativeQuery = true)
//	Usuario login(@Param("nickname") String nickname, @Param("password") String password);
	
	//para spring security
//	@Query(value="SELECT om.* FROM opciones_menu om INNER JOIN accessos_usuario au ON om.idopc = au.idopc INNER JOIN usuarios u ON au.idusr = u.idusr WHERE u.idusr = :id", nativeQuery = true)
//	List<OpcionMenu> findRoles(@Param("id") Integer idUsuario);
//	
//	Usuario findByUsername(String nickname);
//
//	Usuario findByRoles(OpcionMenu rol) throws Exception;
}
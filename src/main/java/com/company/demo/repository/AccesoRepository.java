package com.company.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.company.demo.entity.AccesoUsuario;
import com.company.demo.entity.Usuario;

@Repository
public interface AccesoRepository extends JpaRepository<AccesoUsuario, Integer>{

	@Query(value = "INSERT INTO accesos_usuario VALUES(0, :idOpcion, (SELECT idUsr FROM USUARIOS WHERE codigo = :codigo))", nativeQuery = true)
	Usuario saveByUser(@Param("codigo") String codigoUser, @Param("idOpcion") Integer idOpcion);

	@Query(value = "DELETE FROM accesos_usuario WHERE idusr = :idUsuario AND idopc = :idOpcion", nativeQuery = true)
	Usuario deleteForUser(@Param("idUsuario")Integer idUser, @Param("idOpcion")Integer idOpcion);

}
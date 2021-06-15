package com.company.demo.repository;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.company.demo.entity.OpcionMenu;
import com.company.demo.entity.Usuario;

@Repository
public interface OpcionMenuRepository extends JpaRepository<OpcionMenu, Integer>{

	OpcionMenu findByNombre(String string);

	@Query(value = "SELECT om.* FROM OPCIONES_MENU AS om INNER JOIN ACCESOS_USUARIO AS au ON om.idopc = au.idopc INNER JOIN USUARIOS AS u ON au.idusr = u.idusr WHERE u.idusr = :idUser", nativeQuery = true)
	List<OpcionMenu> findByUsuarios(@Param("idUser") Integer idUsuario);

}

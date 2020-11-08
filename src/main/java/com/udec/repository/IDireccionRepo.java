package com.udec.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.udec.entity.Direccion;

@Repository
public interface IDireccionRepo extends JpaRepository<Direccion, Integer> {
	
	@Modifying
	@Transactional
	@Query(value = "UPDATE public.dirreccion SET barrio = ?, descripcion = ? WHERE autor_id = ?", nativeQuery = true)
	void editarNativo(String barrio, String descripcion, Integer idAutor);
}

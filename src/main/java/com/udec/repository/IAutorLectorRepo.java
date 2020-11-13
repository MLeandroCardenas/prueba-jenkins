package com.udec.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.udec.entity.AutorLector;

@Repository
public interface IAutorLectorRepo extends JpaRepository<AutorLector, Integer> {
	
	
	@Modifying
	@Transactional
	@Query(value="INSERT INTO autor_lector (info_adicional, id_autor, id_lector) VALUES (:infoAdicional, :idAutor, :idLector)", nativeQuery = true)
	Integer guardarAutorLector(@Param("infoAdicional") String infoAdicional, @Param("idAutor") Integer idAutor, @Param("idLector") Integer idLector);
	
	//Eliminar asociacion
	
	@Query("from AutorLector al where al.autor.id = :idAutor")
	Page<AutorLector> listarAutoresLectores(Pageable pageable, @Param("idAutor") Integer idAutor);
}

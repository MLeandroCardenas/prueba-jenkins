package com.udec.repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.udec.entity.Autor;

@Repository
public interface IAutorRepo extends JpaRepository<Autor, Integer>{
	
	long countById(int id);
	
	@Query("SELECT l.autor FROM Libro l WHERE lower(l.nombre) LIKE lower(concat('%', :nombreLibro, '%'))")
	Page<Autor> listarAutoresConLibros(Pageable pageable, String nombreLibro);
	
	Page<Autor> findBylibros_NombreIgnoreCase(Pageable pageable, String libro);
	
	@Modifying
	@Transactional
	@Query(value = "UPDATE public.dirreccion SET barrio = ?, descripcion = ? WHERE autor_id = ?", nativeQuery = true)
	void editarNativo(String barrio, String descripcion, Integer idAutor);
	
}
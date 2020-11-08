package com.udec.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.udec.entity.Libro;

@Repository
public interface ILibroRepo extends JpaRepository<Libro, Integer> {
	
	long countByNombreAndAutor_Id(String nombreLibro, int id);
}

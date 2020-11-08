package com.udec.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.udec.entity.Alumno;

@Repository
public interface IAlumnoRepo extends JpaRepository<Alumno, Integer> {
	
	Alumno findByIdentificacion (String identificacion);

	@Query(value = "SELECT a FROM Alumno a")
	List<Alumno> findAllAlumnos (Sort sort);
	
	@Query("SELECT COUNT (a) FROM Alumno a WHERE a.identificacion = :identificacion OR a.id = :id")
	long validarUsuarioEditar(String identificacion, int id);
	
	long countById(int id);
	
	Page<Alumno> findByNombresIgnoreCase(Pageable pageable, String nombre);
	
}

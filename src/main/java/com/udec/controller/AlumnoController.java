package com.udec.controller;
import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.udec.entity.Alumno;
import com.udec.service.IAlumnoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@PreAuthorize("hasAuthority('Alumno')")
@RestController
@RequestMapping("/alumnos")
@Validated
public class AlumnoController {
	
	@Autowired
	private IAlumnoService servicioAlumno;

	@PreAuthorize("hasAuthority('Admin')")
	@GetMapping("/listar/{lazy}/{page}/{size}")
	public ResponseEntity<Page<Alumno>> obtenerAlumnos(@PathVariable boolean lazy, @PathVariable @Min(value = 0, message = "minimo 0") int page, @PathVariable @Min(value = 1, message = "minimo 1") int size) {		
		Page<Alumno> alumno = servicioAlumno.listarTodos(lazy,page,size);
		return new ResponseEntity<Page<Alumno>>(alumno,HttpStatus.OK);	
	}
	
	@PreAuthorize("hasAuthority('Admin')")
	@GetMapping("/listar/ordenado")
	public ResponseEntity<List<Alumno>> obtenerAlumnosOrdenado() {		
		List<Alumno> alumno = servicioAlumno.ordenarPorNombres();
		return new ResponseEntity<List<Alumno>>(alumno,HttpStatus.OK);
	}
	
	@PreAuthorize("hasAuthority('Admin')")
	@GetMapping("/listar/{lazy}/{id}")
	public ResponseEntity<Alumno> obtenerAlumno(@PathVariable boolean lazy, @PathVariable @Min(value = 1, message = "minimo 1") Integer id) {		
		Alumno alumno = servicioAlumno.listarPorId(lazy,id);
		return new ResponseEntity<Alumno>(alumno,HttpStatus.OK);	
	}
	
	@PreAuthorize("hasAuthority('Admin')")
	@GetMapping("/listar/{page}/{size}/{nombre}")
	public ResponseEntity<Page<Alumno>> obtenerAlumnoPorNombre(@PathVariable @Min(value = 0, message = "no puede ser menor a 0") int page, @PathVariable @Min(value = 1, message = "no puede ser menor a 1") int size, @PathVariable @NotNull(message = "nombre requerido") String nombre) {		
		Page<Alumno> alumno = servicioAlumno.listarPorNombre(page, size, nombre);
		return new ResponseEntity<Page<Alumno>>(alumno,HttpStatus.OK);
	}
	
	@PreAuthorize("hasAuthority('Admin')")
	@PostMapping("/guardar")
	public ResponseEntity<Alumno> guardarAlumno(@Valid @RequestBody Alumno alumno) {		
		servicioAlumno.guardar(alumno);
		return new ResponseEntity<Alumno>(HttpStatus.CREATED);	
	}
	
	
	@PutMapping("/editar")
	public ResponseEntity<Alumno> editarAlumno(@Valid @RequestBody Alumno alumno) {		
		servicioAlumno.editar(alumno);
		return new ResponseEntity<Alumno>(alumno,HttpStatus.OK);	
	}
	
	@PreAuthorize("hasAuthority('Admin')")
	@DeleteMapping("/eliminar/{id}")
	public ResponseEntity<Alumno> eliminarAlumno(@PathVariable @Min(value = 1, message = "minimo 1") Integer id) {		
		servicioAlumno.eliminar(id);
		return new ResponseEntity<Alumno>(HttpStatus.NO_CONTENT);	
	}
}

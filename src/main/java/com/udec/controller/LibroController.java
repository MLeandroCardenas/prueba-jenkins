package com.udec.controller;

import javax.validation.Valid;
import javax.validation.constraints.Min;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.udec.entity.Libro;
import com.udec.service.ILibroService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/libros")
@Validated
@Api(value = "/libros", description = "Servicios rest para la clase libro")
public class LibroController {

	@Autowired
	private ILibroService servicio;
	
	@GetMapping("/listar/{lazy}/{page}/{size}")
	@ApiOperation(value = "Obtener registros",
    notes = "Obtener todos los registros") 	
	@ApiResponses(value = { @ApiResponse (code = 404, message = "Registro no encontrado") })
	public ResponseEntity<Page<Libro>> obtenerLibros(@PathVariable boolean lazy, @PathVariable @Min(value = 0, message = "minimo 0") int page, @PathVariable @Min(value = 1, message = "minimo 1") int size) {		
		Page<Libro> libro = servicio.listarTodos(lazy, page, size);
		return new ResponseEntity<Page<Libro>>(libro, HttpStatus.OK);	
	}
	
	@GetMapping("/listar/{lazy}/{id}")
	@ApiOperation(value = "Obtener autor",
    notes = "Obtener un autor") 	
	@ApiResponses(value = { @ApiResponse (code = 404, message = "Registro no encontrado") })
	public ResponseEntity<Libro> obtenerLibro(@PathVariable boolean lazy, @PathVariable int id) {		
		Libro libro = servicio.listarPorId(lazy, id);
		return new ResponseEntity<Libro>(libro, HttpStatus.OK);	
	}
	
	@PostMapping("/guardar")
	public ResponseEntity<Libro> guardarLibro(@Valid @RequestBody Libro libro) {		
		servicio.guardar(libro);
		return new ResponseEntity<Libro>(HttpStatus.CREATED);	
	}
	
	@PutMapping("/editar")
	public ResponseEntity<Libro> editarLibro(@Valid @RequestBody Libro libro) {		
		servicio.editar(libro);
		return new ResponseEntity<Libro>(libro,HttpStatus.OK);	
	}
	
	@DeleteMapping("/eliminar/{id}")
	public ResponseEntity<Libro> eliminarLibro(@PathVariable @Min(value = 1, message = "minimo 1") Integer id) {		
		servicio.eliminar(id);
		return new ResponseEntity<Libro>(HttpStatus.NO_CONTENT);	
	}
}

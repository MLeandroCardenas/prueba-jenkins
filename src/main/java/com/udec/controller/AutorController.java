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

import com.udec.dto.AutorLectorDto;
import com.udec.entity.Autor;
import com.udec.entity.AutorLector;
import com.udec.entity.Direccion;
import com.udec.service.IAutorService;
import com.udec.views.AutorView;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/autores")
@Validated
@Api(value = "/autores", description = "Servicios rest para la clase autor")
public class AutorController {
	
	@Autowired
	private IAutorService servicio;
	
	@GetMapping("/listartodos/{lazy}/{page}/{size}")
	@ApiOperation(value = "Obtener registros",
    notes = "Obtener todos los registros") 	
	@ApiResponses(value = { @ApiResponse (code = 404, message = "Registro no encontrado") })
	public ResponseEntity<Page<Autor>> obtenerAutores(@PathVariable boolean lazy, @PathVariable @Min(value = 0, message = "minimo 0") int page, @PathVariable @Min(value = 1, message = "minimo 1") int size) {		
		Page<Autor> autores = servicio.listarTodos(lazy, page, size);
		return new ResponseEntity<Page<Autor>>(autores, HttpStatus.OK);	
	}
	
	@GetMapping("/listar/{lazy}/{id}")
	@ApiOperation(value = "Obtener autor",
    notes = "Obtener un autor") 	
	@ApiResponses(value = { @ApiResponse (code = 404, message = "Registro no encontrado") })
	public ResponseEntity<Autor> obtenerAutor(@PathVariable boolean lazy, @PathVariable int id) {		
		Autor autor = servicio.listarPorId(lazy,id);
		return new ResponseEntity<Autor>(autor, HttpStatus.OK);	
	}
	
	@GetMapping("/info/autoresvista/{page}/{size}")
	@ApiOperation(value = "Obtener registros",
    notes = "Obtener todos los registros") 	
	@ApiResponses(value = { @ApiResponse (code = 404, message = "Registro no encontrado") })
	public ResponseEntity<Page<AutorView>> obtenerVistaAutores(@PathVariable @Min(value = 0, message = "minimo 0") int page, @PathVariable @Min(value = 1, message = "minimo 1") int size) {		
		Page<AutorView> autores = servicio.vistaInfoAutores(page, size);
		return new ResponseEntity<Page<AutorView>>(autores, HttpStatus.OK);	
	}
	
	@GetMapping("/info/autorvista/{id}")
	@ApiOperation(value = "Obtener registros",
    notes = "Obtener todos los registros") 	
	@ApiResponses(value = { @ApiResponse (code = 404, message = "Registro no encontrado") })
	public ResponseEntity <AutorView> obtenerVistaAutor(@PathVariable @Min(value = 0, message = "minimo 0") Integer id) {		
		AutorView autor = servicio.vistaInfoAutor(id);
		return new ResponseEntity <AutorView> (autor, HttpStatus.OK);	
	}
	
	@GetMapping("/listar/{page}/{size}/{nombreLibro}")
	@ApiOperation(value = "Obtener registros",
    notes = "Obtener todos los registros") 	
	@ApiResponses(value = { @ApiResponse (code = 404, message = "Registro no encontrado") })
	public ResponseEntity<Page<Autor>> obtenerAutoresLibro(@PathVariable @Min(value = 0, message = "minimo 0") int page, @PathVariable @Min(value = 1, message = "minimo 1") int size, @PathVariable String nombreLibro) {		
		Page<Autor> autores = servicio.listarAutoresConLibros(page, size, nombreLibro);
		return new ResponseEntity<Page<Autor>>(autores, HttpStatus.OK);	
	}
	
	@GetMapping("/listar/reservadas/{page}/{size}/{nombreLibro}")
	@ApiOperation(value = "Obtener registros",
    notes = "Obtener todos los registros") 	
	@ApiResponses(value = { @ApiResponse (code = 404, message = "Registro no encontrado") })
	public ResponseEntity<Page<Autor>> obtenerAutoresLibroReservadas(@PathVariable @Min(value = 0, message = "minimo 0") int page, @PathVariable @Min(value = 1, message = "minimo 1") int size,  @PathVariable String nombreLibro) {		
		Page<Autor> autores = servicio.listarAutoresConLibrosReservadas(page, size, nombreLibro);
		return new ResponseEntity<Page<Autor>>(autores, HttpStatus.OK);	
	}
	
	@PostMapping("/guardar")
	public ResponseEntity<Autor> guardarAutor(@Valid @RequestBody Autor autor) {		
		servicio.guardar(autor);
		return new ResponseEntity<Autor>(HttpStatus.CREATED);	
	}
	
	@GetMapping("/autorLector/{page}/{size}/{idAutor}")
	public ResponseEntity<Page<AutorLector>> listarAutorLector(@PathVariable @Min(value = 0, message = "minimo 0") int page, @PathVariable @Min(value = 1, message = "minimo 1") int size,  @PathVariable Integer idAutor) {		
		Page<AutorLector> autores = servicio.listarAutoresLectores(page, size, idAutor);
		return new ResponseEntity<Page<AutorLector>>(autores, HttpStatus.OK);	
	}
	
	@PostMapping("/asociarLector")
	public ResponseEntity<Autor> guardarAutorLector(@Valid @RequestBody AutorLectorDto autor) {		
		servicio.guardarAutorLector(autor);
		return new ResponseEntity<Autor>(HttpStatus.CREATED);	
	}
	
	@PutMapping("/editar")
	public ResponseEntity<Autor> editarAutor(@Valid @RequestBody Autor autor) {		
		servicio.editar(autor);
		return new ResponseEntity<Autor>(autor,HttpStatus.OK);	
	}
	
	@PutMapping("/editarnativo")
	public ResponseEntity<Direccion> editarDireccionNativo(@Valid @RequestBody Direccion direccion) {		
		servicio.editarNativo(direccion);
		return new ResponseEntity<Direccion>(direccion,HttpStatus.OK);	
	}
	
	@DeleteMapping("/eliminar/{id}")
	public ResponseEntity<Autor> eliminarAutor(@PathVariable @Min(value = 1, message = "minimo 1") Integer id) {		
		servicio.eliminar(id);
		return new ResponseEntity<Autor>(HttpStatus.NO_CONTENT);	
	}
	
	@DeleteMapping("/eliminar/id/{id}")
	public ResponseEntity<Autor> eliminarSoloAutor(@PathVariable @Min(value = 1, message = "minimo 1") Integer id) {		
		servicio.eliminarSinLibros(id);
		return new ResponseEntity<Autor>(HttpStatus.NO_CONTENT);	
	}
}

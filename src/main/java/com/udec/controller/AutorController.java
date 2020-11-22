package com.udec.controller;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Min;
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
	
	@PreAuthorize("hasAuthority('Admin')")
	@GetMapping("/listartodos/{lazy}/{page}/{size}")
	public ResponseEntity<Page<Autor>> obtenerAutores(@PathVariable boolean lazy, @PathVariable @Min(value = 0, message = "minimo 0") int page, @PathVariable @Min(value = 1, message = "minimo 1") int size) {		
		Page<Autor> autores = servicio.listarTodos(lazy, page, size);
		return new ResponseEntity<Page<Autor>>(autores, HttpStatus.OK);	
	}
	
	@PreAuthorize("hasAuthority('Admin')")
	@GetMapping("/listar/{lazy}/{id}")
	public ResponseEntity<Autor> obtenerAutor(@PathVariable boolean lazy, @PathVariable int id) {		
		Autor autor = servicio.listarPorId(lazy,id);
		return new ResponseEntity<Autor>(autor, HttpStatus.OK);	
	}
	

	@PreAuthorize("hasAuthority('Admin')")
	@GetMapping("/info/autoresvista/{page}/{size}")
	public ResponseEntity<Page<AutorView>> obtenerVistaAutores(@PathVariable @Min(value = 0, message = "minimo 0") int page, @PathVariable @Min(value = 1, message = "minimo 1") int size) {		
		Page<AutorView> autores = servicio.vistaInfoAutores(page, size);
		return new ResponseEntity<Page<AutorView>>(autores, HttpStatus.OK);	
	}
	
	@PreAuthorize("hasAuthority('Admin')")
	@GetMapping("/info/autorvista/{id}")
	public ResponseEntity <AutorView> obtenerVistaAutor(@PathVariable @Min(value = 0, message = "minimo 0") Integer id) {		
		AutorView autor = servicio.vistaInfoAutor(id);
		return new ResponseEntity <AutorView> (autor, HttpStatus.OK);	
	}
	
	@PreAuthorize("hasAuthority('Alumno') OR hasAuthority('Admin')")
	@GetMapping("/listar/{page}/{size}/{nombreLibro}")
	public ResponseEntity<Page<Autor>> obtenerAutoresLibro(@PathVariable @Min(value = 0, message = "minimo 0") int page, @PathVariable @Min(value = 1, message = "minimo 1") int size, @PathVariable String nombreLibro) {		
		Page<Autor> autores = servicio.listarAutoresConLibros(page, size, nombreLibro);
		return new ResponseEntity<Page<Autor>>(autores, HttpStatus.OK);	
	}
	
	@PreAuthorize("hasAuthority('Alumno') OR hasAuthority('Admin')")
	@GetMapping("/listar/reservadas/{page}/{size}/{nombreLibro}")
	public ResponseEntity<Page<Autor>> obtenerAutoresLibroReservadas(@PathVariable @Min(value = 0, message = "minimo 0") int page, @PathVariable @Min(value = 1, message = "minimo 1") int size,  @PathVariable String nombreLibro) {		
		Page<Autor> autores = servicio.listarAutoresConLibrosReservadas(page, size, nombreLibro);
		return new ResponseEntity<Page<Autor>>(autores, HttpStatus.OK);	
	}
	
	@PreAuthorize("hasAuthority('Admin')")
	@PostMapping("/guardar")
	public ResponseEntity<Autor> guardarAutor(@Valid @RequestBody Autor autor) {		
		servicio.guardar(autor);
		return new ResponseEntity<Autor>(HttpStatus.CREATED);	
	}
	
	@PreAuthorize("hasAuthority('Alumno') OR hasAuthority('Admin')")
	@GetMapping("/autorLector/{page}/{size}/{idAutor}")
	public ResponseEntity<Page<AutorLector>> listarAutorLector(@PathVariable @Min(value = 0, message = "minimo 0") int page, @PathVariable @Min(value = 1, message = "minimo 1") int size,  @PathVariable Integer idAutor) {		
		Page<AutorLector> autores = servicio.listarAutoresLectores(page, size, idAutor);
		return new ResponseEntity<Page<AutorLector>>(autores, HttpStatus.OK);	
	}
	
	@PreAuthorize("hasAuthority('Autor')")
	@PostMapping("/asociarLector")
	public ResponseEntity<Autor> guardarAutorLector(@Valid @RequestBody AutorLectorDto autor) {		
		servicio.guardarAutorLector(autor);
		return new ResponseEntity<Autor>(HttpStatus.CREATED);	
	}
	
	@PreAuthorize("hasAuthority('Autor')")
	@PostMapping("/asociarLectoresLista")
	public ResponseEntity<Autor> guardarAutoresLectores(@Valid @RequestBody List<AutorLectorDto> autor) {		
		servicio.guardarAutorLector(autor);
		return new ResponseEntity<Autor>(HttpStatus.CREATED);	
	}
	
	@PreAuthorize("hasAuthority('Autor')")
	@PutMapping("/editar")
	public ResponseEntity<Autor> editarAutor(@Valid @RequestBody Autor autor) {		
		servicio.editar(autor);
		return new ResponseEntity<Autor>(autor,HttpStatus.OK);	
	}
	
	@PreAuthorize("hasAuthority('Autor')")
	@PutMapping("/editarnativo")
	public ResponseEntity<Direccion> editarDireccionNativo(@Valid @RequestBody Direccion direccion) {		
		servicio.editarNativo(direccion);
		return new ResponseEntity<Direccion>(direccion,HttpStatus.OK);	
	}
	
	@PreAuthorize("hasAuthority('Admin')")
	@DeleteMapping("/eliminar/{id}")
	public ResponseEntity<Autor> eliminarAutor(@PathVariable @Min(value = 1, message = "minimo 1") Integer id) {		
		servicio.eliminar(id);
		return new ResponseEntity<Autor>(HttpStatus.NO_CONTENT);	
	}
	
	@PreAuthorize("hasAuthority('Admin')")
	@DeleteMapping("/eliminar/id/{id}")
	public ResponseEntity<Autor> eliminarSoloAutor(@PathVariable @Min(value = 1, message = "minimo 1") Integer id) {		
		servicio.eliminarSinLibros(id);
		return new ResponseEntity<Autor>(HttpStatus.NO_CONTENT);	
	}
}

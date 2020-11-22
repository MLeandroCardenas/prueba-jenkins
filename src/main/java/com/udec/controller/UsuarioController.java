package com.udec.controller;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.udec.entity.Autor;
import com.udec.entity.Usuario;
import com.udec.service.IUsuarioService;

@RestController
@RequestMapping("/usuarios")
@Validated
public class UsuarioController {
	
	@Autowired
	private IUsuarioService servicio;
	
	@PostMapping("/guardar")
	public ResponseEntity<Autor> guardarUsuario(@Valid @RequestBody Usuario usuario) {		
		servicio.guardar(usuario);
		return new ResponseEntity<Autor>(HttpStatus.CREATED);	
	}
	
	@PreAuthorize("hasAuthority('Admin')")
	@GetMapping("/listar/{id}")
	public ResponseEntity<Usuario> listarUsuario( @PathVariable Integer id) {
		Usuario usuario = servicio.listarPorId(false, id);
		return new ResponseEntity<Usuario>(usuario, HttpStatus.OK);	
	}
	
	@PreAuthorize("hasAuthority('Admin')")
	@GetMapping("/listartodos/{lazy}/{page}/{size}")
	public ResponseEntity<Page<Usuario>> obtenerUsuarios(@PathVariable boolean lazy, @PathVariable @Min(value = 0, message = "minimo 0") int page, @PathVariable @Min(value = 1, message = "minimo 1") int size) {		
		Page<Usuario> usuarios = servicio.listarTodos(lazy, page, size);
		return new ResponseEntity<Page<Usuario>>(usuarios, HttpStatus.OK);	
	}

}

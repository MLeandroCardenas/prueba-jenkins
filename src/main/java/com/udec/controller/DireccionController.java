package com.udec.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.udec.entity.Direccion;
import com.udec.service.IDireccionService;

@RestController
@RequestMapping("/direcciones")
public class DireccionController {
	
	@Autowired
	private IDireccionService servicio;
	
	@PutMapping("/editarnativo")
	public ResponseEntity<Direccion> editarDireccionNativo(@Valid @RequestBody Direccion direccion) {		
		servicio.editarNativo(direccion);
		return new ResponseEntity<Direccion>(direccion,HttpStatus.OK);	
	}
}

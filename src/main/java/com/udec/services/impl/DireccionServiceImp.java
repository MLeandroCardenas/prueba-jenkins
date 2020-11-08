package com.udec.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.udec.entity.Direccion;
import com.udec.exception.BussinesException;
import com.udec.exception.ModelNotFoundException;
import com.udec.repository.IDireccionRepo;
import com.udec.service.IDireccionService;

@Service
public class DireccionServiceImp implements IDireccionService {
	
	@Autowired
	private IDireccionRepo repo;

	@Override
	public void editarNativo(Direccion direccion) {
		if(direccion.getId() == null)
			throw new BussinesException("El id debe ser obligatorio");
		else if(!repo.existsById(direccion.getId()))
			throw new ModelNotFoundException("Este id no existe");			
		else
			repo.editarNativo(direccion.getBarrio(), direccion.getDescripcion(), direccion.getId());
	}
}

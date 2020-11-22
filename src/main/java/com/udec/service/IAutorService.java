package com.udec.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.udec.dto.AutorLectorDto;
import com.udec.entity.Autor;
import com.udec.entity.AutorLector;
import com.udec.entity.Direccion;
import com.udec.repository.IOperacionesGenericas;
import com.udec.views.AutorView;

public interface IAutorService extends IOperacionesGenericas<Autor, Integer> {
	void eliminarSinLibros(Integer id);
	
	Page<Autor> listarAutoresConLibros(int page, int size, String nombreLibro);
	
	Page<Autor> listarAutoresConLibrosReservadas(int page, int size, String nombreLibro);
	
	Page<AutorLector> listarAutoresLectores(int page, int size, Integer idAutor);
	
	void editarNativo(Direccion direccion);
	
	Page<AutorView> vistaInfoAutores(int page, int size);
	
	AutorView vistaInfoAutor (Integer id);
	
	public void guardarAutorLector(AutorLectorDto dto);
	
	public void guardarAutorLector(List<AutorLectorDto> dto);
}

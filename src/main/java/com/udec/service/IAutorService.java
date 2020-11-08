package com.udec.service;

import org.springframework.data.domain.Page;
import com.udec.entity.Autor;
import com.udec.repository.IOperacionesGenericas;
import com.udec.views.AutorView;

public interface IAutorService extends IOperacionesGenericas<Autor, Integer> {
	void eliminarSinLibros(Integer id);
	Page<Autor> listarAutoresConLibros(int page, int size, String nombreLibro);
	Page<Autor> listarAutoresConLibrosReservadas(int page, int size, String nombreLibro);
	Page<AutorView> vistaInfoAutores(int page, int size);
}

package com.udec.services.impl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.udec.entity.Autor;
import com.udec.entity.Libro;
import com.udec.exception.BussinesException;
import com.udec.exception.ModelNotFoundException;
import com.udec.repository.IAutorRepo;
import com.udec.repository.IVistaAutorRepo;
import com.udec.service.IAutorService;
import com.udec.views.AutorView;

@Service
public class AutorServiceImp implements IAutorService {
	
	@Autowired
	private IAutorRepo repoBD;
	
	@Autowired
	private IVistaAutorRepo repoVista;
	
	public boolean validarCampos(Autor autor) {
		if(autor.getDireccion().getDescripcion() == null)
			throw new BussinesException("descripcion obligatoria");
		else if(autor.getDireccion().getDescripcion().length() < 4 || autor.getDireccion().getDescripcion().length() > 50)
			throw new BussinesException("Descripcion: Minimo 4 caracteres y maximo 50 caracteres");
		else if(autor.getDireccion().getBarrio().length() < 4 || autor.getDireccion().getBarrio().length() > 30)
			throw new BussinesException("Barrio: Minimo 4 caracteres y maximo 30 caracteres");
		else
		return true;
	}
	
	@Override
	public Page<Autor> listarAutoresConLibros(int page, int size, String nombreLibro) {
		Page<Autor> autoresConLibros = repoBD.listarAutoresConLibros(PageRequest.of(page, size), nombreLibro);
		for(Autor autor: autoresConLibros.getContent()) {
			autor.setLibros(null);
		}
		return autoresConLibros;
	}
	
	@Override
	public Page<Autor> listarAutoresConLibrosReservadas(int page, int size, String nombreLibro) {
		Page<Autor> autoresConLibros = repoBD.findBylibros_NombreIgnoreCase(PageRequest.of(page, size), nombreLibro);
		for(Autor autor: autoresConLibros.getContent()) {
			autor.setLibros(null);
		}
		return autoresConLibros;
	}
	
	@Override
	public void eliminar(Integer id) {
		if(!repoBD.existsById(id))
			throw new ModelNotFoundException("No existe un autor con el id " + id);
		repoBD.deleteById(id);
	}
	
	@Override
	public void eliminarSinLibros(Integer id) {
		Autor autor = repoBD.findById(id).orElseThrow(() -> new ModelNotFoundException("No existe un autor con el id " + id));
		if(!autor.getLibros().isEmpty())
			throw new BussinesException("¡Advertencia! el autor " 
					+ autor.getNombres() + " " + autor.getApellidos() + " tiene libros asociados");
		else
			repoBD.deleteById(id);
	}

	@Override
	public void guardar(Autor autor) {
		if(autor.getLibros() != null) {
			for(Libro libros: autor.getLibros()) {
				libros.setAutor(autor);
			}
		}
		if(autor.getDireccion() != null) {
			if(validarCampos(autor)) {
				autor.getDireccion().setAutor(autor);
				repoBD.save(autor);
			}
		}
	}
	
	@Override
	public void editar(Autor autor) {
		if(autor.getId() == null) 
			throw new BussinesException("id del autor es requerido");
		else if(repoBD.countById(autor.getId()) == 0) 
			throw new ModelNotFoundException("No existe un autor con el id " + autor.getId());
		else {
			Autor nuevoAutor = repoBD.findById(autor.getId()).orElseThrow(() -> new ModelNotFoundException("No encontrado"));
			nuevoAutor.setApellidos(autor.getApellidos());
			nuevoAutor.setNombres(autor.getNombres());
			nuevoAutor.setFechaNacimiento(autor.getFechaNacimiento());
			if(validarCampos(autor)) {
				nuevoAutor.getDireccion().setDescripcion(autor.getDireccion().getDescripcion());
				nuevoAutor.getDireccion().setBarrio(autor.getDireccion().getBarrio());
				repoBD.save(nuevoAutor);
			}
		}
	}

	@Override
	public Autor listarPorId(boolean lazy, Integer id) {
		Autor autor = repoBD.findById(id).orElseThrow(() -> new ModelNotFoundException("No encontrado"));
		if(!lazy) {
			autor.setLibros(null);
			autor.setDireccion(null);
			return autor;
		} else 
			return autor;
	}

	@Override
	public Page<Autor> listarTodos(boolean lazy, Integer page, Integer size) {
		Page<Autor> listaAutoresPaginada = repoBD.findAll(PageRequest.of(page, size, Sort.by("apellidos").ascending())); 
		if(!lazy) {
			for (Autor autor : listaAutoresPaginada.getContent()) {
				autor.setLibros(null);
				autor.setDireccion(null);
			}
			return listaAutoresPaginada;
		} else
			return listaAutoresPaginada;
	}

	@Override
	public Page<AutorView> vistaInfoAutores(int page, int size) {
		Page<AutorView> listaInfoAutores = repoVista.listarInformacionAutores(PageRequest.of(page, size, Sort.by("apellidos").ascending()));
		return listaInfoAutores;
	}
}

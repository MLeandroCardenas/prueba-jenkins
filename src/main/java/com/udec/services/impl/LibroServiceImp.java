package com.udec.services.impl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.udec.entity.Libro;
import com.udec.exception.BussinesException;
import com.udec.exception.ModelNotFoundException;
import com.udec.repository.IAutorRepo;
import com.udec.repository.ILibroRepo;
import com.udec.service.ILibroService;

@Service
public class LibroServiceImp implements ILibroService {
	
	@Autowired
	private ILibroRepo repo;
	
	@Autowired
	private IAutorRepo repoAutor;

	@Override
	public Libro listarPorId(boolean lazy, Integer id) {
		return repo.findById(id).orElseThrow(() -> new ModelNotFoundException("No encontrado"));
	}

	@Override
	public void editar(Libro libro) {
		if (libro.getId() == null)
			throw new BussinesException("id del libro es requerido");
		else if (!repo.existsById(libro.getId()))
			throw new ModelNotFoundException("No existe un libro con el id " + libro.getId());
		else {
			Libro libroEditar = repo.findById(libro.getId())
					.orElseThrow(() -> new ModelNotFoundException("Libro no encontrado"));
			if (repo.countByNombreAndAutor_Id(libroEditar.getNombre(), libroEditar.getAutor().getId()) > 1)
				throw new BussinesException("Ya existe un libro con ese nombre");
			libroEditar.setNombre(libro.getNombre());
			libroEditar.setNumeroPaginas(libro.getId());
			repo.save(libroEditar);
		}
	}

	@Override
	public void guardar(Libro libro) {
		if (libro.getAutor() != null && libro.getAutor().getId() != null) {
			if (repo.countByNombreAndAutor_Id(libro.getNombre(), libro.getAutor().getId()) > 0)
				throw new BussinesException("Ya existe un libro con el nombre ");
			if (repoAutor.countById(libro.getAutor().getId()) > 0) {
				repo.save(libro);
			} else
				throw new ModelNotFoundException("No existe un autor con el id " + libro.getAutor().getId());
		} else
			throw new BussinesException("El id del autor es requerido");
	}

	@Override
	public void eliminar(Integer id) {
		if(repo.existsById(id))
			repo.deleteById(id);
		else
			throw new ModelNotFoundException("No existe un libro con el id " + id);
	}

	@Override
	public Page<Libro> listarTodos(boolean lazy, Integer page, Integer size) {
		return repo.findAll(PageRequest.of(page, size, Sort.by("nombre").ascending()));
	}
}

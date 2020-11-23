package com.udec.services.impl;
import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.udec.entity.Alumno;
import com.udec.exception.BussinesException;
import com.udec.exception.ModelNotFoundException;
import com.udec.repository.IAlumnoRepo;
import com.udec.service.IAlumnoService;


@Service
public class AlumnoServiceImpl implements IAlumnoService {

	@Autowired
	private IAlumnoRepo repo;

	@Override
	public List<Alumno> ordenarPorNombres() {
		return repo.findAllAlumnos(Sort.by(Sort.Direction.ASC,"nombres"));
	}


	@Override
	public Page<Alumno> listarPorNombre(int page, int size, String nombre) {
		return repo.findByNombresIgnoreCase(PageRequest.of(page, size, Sort.by("edad").ascending()), nombre);
	}

	@Override
	public boolean validarFecha(Alumno alumno) {
		LocalDate fechaActual = LocalDate.now();
		int anioActual = fechaActual.getYear();
		int anioUsuaio = alumno.getFechaNacimiento().getYear();
		int resultado = anioActual - alumno.getEdad();
		if(resultado != anioUsuaio) 
			return false;
		else
			return true;
	}


	@Override
	public void guardar(Alumno entidad) {
		if(repo.findByIdentificacion(entidad.getIdentificacion()) != null)
			throw new BussinesException("Ya se encuentra registrada esta identificacion");
		else if(!validarFecha(entidad))
			throw new BussinesException("La fecha de nacimiento no corresponde a la edad digitada");
		repo.save(entidad);
	}


	@Override
	public void eliminar(Integer id) {
		if(repo.countById(id) == 0)
			throw new ModelNotFoundException("No existe un alumno con ese id");
		repo.deleteById(id);
	}


	@Override
	public void editar(Alumno alumno) {
		if(alumno.getId() == null) 
			throw new BussinesException("id del alumno es requerido");
		else if(repo.countById(alumno.getId()) == 0) 
			throw new ModelNotFoundException("No existe el alumno");
		else if(repo.validarUsuarioEditar(alumno.getIdentificacion(), alumno.getId()) > 1)
			throw new BussinesException("No se puede editar, alguien ya esta  registrado");
		else if(!validarFecha(alumno))
			throw new BussinesException("La fecha de nacimiento no corresponde a la edad digitada");
		else
			repo.save(alumno);
	}


	@Override
	public Alumno listarPorId(boolean lazy, Integer id) {
		Alumno alumno = repo.findById(id).orElseThrow(() -> new ModelNotFoundException("No encontrado"));
		return alumno;
	}

	@Override
	public Page<Alumno> listarTodos(boolean lazy, Integer page, Integer size) {
		Page<Alumno> listaAutoresPaginada = repo.findAll(PageRequest.of(page, size, Sort.by("nombres").ascending())); 
		return listaAutoresPaginada;
	}


	@Override
	public Page<Alumno> listarPorNombre2(int page, int size, String nombre) {
		// TODO Auto-generated method stub
		return null;
	}
}

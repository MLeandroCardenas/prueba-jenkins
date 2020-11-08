package com.udec.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import io.swagger.annotations.Api;

@Api(value = "/autorDto", description = "Servicios rest para la clase alumnos")
public class AutorDto implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	@NotNull(message = "nombres obigatorios")
	@Size(min = 4, max = 30, message = "Minimo 4 caracteres y maximo 30 caracteres")
	private String nombres;
	
	@NotNull(message = "Campo apellidos es obligatorio")
	@Size(min = 4, max = 30, message = "Minimo 4 caracteres y maximo 30 caracteres")
	private String apellidos;
	
	@Past(message = "La fecha tiene que ser menor a la actual")
	@NotNull(message = "Campo fecha es obligatorio")
	private LocalDate fechaNacimiento;
	
	private List<LibroDto> libros;
	
	public AutorDto() {}
	
	public AutorDto(Integer id,
			@NotNull(message = "nombres obigatorios") @Size(min = 4, max = 30, message = "Minimo 4 caracteres y maximo 30 caracteres") String nombres,
			@NotNull(message = "Campo apellidos es obligatorio") @Size(min = 4, max = 30, message = "Minimo 4 caracteres y maximo 30 caracteres") String apellidos,
			@Past(message = "La fecha tiene que ser menor a la actual") @NotNull(message = "Campo fecha es obligatorio") LocalDate fechaNacimiento) {
		super();
		this.id = id;
		this.nombres = nombres;
		this.apellidos = apellidos;
		this.fechaNacimiento = fechaNacimiento;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombres() {
		return nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public LocalDate getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(LocalDate fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public List<LibroDto> getLibros() {
		return libros;
	}

	public void setLibros(List<LibroDto> libros) {
		this.libros = libros;
	}
}

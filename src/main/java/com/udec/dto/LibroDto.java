package com.udec.dto;
import java.io.Serializable;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class LibroDto implements Serializable {

	private static final long serialVersionUID = 1L;

	
	private Integer id;
	
	@NotNull(message = "nombres obigatorios")
	@Size(min = 4, max = 30, message = "Minimo 4 caracteres y maximo 30 caracteres")
	private String nombre;
	
	@NotNull(message = "nombres obigatorios")
	@Min(value = 1, message = "Minimo 1 pagina")
	@Max(value = 1000, message = "Maximo 1000 paginas")
	private Integer numeroPaginas;
	
	private AutorDto autor;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Integer getNumeroPaginas() {
		return numeroPaginas;
	}

	public void setNumeroPaginas(Integer numeroPaginas) {
		this.numeroPaginas = numeroPaginas;
	}

	public AutorDto getAutor() {
		return autor;
	}

	public void setAutor(AutorDto autor) {
		this.autor = autor;
	}
}

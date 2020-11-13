package com.udec.dto;
import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class AutorLectorDto implements Serializable {

	private static final long serialVersionUID = 1L;

	@NotNull(message = "id del autor es requerido")
	private AutorDto autor;

	@NotNull(message = "id del lector es requerido")
	private LectorDto lector;
	
	@NotNull(message = "informacion adicional es requerida")
	@Size(min = 5, max = 30, message = "Minimo 5 y maximo 30 caracteres")
	private String infoAdicional;

	public AutorDto getAutor() {
		return autor;
	}

	public void setAutor(AutorDto autor) {
		this.autor = autor;
	}

	public LectorDto getLector() {
		return lector;
	}

	public void setLector(LectorDto lector) {
		this.lector = lector;
	}

	public String getInfoAdicional() {
		return infoAdicional;
	}

	public void setInfoAdicional(String infoAdicional) {
		this.infoAdicional = infoAdicional;
	}	
}

package com.udec.dto;

import java.io.Serializable;

public class LectorDto implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private Integer id;
	
	private String cedula;
	
	private String nombres;
	
	private String apellidos;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
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

}

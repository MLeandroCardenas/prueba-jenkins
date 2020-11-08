package com.udec.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "dirreccion")
public class Direccion {
	
	@Id
	private Integer id;
	
	@NotNull(message = "descripcion obigatoria")
	@Size(min = 4, max = 50, message = "Minimo 4 caracteres y maximo 50 caracteres")
	@Column(name = "descripcion", nullable = false)
	private String descripcion;
	
	@Size(min = 4, max = 30, message = "Minimo 4 caracteres y maximo 30 caracteres")
	@Column(name = "barrio", nullable = true)
	private String barrio;
	
	@OneToOne
	@MapsId
	private Autor autor;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getBarrio() {
		return barrio;
	}

	public void setBarrio(String barrio) {
		this.barrio = barrio;
	}

	@JsonIgnore
	public Autor getAutor() {
		return autor;
	}

	public void setAutor(Autor autor) {
		this.autor = autor;
	}
}

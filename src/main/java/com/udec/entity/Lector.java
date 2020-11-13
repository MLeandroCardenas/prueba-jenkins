package com.udec.entity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "lector")
public class Lector {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@NotNull(message = "cedula obigatoria")
	@Size(min = 4, max = 12, message = "Minimo 4 caracteres y maximo 12 caracteres")
	@Column(name = "cedula", nullable = false, unique = true)
	private String cedula;
	
	@NotNull(message = "nombres obigatorios")
	@Size(min = 4, max = 30, message = "Minimo 4 caracteres y maximo 30 caracteres")
	@Column(name = "nombres", nullable = false)
	private String nombres;
	
	@NotNull(message = "Campo apellidos es obligatorio")
	@Size(min = 4, max = 30, message = "Minimo 4 caracteres y maximo 30 caracteres")
	@Column(name = "apellidos", nullable = false)
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

package com.udec.entity;

import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

@Entity
@Table(name = "alumno")
public class Alumno {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@NotNull(message = "nombres obigatorios")
	@Size(min = 4, max = 30, message = "Minimo 4 caracteres y maximo 30 caracteres")
	@Column(name = "nombres", nullable = false)
	private String nombres;
	
	@NotNull(message = "Campo universidad es obligatorio")
	@Size(min = 3, max = 100, message = "Minimo 3 caracteres y maximo 100 caracteres")
	@Column(name = "universidad", nullable = false)
	private String universidad;
	
	@NotNull(message = "Campo edad es obligatorio")
	@Min(value = 18, message = "Minimo 18 años")
	@Max(value = 60, message = "Maximo 60 años")
	@Column(name = "edad", nullable = false)
	private Integer edad;
	
	@NotNull(message = "Campo identificacion es obligatorio")
	@Size(min = 4, max = 10, message = "minimo 4 y maximo 10 numeros")
	@Column(name = "identificacion", nullable = false, unique = true)
	private String identificacion;
	
	@NotNull(message = "Campo correo es obligatorio")
	@Email(regexp = "^[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?$", message = "No es un correo valido")
	@Column(name = "correo", nullable = false)
	private String correo;
	
	
	@Past(message = "La fecha tiene que ser menor a la actual")
	@NotNull(message = "Campo fecha es obligatorio")
	@Column(name = "fecha_nacimiento", nullable = false)
	private LocalDate fechaNacimiento;

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

	public String getUniversidad() {
		return universidad;
	}

	public void setUniversidad(String universidad) {
		this.universidad = universidad;
	}

	public Integer getEdad() {
		return edad;
	}

	public void setEdad(Integer edad) {
		this.edad = edad;
	}

	public String getIdentificacion() {
		return identificacion;
	}

	public void setIdentificacion(String identificacion) {
		this.identificacion = identificacion;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public LocalDate getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(LocalDate fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
}


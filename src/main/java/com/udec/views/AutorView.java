package com.udec.views;
import java.io.Serializable;
import java.math.BigInteger;
import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Subselect;
import org.springframework.data.annotation.Immutable;
@Entity
@Table(name = "vista_info_autores")
@Immutable
public class AutorView {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column
	private String nombres;
	
	@Column
	private String apellidos;
	
	@Column
	private LocalDate fechaNacimiento;
	
	@Column
	private String descripcion;
	
	@Column
	private String barrio;
	
	@Column
	private BigInteger libros;

}

package ar.edu.um.programacion2.servicioVentas.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


import lombok.Data;

@Data
@Entity
@Table(name = "venta")
public class Venta implements Serializable{

	private static final long serialVersionUID = 5402519510226668683L;


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "venta_id")
	private Long id;
	
	@Column(name="cliente")
	private Long cliente_id;
	
	@Column(name = "tarjeta")
	private Long tarjeta_id;
	
	@Column(name = "monto")
	private Long monto;
	
	@Column(name = "fecha")
	private Date fecha;
	
}
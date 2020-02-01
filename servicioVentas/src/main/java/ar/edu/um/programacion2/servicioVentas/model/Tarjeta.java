package ar.edu.um.programacion2.servicioVentas.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
@Entity
@Table(name = "tarjeta")
public class Tarjeta implements Serializable{


	private static final long serialVersionUID = 2976581438452388806L;


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "tarjeta_id")
	private Long id;
	
	@Column(name = "cod_seguridad")
	private Long cod_seguridad;
	
	@Column(name = "vencimiento")
	private String vencimiento;
	
	@Column(name = "monto")
	private Double monto = 5000.00;
	
	@Column(name = "numero")
	private Long numero;
	
	@Column(name = "tipo")
	@NotNull
	@Size(max = 100)
	private String tipo = "";

	@ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "cliente_id", nullable = false)
	private Cliente cliente;
	
}
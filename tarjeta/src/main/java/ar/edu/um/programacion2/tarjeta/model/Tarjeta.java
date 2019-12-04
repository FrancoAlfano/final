package ar.edu.um.programacion2.tarjeta.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
@Entity
@Table(name = "tarjeta")
public class Tarjeta implements Serializable{

	private static final long serialVersionUID = 7352918788805814920L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "tarjeta_id")
	private Long id;
	
	
	@Column(name = "numero")
	@NotNull
	@Size(max=100)
	private Long numero;
	
	@Column(name="cod_seguridad")
	@NotNull
	@Size(max=100)
	private Long cod_seguridad;
	
	@Column(name="vencimiento")
	@NotNull
	@Size(max=100)
	private Long vencimiento;
	
	@Column(name="monto_max")
	@NotNull
	@Size(max=100)
	private Double monto_max;

}

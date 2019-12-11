package ar.edu.um.programacion2.servicioLogs.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "logs")
public class Logs implements Serializable{


	private static final long serialVersionUID = 2976581438452388806L;


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "logs_id")
	private Long id;
	
	@Column(name = "id_venta")
	private Long id_venta;
	
	@Column(name = "paso")
	private String paso;
	
	@Column(name = "resultado")
	private String resultado;
	
	@Column(name = "explicacion")
	private String explicacion;
	
}
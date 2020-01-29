package ar.edu.um.programacion2.servicioTarjeta.model;

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
@Table(name = "cliente")
public class Cliente implements Serializable{

	private static final long serialVersionUID = 5402519510226668683L;


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cliente_id")
	private Long id;
	
	@Column(name="nombre")
	@NotNull
	@Size(max=100)
	private String nombre ="";
	
	@Column(name = "apellido")
	@NotNull
	@Size(max = 100)
	private String apellido = "";

	
}

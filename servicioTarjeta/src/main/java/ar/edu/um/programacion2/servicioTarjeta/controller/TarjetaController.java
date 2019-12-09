package ar.edu.um.programacion2.servicioTarjeta.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ar.edu.um.programacion2.servicioTarjeta.model.Tarjeta;
import ar.edu.um.programacion2.servicioTarjeta.service.TarjetaService;

@RestController
@RequestMapping("/tarjeta")
public class TarjetaController {
	@Autowired
	private TarjetaService service;
	

	@GetMapping("/find/{tarjetaId}")
	public ResponseEntity<Tarjeta> findById(@PathVariable Long tarjetaId){
		return new ResponseEntity<Tarjeta>(service.findById(tarjetaId), HttpStatus.CREATED);
	}
	
	@GetMapping("/check/{tarjetaId}/{monto}")
	public ResponseEntity<Tarjeta> findByIdAndMonto(@PathVariable Long tarjetaId, @PathVariable Double monto){
		return new ResponseEntity<Tarjeta>(service.findByIdAndMonto(tarjetaId, monto), HttpStatus.OK);
		
	}
	
}
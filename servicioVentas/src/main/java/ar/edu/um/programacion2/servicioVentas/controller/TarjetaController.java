package ar.edu.um.programacion2.servicioVentas.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ar.edu.um.programacion2.servicioVentas.model.Tarjeta;
import ar.edu.um.programacion2.servicioVentas.service.TarjetaService;

@RestController
@RequestMapping("/tarjeta")
public class TarjetaController {
	@Autowired
	private TarjetaService service;
	
	@GetMapping("/all")
	public ResponseEntity<List<Tarjeta>> findAll(){
		return new ResponseEntity<List<Tarjeta>>(service.findAll(), HttpStatus.OK);
	}
	
	@GetMapping("/find/{tarjetaId}")
	public ResponseEntity<Tarjeta> findById(@PathVariable Long tarjetaId){
		return new ResponseEntity<Tarjeta>(service.findById(tarjetaId), HttpStatus.OK);
	}
	
	@GetMapping("/token/{numero}")
	public ResponseEntity<Long> findByNumero(@PathVariable Long numero){
		return new ResponseEntity<Long>(service.findByNumero(numero), HttpStatus.OK);
		
	}
	
	@PostMapping("/add")
	public ResponseEntity<Tarjeta> add(@RequestBody Tarjeta tarjeta){
		return new ResponseEntity<Tarjeta>(service.add(tarjeta), HttpStatus.OK);
	}
	
	@DeleteMapping("/remove/{numero}")
	public ResponseEntity<Void> delete(@PathVariable Long numero){
		return new ResponseEntity<Void>(service.delete(numero), HttpStatus.NO_CONTENT);
	}
	
	@PutMapping("/update/{tarjetaId}")
	public ResponseEntity<Tarjeta> update(@RequestBody Tarjeta tarjeta, @PathVariable Long tarjetaId){
		return new ResponseEntity<Tarjeta>(service.update(tarjeta,tarjetaId), HttpStatus.OK);
		
	}
	
}
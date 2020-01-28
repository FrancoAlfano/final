package ar.edu.um.programacion2.servicioLogs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ar.edu.um.programacion2.servicioLogs.model.Logs;
import ar.edu.um.programacion2.servicioLogs.service.LogsService;

@RestController
@RequestMapping("/logs")
public class LogsController {
	@Autowired
	private LogsService service;
	
	@PostMapping("/tarjetaExpirada")
	public ResponseEntity<Logs> tarjetaExpirada(@RequestBody Logs log){
		return new ResponseEntity<Logs>(service.tarjetaExpirada(log), HttpStatus.OK);
	}
	
	@PostMapping("/tarjetaFound")
	public ResponseEntity<Logs> tarjetaFound(@RequestBody Logs log){		
		return new ResponseEntity<Logs>(service.tarjetaFound(log), HttpStatus.OK);
	}	

	@PostMapping("/registro")
	public ResponseEntity<Logs> add(@RequestBody Logs log){		
		return new ResponseEntity<Logs>(service.add(log), HttpStatus.OK);
	}
		
	@PostMapping("/tarjetaNotFound")
	public ResponseEntity<Logs> tarjetaNotFound(@RequestBody Logs log){
		return new ResponseEntity<Logs>(service.tarjetaNotFound(log), HttpStatus.OK);
	}
	@PostMapping("/montoSuperado")
	public ResponseEntity<Logs> montoSuperado(@RequestBody Logs log){
		return new ResponseEntity<Logs>(service.montoSuperado(log), HttpStatus.OK);
	}
	
	@PostMapping("/ventaSuccess")
	public ResponseEntity<Logs> ventaSuccess(@RequestBody Logs log){
		return new ResponseEntity<>(service.ventaSuccess(log), HttpStatus.OK);
	}
	
}
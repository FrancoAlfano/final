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

import ar.edu.um.programacion2.servicioVentas.model.Venta;
import ar.edu.um.programacion2.servicioVentas.service.VentaService;

@RestController
@RequestMapping("/venta")
public class VentaController {
	@Autowired
	private VentaService service;
	
	@GetMapping("/all")
	public ResponseEntity<List<Venta>> findAll(){
		return new ResponseEntity<List<Venta>>(service.findAll(), HttpStatus.OK);
	}
	
	@GetMapping("/find/{ventaId}")
	public ResponseEntity<Venta> findById(@PathVariable Long ventaId){
		return new ResponseEntity<Venta>(service.findById(ventaId), HttpStatus.OK);
	}

	@PostMapping("/add")
	public ResponseEntity<Object> add(@RequestBody Venta venta){
		return service.add(venta);
	}
	
	@DeleteMapping("/remove/{numero}")
	public ResponseEntity<Void> delete(@PathVariable Long numero){
		return new ResponseEntity<Void>(service.delete(numero), HttpStatus.NO_CONTENT);
	}
	
	@PutMapping("/update/{ventaId}")
	public ResponseEntity<Venta> update(@RequestBody Venta venta, @PathVariable Long ventaId){
		return new ResponseEntity<Venta>(service.update(venta,ventaId), HttpStatus.OK);
		
	}
	
}

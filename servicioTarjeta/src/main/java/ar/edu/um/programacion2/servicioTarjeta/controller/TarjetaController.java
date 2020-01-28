package ar.edu.um.programacion2.servicioTarjeta.controller;



import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ar.edu.um.programacion2.servicioTarjeta.model.Tarjeta;
import ar.edu.um.programacion2.servicioTarjeta.service.TarjetaService;

@RestController
@RequestMapping("/tarjeta")
public class TarjetaController {
	@Autowired
	private TarjetaService service;
	
	@PostMapping("/find")
	public ResponseEntity<Object> find(@RequestBody Tarjeta tarjeta_id){
		return service.find(tarjeta_id);
	}
	
	@PostMapping("/checkMonto")
	public ResponseEntity<Object> checkMonto(@RequestBody Tarjeta tarjeta){
		return service.checkMonto(tarjeta);
	}
	
	@GetMapping("/all")
	public ResponseEntity<List<Tarjeta>> findAll(){
		return new ResponseEntity<List<Tarjeta>>(service.findAll(), HttpStatus.OK);
	}

	@PostMapping("/checkVencimiento")
	public ResponseEntity<Object> checkVencimiento(@RequestBody Tarjeta tar){
		return service.checkVencimiento(tar.getId());
	}

}
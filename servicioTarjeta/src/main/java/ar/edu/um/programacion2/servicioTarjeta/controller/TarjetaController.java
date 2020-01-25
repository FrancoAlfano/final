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
	
	@PostMapping("/findTarjeta")
	public ResponseEntity<Object> find(@RequestBody Tarjeta tarjeta_id){
		return service.find(tarjeta_id);
	}
	
	@GetMapping("/all")
	public ResponseEntity<List<Tarjeta>> findAll(){
		return new ResponseEntity<List<Tarjeta>>(service.findAll(), HttpStatus.OK);
	}
	
	@PostMapping("/checkTarjeta")
	public ResponseEntity<Object> checkTarjeta (@RequestBody Tarjeta tarjeta) {
		return service.checkTarjeta(tarjeta);
	}
	

	@PostMapping("/token")
	public ResponseEntity<Object> token(@RequestBody Tarjeta tar){
		return service.token(tar.getId());
	}

	
	@PostMapping("/monto")
	public ResponseEntity<Object> monto(@RequestBody Tarjeta tar){
		return service.monto(tar.getId(), tar.getMonto());		
	}
}
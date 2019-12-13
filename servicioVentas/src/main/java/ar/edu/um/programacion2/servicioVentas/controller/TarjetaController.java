package ar.edu.um.programacion2.servicioVentas.controller;


import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import ar.edu.um.programacion2.servicioVentas.model.Tarjeta;
import ar.edu.um.programacion2.servicioVentas.service.TarjetaService;

@RestController
@RequestMapping("/tarjeta")
public class TarjetaController {
	@Autowired
	private TarjetaService service;
	
	@Autowired
	RestTemplate restTemplate;
	

	String url = "http://localhost:8081/tarjeta/find";
	
	
	@PostMapping("/test")
	public Object createProducts(@RequestBody Tarjeta tarjeta) {	
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		headers.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
		HttpEntity<Tarjeta> entity = new HttpEntity<Tarjeta>(tarjeta, headers);
		return restTemplate.postForEntity(url, entity, Object.class);
	}
	
	
	@GetMapping("/all")
	public ResponseEntity<List<Tarjeta>> findAll(){
		return new ResponseEntity<List<Tarjeta>>(service.findAll(), HttpStatus.OK);
	}
	
	@GetMapping("/find/{tarjetaId}")
	public ResponseEntity<Tarjeta> findById(@PathVariable Long tarjetaId){
		return new ResponseEntity<Tarjeta>(service.findById(tarjetaId), HttpStatus.OK);
	}
	
	@PostMapping("/token")
	public ResponseEntity<Long> findByNumero(@RequestBody Tarjeta tar){
		Long numero = tar.getNumero();
		return new ResponseEntity<Long>(service.findByNumero(numero), HttpStatus.OK);
		
	}
	
	@PostMapping("/add")
	public ResponseEntity<Object> add(@RequestBody Tarjeta tarjeta){
		Tarjeta tar2 = service.add(tarjeta);
		return new ResponseEntity<Object>(tar2.getId(), HttpStatus.OK);
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

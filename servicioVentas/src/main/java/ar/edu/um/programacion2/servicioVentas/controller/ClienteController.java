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

import ar.edu.um.programacion2.servicioVentas.model.Cliente;
import ar.edu.um.programacion2.servicioVentas.service.ClienteService;

@RestController
@RequestMapping("/cliente")
public class ClienteController {
	@Autowired
	private ClienteService service;
	
	@GetMapping("/all")
	public ResponseEntity<List<Cliente>> findAll(){
		return new ResponseEntity<List<Cliente>>(service.findAll(), HttpStatus.OK);
	}
	
	@GetMapping("/find/{clienteId}")
	public ResponseEntity<Cliente> findById(@PathVariable Long clienteId){
		return new ResponseEntity<Cliente>(service.findById(clienteId), HttpStatus.OK);
	}
	
	@PostMapping("/token")
	public ResponseEntity<Object> token(@RequestBody Cliente cliente){
		String nombre = cliente.getNombre();
		String apellido = cliente.getApellido();
	
		return new ResponseEntity<Object>(service.findByNombreAndApellido(nombre, apellido), HttpStatus.OK);
		
	}
	
	@PostMapping("/add")
	public ResponseEntity<Object> add(@RequestBody Cliente cliente){
		Cliente cli = service.add(cliente);
		Long id_cliente = cli.getId();
		return new ResponseEntity<Object>(id_cliente, HttpStatus.OK);
	}
	
	@DeleteMapping("/remove/{numero}")
	public ResponseEntity<Void> delete(@PathVariable Long numero){
		return new ResponseEntity<Void>(service.delete(numero), HttpStatus.NO_CONTENT);
	}
	
	@PutMapping("/update/{clienteId}")
	public ResponseEntity<Cliente> update(@RequestBody Cliente cliente, @PathVariable Long clienteId){
		return new ResponseEntity<Cliente>(service.update(cliente,clienteId), HttpStatus.OK);
		
	}
	
}

package ar.edu.um.programacion2.servicioVentas.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import ar.edu.um.programacion2.servicioVentas.model.Cliente;
import ar.edu.um.programacion2.servicioVentas.service.ClienteService;

@Controller
@RequestMapping("/cliente")
public class ClienteController {
	@Autowired
	private ClienteService service;
	
	@RequestMapping()
	public String getAllClientes(Model model) {
		List<Cliente> list = service.findAll();
		model.addAttribute("clientes", list);
		return "cliente-all";
	}
	
	@PostMapping("/createCliente")
	public String createCliente(Cliente cliente) {
		service.add(cliente);
		return "redirect:/cliente/";
	}
	
	@RequestMapping("/agregarCliente")
	public String viewAgregarCliente(Model model) {
		model.addAttribute("cliente", new Cliente());
		return "cliente-add";
	}
	
	@RequestMapping("/goToUpdate/{id}")
	public String updateOrCreate(Model model, @PathVariable("id") Long id){
		Cliente cliente = service.findById(id);
		model.addAttribute("cliente", cliente);
		return "cliente-update";
	}
	
	@PostMapping("/update")
	public String update(Cliente cliente){
		service.update(cliente,cliente.getId());
		return "redirect:/cliente/";
		
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
	
	@RequestMapping("/remove/{numero}")
	public String delete(@PathVariable Long numero){
		service.delete(numero);
		return "redirect:/cliente/";
	}
	
	/*
	@GetMapping("/all")
	public ResponseEntity<List<Cliente>> findAll(){
		return new ResponseEntity<List<Cliente>>(service.findAll(), HttpStatus.OK);
	}
	
	@PostMapping("/add")
	public ResponseEntity<Object> add(@RequestBody Cliente cliente){
		return new ResponseEntity<Object>(service.add(cliente), HttpStatus.CREATED);
	}
	
	@DeleteMapping("/remove/{numero}")
	public ResponseEntity<Void> delete(@PathVariable Long numero){
		return new ResponseEntity<Void>(service.delete(numero), HttpStatus.NO_CONTENT);
	}
	
	@PutMapping("/update/{clienteId}")
	public ResponseEntity<Cliente> update(@RequestBody Cliente cliente, @PathVariable Long clienteId){
		return new ResponseEntity<Cliente>(service.update(cliente,clienteId), HttpStatus.OK);
		
	}
	*/
}

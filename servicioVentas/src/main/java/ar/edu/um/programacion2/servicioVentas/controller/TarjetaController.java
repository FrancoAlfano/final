package ar.edu.um.programacion2.servicioVentas.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import ar.edu.um.programacion2.servicioVentas.model.Cliente;
import ar.edu.um.programacion2.servicioVentas.model.Tarjeta;
import ar.edu.um.programacion2.servicioVentas.service.TarjetaService;

@Controller
@RequestMapping("/tarjeta")
public class TarjetaController {
	@Autowired
	private TarjetaService service;

	@RequestMapping()
	public String getAllTarjetas(Model model) {
		List<Tarjeta> list = service.findAll();
		model.addAttribute("tarjetas", list);
		return "tarjeta-all";
	}

	@GetMapping("/find/{tarjetaId}")
	public ResponseEntity<Tarjeta> findById(@PathVariable Long tarjetaId){
		return new ResponseEntity<Tarjeta>(service.findById(tarjetaId), HttpStatus.OK);
	}
	
	@PostMapping("/token")
	public Long findByNumero(@RequestBody Tarjeta tar){
		Cliente cliente = tar.getCliente();
		return service.findByNumeroAndClienteId(tar.getNumero(), cliente.getId());
	}
	
	@PostMapping("/createTarjeta")
	public String createTarjeta(Tarjeta tarjeta) {
		service.add(tarjeta);
		return "redirect:/tarjeta/";
	}
	
	@RequestMapping("/agregarTarjeta")
	public String viewAgregarTarjeta(Model model) {
		model.addAttribute("tarjeta", new Tarjeta());
		return "tarjeta-add";
	}
	
	@RequestMapping("/goToUpdate/{id}")
	public String updateOrCreate(Model model, @PathVariable("id") Long id){
		Tarjeta tarjeta = service.findById(id);
		model.addAttribute("tarjeta", tarjeta);
		return "tarjeta-update";
	}
	
	@PostMapping("/update")
	public String update(Tarjeta tarjeta){
		service.update(tarjeta,tarjeta.getId());
		return "redirect:/tarjeta/";
		
	}
	
	@RequestMapping("/remove/{tarjeta_id}")
	public String delete(@PathVariable Long tarjeta_id){
		service.delete(tarjeta_id);
		return "redirect:/tarjeta/";
	}
	
	
	@DeleteMapping("/remove/{numero}")
	public ResponseEntity<Void> deletePost(@PathVariable Long numero){
		return new ResponseEntity<Void>(service.delete(numero), HttpStatus.NO_CONTENT);
	}
	
	
	/*
	 * 
	
	@GetMapping("/all")
	public ResponseEntity<List<Tarjeta>> findAll(){
		return new ResponseEntity<List<Tarjeta>>(service.findAll(), HttpStatus.OK);
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
	*/
}

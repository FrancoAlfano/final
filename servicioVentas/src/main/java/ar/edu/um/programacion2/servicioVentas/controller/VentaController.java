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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import ar.edu.um.programacion2.servicioVentas.model.Venta;
import ar.edu.um.programacion2.servicioVentas.service.VentaService;

@Controller
@RequestMapping("/venta")
public class VentaController {
	@Autowired
	private VentaService service;
	
	@GetMapping("/all")
	public ResponseEntity<List<Venta>> findAll(){
		return new ResponseEntity<List<Venta>>(service.findAll(), HttpStatus.OK);
	}
	
	@PostMapping("/add")
	public ResponseEntity<Object> add(@RequestBody Venta venta){
		return service.add(venta);
	}
	
	@DeleteMapping("/postmanDelete/{numero}")
	public ResponseEntity<Void> postmanDelete(@PathVariable Long numero){
		return new ResponseEntity<Void>(service.delete(numero), HttpStatus.OK);
	}

	@GetMapping("/find/{ventaId}")
	public ResponseEntity<Venta> findById(@PathVariable Long ventaId){
		return new ResponseEntity<Venta>(service.findById(ventaId), HttpStatus.OK);
	}
	
	@PutMapping("/postmanUpdate/{ventaId}")
	public ResponseEntity<Venta> postmanUpdate(@RequestBody Venta venta, @PathVariable Long ventaId){
		return new ResponseEntity<Venta>(service.update(venta,ventaId), HttpStatus.OK);
	}
		
	@RequestMapping()
	public String getAllVentas(Model model) {
		List<Venta> list = service.findAll();
		model.addAttribute("ventas", list);
		return "venta-all";
	}
	
	@RequestMapping("/remove/{numero}")
	public String delete(@PathVariable Long numero){
		service.delete(numero);
		return "redirect:/venta/";
	}
	
	@RequestMapping("/agregarVenta")
	public String viewAgregarVenta(Model model) {
		model.addAttribute("venta", new Venta());
		return "venta-add";
	}

	@RequestMapping("/goToUpdate/{id}")
	public String updateOrCreate(Model model, @PathVariable("id") Long id){
		Venta venta = service.findById(id);
		model.addAttribute("venta", venta);
		return "venta-update";
	}
	
	@RequestMapping("/createVenta")
	public String createVenta(Venta venta) {
		service.add(venta);
		return "redirect:/venta/";
	}
	
	@PostMapping("/update")
	public String update(Venta venta){
		service.update(venta,venta.getId());
		return "redirect:/venta/";		
	}
	
}

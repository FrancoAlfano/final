package ar.edu.um.programacion2.servicioTarjeta.controller;



import java.util.Date;
import java.util.List;

import org.json.JSONObject;
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
	
	
	@GetMapping("/all")
	public ResponseEntity<List<Tarjeta>> findAll(){
		return new ResponseEntity<List<Tarjeta>>(service.findAll(), HttpStatus.OK);
	}
	

	@PostMapping("/find")
	public ResponseEntity<Object> findById(@RequestBody Tarjeta tar){
		
		JSONObject jo = new JSONObject();
		JSONObject jo2 = new JSONObject();

		Long tarjetaId = tar.getId();
		
		tar = service.findById(tarjetaId);
		
		if (tar != null) {
			Date ven = tar.getVencimiento();
			Date today = new Date();
			
			if (ven.before(today)) {
				jo2.put("codError", "21");
				jo2.put("error", "Tarjeta Expirada");
				return new ResponseEntity<Object>(jo2.toString(), HttpStatus.FORBIDDEN);
				
			}else {
				return new ResponseEntity<Object>(HttpStatus.CREATED);
			}
			
		}else {
			jo.put("codError", "20");
			jo.put("error", "No existe tarjeta");
			return new ResponseEntity<Object>(jo.toString(), HttpStatus.FORBIDDEN);
		}		
	}
	
	@PostMapping("/monto")
	public ResponseEntity<Object> findByIdAndMonto(@RequestBody Tarjeta tar){
		Long tarjetaId = tar.getId();
		Double monto = tar.getMonto();
		JSONObject jo = new JSONObject();
		
		tar = service.findById(tarjetaId);
		
		if (tar != null && monto<=tar.getMonto()) {
			return new ResponseEntity<Object>(HttpStatus.CREATED);
			
		} else {
			
			jo.put("codError", "30");
			jo.put("error", "Monto máximo de venta superado");
			return new ResponseEntity<Object>(jo.toString(), HttpStatus.FORBIDDEN);
		}
		
	}
	
}
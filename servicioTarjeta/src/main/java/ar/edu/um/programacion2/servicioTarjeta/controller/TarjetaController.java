package ar.edu.um.programacion2.servicioTarjeta.controller;



import java.util.Date;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import ar.edu.um.programacion2.servicioTarjeta.model.Tarjeta;
import ar.edu.um.programacion2.servicioTarjeta.service.TarjetaService;

@RestController
@RequestMapping("/tarjeta")
public class TarjetaController {
	@Autowired
	private TarjetaService service;
	

	@GetMapping("/find/{tarjetaId}")
	public ResponseEntity<Object> findById(@PathVariable Long tarjetaId){
		JSONObject jo = new JSONObject();
		JSONObject jo2 = new JSONObject();
		Tarjeta tar = new Tarjeta();
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
			return new ResponseEntity<Object>(jo, HttpStatus.FORBIDDEN);
		}
		
	}
	
	@GetMapping("/check/{tarjetaId}/{monto}")
	public ResponseEntity<Tarjeta> findByIdAndMonto(@PathVariable Long tarjetaId, @PathVariable Double monto){
		return new ResponseEntity<Tarjeta>(service.findByIdAndMonto(tarjetaId, monto), HttpStatus.OK);
		
	}
	
}
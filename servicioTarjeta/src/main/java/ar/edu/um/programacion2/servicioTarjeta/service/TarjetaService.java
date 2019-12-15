package ar.edu.um.programacion2.servicioTarjeta.service;

import java.util.Date;
import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import ar.edu.um.programacion2.servicioTarjeta.exception.TarjetaNotFoundException;
import ar.edu.um.programacion2.servicioTarjeta.model.Tarjeta;
import ar.edu.um.programacion2.servicioTarjeta.repository.ITarjetaRepository;

@Service
public class TarjetaService {
	@Autowired
	private ITarjetaRepository repository;
	
	public Tarjeta check(Tarjeta tarjeta) {
		return repository.findById(tarjeta.getId()).orElseThrow(null);
	}
	
	public List<Tarjeta> findAll() {		
		return repository.findAll();
	}

	public ResponseEntity<Object> token(Long tarjetaId) {		
		JSONObject jo = new JSONObject();
		JSONObject jo2 = new JSONObject();		
		Tarjeta tar = repository.findById(tarjetaId).orElseThrow(()-> new TarjetaNotFoundException(tarjetaId));			
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

	public ResponseEntity<Object> monto(Long tarjetaId, Double monto) {
		Tarjeta tar = repository.findByIdAndMonto(tarjetaId, monto);
		JSONObject jo = new JSONObject();		
		if (tar != null && monto<5000 && monto<=tar.getMonto()) {
			return new ResponseEntity<Object>(HttpStatus.CREATED);			
		} else {			
			jo.put("codError", "30");
			jo.put("error", "Monto máximo de venta superado");
			return new ResponseEntity<Object>(jo.toString(), HttpStatus.FORBIDDEN);
		}
	}

	
	
}
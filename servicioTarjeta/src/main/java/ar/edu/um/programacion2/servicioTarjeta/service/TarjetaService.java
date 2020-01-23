package ar.edu.um.programacion2.servicioTarjeta.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import ar.edu.um.programacion2.servicioTarjeta.exception.TarjetaNotFoundException;
import ar.edu.um.programacion2.servicioTarjeta.model.Logs;
import ar.edu.um.programacion2.servicioTarjeta.model.Tarjeta;
import ar.edu.um.programacion2.servicioTarjeta.repository.ITarjetaRepository;

@Service
public class TarjetaService {
	@Autowired
	private ITarjetaRepository repository;
	
	@Autowired
	RestTemplate restTemplate;
	
	public ResponseEntity<Object> find(Long tarjeta_id) {
		Optional<Tarjeta> tar;
		Tarjeta tarjeta = new Tarjeta();
		try {
			tar = repository.findById(tarjeta_id);
		} catch (EntityNotFoundException e){
			throw new TarjetaNotFoundException(tarjeta_id);
		}
		return new ResponseEntity<Object>(tar, HttpStatus.OK);
	}
	
	public Optional<Tarjeta> findById(Long tarjeta_id) {
		return repository.findById(tarjeta_id);
	}
	
	public ResponseEntity<Object> checkTarjeta(Tarjeta t){
		Double monto = t.getMonto();
		String logSuccess = "http://localhost:8082/logs/tarjetaFound";			
		String logFailure = "http://localhost:8082/logs/tarjetaNotFound";
		String logMontoSuperado = "http://localhost:8082/logs/montoSuperado";
		Optional<Tarjeta> tarjeta = findById(t.getId());
		Logs log = new Logs();
		Tarjeta tar = tarjeta.get();
		if (tarjeta.isPresent() == true) {
			if (monto <= 5000 && monto<= tar.getMonto()) {
				restTemplate.postForEntity(logSuccess, log, Object.class);
				return new ResponseEntity<Object>(tar, HttpStatus.OK);				
			}else {
				restTemplate.postForEntity(logMontoSuperado, log, Object.class);
				return null;
			}
		}else {
			restTemplate.postForEntity(logFailure, log, Object.class);
			return null;
		}
	}
	
	public List<Tarjeta> findAll() {
		return repository.findAll();
	}

	public ResponseEntity<Object> token(Long tarjetaId) {		
		JSONObject jo = new JSONObject();
		JSONObject jo2 = new JSONObject();		
		Optional<Tarjeta> tarjeta = repository.findById(tarjetaId);			
		if (tarjeta.isPresent() == true) {
			Tarjeta tar = tarjeta.get();
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
package ar.edu.um.programacion2.servicioTarjeta.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ar.edu.um.programacion2.servicioTarjeta.exception.TarjetaNotFoundException;
import ar.edu.um.programacion2.servicioTarjeta.model.Cliente;
import ar.edu.um.programacion2.servicioTarjeta.model.Tarjeta;
import ar.edu.um.programacion2.servicioTarjeta.repository.ITarjetaRepository;

@Service
public class TarjetaService {
	@Autowired
	private ITarjetaRepository repository;
	
	@Autowired
	RestTemplate restTemplate;

	public ResponseEntity<Object> find(Tarjeta t){
		Long tarjeta_id = t.getId();
		Cliente cliente = t.getCliente();
		Long cliente_id = cliente.getId();
		Tarjeta tar;
		
		tar = repository.findByIdAndClienteId(tarjeta_id, cliente_id).orElseThrow(()-> new TarjetaNotFoundException(tarjeta_id));
		return new ResponseEntity<Object>(tar, HttpStatus.OK);	
	}
	
	public Tarjeta findById(Long tarjeta_id) {
		return repository.findById(tarjeta_id).orElseThrow(()-> new TarjetaNotFoundException(tarjeta_id));
	}
		
	public ResponseEntity<Object> checkMonto(Tarjeta t){
		Double monto = t.getMonto();
		Optional<Tarjeta> tarjeta = repository.findById(t.getId());
		Tarjeta tar = tarjeta.get();
		if (monto <= 5000) {
			return new ResponseEntity<Object>(tar, HttpStatus.OK);
		}else if (monto <= tar.getMonto()) {
			return new ResponseEntity<Object>(tar, HttpStatus.OK);
			}
		return null;
		}

	
	public List<Tarjeta> findAll() {
		return repository.findAll();
	}

	public ResponseEntity<Object> checkVencimiento(Long tarjetaId) {		
		Optional<Tarjeta> tarjeta = repository.findById(tarjetaId);
		Tarjeta tar = tarjeta.get();
		Date ven = tar.getVencimiento();
		Date today = new Date();			
		if (ven.before(today)) {
			return null;				
		}else {
			return new ResponseEntity<Object>(tar, HttpStatus.CREATED);
		}			
	}

	
}
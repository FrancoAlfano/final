package ar.edu.um.programacion2.servicioVentas.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ar.edu.um.programacion2.servicioVentas.exception.TarjetaNotFoundException;
import ar.edu.um.programacion2.servicioVentas.model.Tarjeta;
import ar.edu.um.programacion2.servicioVentas.repository.ITarjetaRepository;

@Service
public class TarjetaService {
	@Autowired
	private ITarjetaRepository repository;
	
	
	public List<Tarjeta> findAll() {		
		return repository.findAll();
	}

	public Tarjeta add(Tarjeta tarjeta) {
		repository.save(tarjeta);
		return tarjeta;
	}

	public Tarjeta findById(Long tarjetaId) {
		return repository.findById(tarjetaId).orElseThrow(()-> new TarjetaNotFoundException(tarjetaId));
	}
	
	public ResponseEntity<Long> findByNumeroAndClienteId(Long numero, Long cliente_id) {
		Tarjeta tar = repository.findByNumeroAndClienteId(numero, cliente_id);
		if (tar == null) {
			return new ResponseEntity<Long>(0l, HttpStatus.NOT_FOUND);
		}
		Long tarjeta_id = tar.getId();
		return new ResponseEntity<Long>(tarjeta_id, HttpStatus.OK);
	}
	
	public Void delete(Long tarjetaId) {
		repository.deleteById(tarjetaId);
		return null;
	}

	public Tarjeta update(Tarjeta newtarjeta, Long tarjetaId) {		
		return repository.findById(tarjetaId).map(tarjeta -> {
			tarjeta.setCod_seguridad(newtarjeta.getCod_seguridad());
			tarjeta.setVencimiento(newtarjeta.getVencimiento());
			tarjeta.setMonto(newtarjeta.getMonto());
			tarjeta.setNumero(newtarjeta.getNumero());
			tarjeta.setTipo(newtarjeta.getTipo());
			return repository.save(tarjeta);
		}).orElseThrow(() -> new TarjetaNotFoundException(tarjetaId));
	}
}
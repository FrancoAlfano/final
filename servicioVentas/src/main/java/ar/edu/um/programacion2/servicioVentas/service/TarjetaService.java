package ar.edu.um.programacion2.servicioVentas.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
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
	
	public Long findByNumeroAndClienteId(Long numero, Long cliente_id) {
		Tarjeta tar = repository.findByNumeroAndClienteId(numero, cliente_id);
		if (tar == null) {
			return 0l;
		}
		Long tarjeta_id = tar.getId();
		return tarjeta_id;
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
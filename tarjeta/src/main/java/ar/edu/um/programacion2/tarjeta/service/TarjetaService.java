package ar.edu.um.programacion2.tarjeta.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.um.programacion2.tarjeta.exception.TarjetaNotFoundException;
import ar.edu.um.programacion2.tarjeta.model.Tarjeta;
import ar.edu.um.programacion2.tarjeta.repository.ITarjetaRepository;

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

	public Void delete(Long tarjetaId) {
		repository.deleteById(tarjetaId);
		return null;
	}

	public Tarjeta update(Tarjeta newtarjeta, Long tarjetaId) {
		
		return repository.findById(tarjetaId).map(tarjeta -> {
			tarjeta.setCod_seguridad(newtarjeta.getCod_seguridad());
			tarjeta.setId(newtarjeta.getId());
			tarjeta.setMonto_max(newtarjeta.getMonto_max());
			tarjeta.setNumero(newtarjeta.getNumero());
			tarjeta.setVencimiento(newtarjeta.getVencimiento());
			return repository.save(tarjeta);
		}).orElseThrow(() -> new TarjetaNotFoundException(tarjetaId));
	}
}
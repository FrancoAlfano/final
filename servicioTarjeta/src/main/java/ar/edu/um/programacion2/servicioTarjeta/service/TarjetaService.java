package ar.edu.um.programacion2.servicioTarjeta.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.um.programacion2.servicioTarjeta.exception.TarjetaNotFoundException;
import ar.edu.um.programacion2.servicioTarjeta.model.Tarjeta;
import ar.edu.um.programacion2.servicioTarjeta.repository.ITarjetaRepository;

@Service
public class TarjetaService {
	@Autowired
	private ITarjetaRepository repository;


	public Tarjeta findById(Long tarjetaId) {
		
		return repository.findById(tarjetaId).orElseThrow(()-> new TarjetaNotFoundException(tarjetaId));
	}


	public Tarjeta findByIdAndMonto(Long tarjetaId, Double monto) {
		return repository.findByIdAndMonto(tarjetaId, monto);
	}
}
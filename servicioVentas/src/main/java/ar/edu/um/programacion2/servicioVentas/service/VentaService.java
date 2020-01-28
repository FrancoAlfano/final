package ar.edu.um.programacion2.servicioVentas.service;

import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import ar.edu.um.programacion2.servicioVentas.exception.TarjetaNotFoundException;
import ar.edu.um.programacion2.servicioVentas.exception.VentaNotFoundException;
import ar.edu.um.programacion2.servicioVentas.model.Logs;
import ar.edu.um.programacion2.servicioVentas.model.Tarjeta;
import ar.edu.um.programacion2.servicioVentas.model.Venta;
import ar.edu.um.programacion2.servicioVentas.repository.IVentaRepository;
import javassist.NotFoundException;

@Service
public class VentaService {
	@Autowired
	private IVentaRepository repository;
	
	@Autowired
	RestTemplate restTemplate;

	public List<Venta> findAll() {		
		return repository.findAll();
	}
	
	/*
	ahora tengo que cambiar el try catch a servicio Ventas para que el llame a todo lo que se tenga que llamar, en vez de 
	nestearlo, y ahi borrar lo unused code.
	
	
	*/

	public ResponseEntity<Object> add(Venta venta) {		
		//sends to servicioTarjeta -> TarjetaController
		String findTarjeta = "http://localhost:8081/tarjeta/find";
		String checkTarjeta = "http://localhost:8081/tarjeta/checkTarjeta";
		Tarjeta tar = new Tarjeta();
		Logs log = new Logs();
		tar.setId(venta.getTarjeta_id());
		tar.setMonto(venta.getMonto());
		try {
			ResponseEntity<Object> re1 = restTemplate.postForEntity(findTarjeta, tar, Object.class);
		}catch (HttpClientErrorException e) {
			return new ResponseEntity<Object>(HttpStatus.NOT_FOUND);
		}
		System.out.println("WE ARE HEERRRRRREEEEEEEEE 2");

		ResponseEntity<Object> re = restTemplate.postForEntity(checkTarjeta, tar, Object.class);
		if (re.getBody() == null) {
			return null;
		}else {
			Venta v = repository.save(venta);
			log.setId_venta(v.getId());
			String ventaSuccess = "http://localhost:8082/logs/ventaSuccess";
			restTemplate.postForEntity(ventaSuccess, log, Object.class);
			return new ResponseEntity<Object>(v, HttpStatus.CREATED);
		}
	}
	
	
	public Venta findById(Long ventaId) {		
		return repository.findById(ventaId).orElseThrow(()-> new VentaNotFoundException(ventaId));
	}

	public Void delete(Long ventaId) {
		repository.deleteById(ventaId);
		return null;
	}

	public Venta update(Venta newventa, Long ventaId) {		
		return repository.findById(ventaId).map(venta -> {
			venta.setId(ventaId);
			venta.setCliente_id(newventa.getCliente_id());
			venta.setTarjeta_id(newventa.getTarjeta_id());
			venta.setMonto(newventa.getMonto());
			venta.setFecha(newventa.getFecha());
			return repository.save(venta);
		}).orElseThrow(() -> new VentaNotFoundException(ventaId));
	}



}
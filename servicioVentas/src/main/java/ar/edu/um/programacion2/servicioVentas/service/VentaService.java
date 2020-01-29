package ar.edu.um.programacion2.servicioVentas.service;

import java.util.Calendar;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;
import ar.edu.um.programacion2.servicioVentas.exception.VentaNotFoundException;
import ar.edu.um.programacion2.servicioVentas.model.Cliente;
import ar.edu.um.programacion2.servicioVentas.model.Logs;
import ar.edu.um.programacion2.servicioVentas.model.Tarjeta;
import ar.edu.um.programacion2.servicioVentas.model.Venta;
import ar.edu.um.programacion2.servicioVentas.repository.IVentaRepository;

@Service
public class VentaService {
	@Autowired
	private IVentaRepository repository;
	
	@Autowired
	RestTemplate restTemplate;

	public List<Venta> findAll() {		
		return repository.findAll();
	}

	public ResponseEntity<Object> add(Venta venta) {		
		//sends to servicioTarjeta -> TarjetaController
		String findTarjeta = "http://localhost:8081/tarjeta/find";
		String checkVencimiento = "http://localhost:8081/tarjeta/checkVencimiento";
		String checkMonto = "http://localhost:8081/tarjeta/checkMonto";
		String tarjetaFound = "http://localhost:8082/logs/tarjetaFound";
		String logFailure = "http://localhost:8082/logs/tarjetaNotFound";
		String logMontoSuperado = "http://localhost:8082/logs/montoSuperado";
		String logTarjetaVencida = "http://localhost:8082/logs/tarjetaExpirada";
		
		Calendar fecha = Calendar.getInstance();
		Tarjeta tar = new Tarjeta();
		Cliente cliente = new Cliente();
		cliente.setId(venta.getCliente_id());
		Logs log = new Logs();
		venta.setFecha(fecha.getTime());
		tar.setId(venta.getTarjeta_id());
		tar.setMonto(venta.getMonto());
		tar.setCliente(cliente);
		
		System.out.println("EN VS TENEMOS: TARJETA: "+tar+" Y EL ID DEL CLIENTE: "+cliente);
			
		try {
			restTemplate.postForEntity(findTarjeta, tar, Object.class);
			restTemplate.postForEntity(tarjetaFound, log, Object.class);
		}catch (HttpServerErrorException e) {
			ResponseEntity<Object> reTarjetaNotFound = restTemplate.postForEntity(logFailure, log, Object.class);
			return new ResponseEntity<Object>(reTarjetaNotFound.getBody(), HttpStatus.NOT_FOUND);
		}
		
		ResponseEntity<Object> reTarVencimiento = restTemplate.postForEntity(checkVencimiento, tar, Object.class);
		if (reTarVencimiento.getBody() == null) {
			return restTemplate.postForEntity(logTarjetaVencida, log, Object.class);
		}
		
		ResponseEntity<Object> reTarjeta = restTemplate.postForEntity(checkMonto, tar, Object.class);
		if (reTarjeta.getBody() == null) {
			ResponseEntity<Object> reLogSup = restTemplate.postForEntity(logMontoSuperado, log, Object.class);
			return new ResponseEntity<>(reLogSup.getBody(), HttpStatus.FORBIDDEN);
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
package ar.edu.um.programacion2.servicioVentas.service;

import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ar.edu.um.programacion2.servicioVentas.exception.VentaNotFoundException;
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

	public Venta add(Venta venta) {
		String url1 = "http://localhost:8081/tarjeta/check";
		Tarjeta tar = new Tarjeta();
		tar.setId(venta.getTarjeta_id());
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<Object> tarjetaEntity = new HttpEntity<Object>(tar,headers);
		ResponseEntity<Object> result1 = restTemplate.exchange(url1, HttpMethod.POST, tarjetaEntity, Object.class);
		Logs log = new Logs();
		Venta v = repository.save(venta);
		log.setId_venta(v.getId());
		
		if (result1 == null) {
			String url2 = "http://localhost:8082/logs/notfound";
			HttpEntity<Logs> notfoundEntity = new HttpEntity<Logs>(log,headers);
			restTemplate.exchange(url2, HttpMethod.POST, notfoundEntity, Logs.class);
			return null;
		}else {
			String url3 = "http://localhost:8082/logs/ventaSuccess";
			HttpEntity<Logs> successEntity = new HttpEntity<Logs>(log,headers);
			restTemplate.exchange(url3, HttpMethod.POST, successEntity, Logs.class);
		}
		return v;
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
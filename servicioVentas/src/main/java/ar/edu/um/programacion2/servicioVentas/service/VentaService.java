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
	

	String url = "http://localhost:8082/logs/registro";

	
	public ResponseEntity<Object> addLog(Venta venta){
		Logs log = new Logs();
		log.setId_venta(venta.getId());
		log.setPaso("agregar venta");
		log.setResultado("OK");
		log.setExplicacion("Tarjeta agregada exitosamente");		
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<Object> entity = new HttpEntity<Object>(log,headers);
		return restTemplate.exchange(url, HttpMethod.POST, entity, Object.class);
	}

	public List<Venta> findAll() {		
		return repository.findAll();
	}
	
	public ResponseEntity<Object> checkMonto(Tarjeta tarjeta){
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<Object> entity = new HttpEntity<Object>(tarjeta,headers);
		return restTemplate.exchange(url, HttpMethod.POST, entity, Object.class);
	}
	

	public Venta add(Venta venta) {
		String url1 = "http://localhost:8081/tarjeta/check";
		Tarjeta tar = new Tarjeta();
		tar.setId(venta.getTarjeta_id());
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<Object> tarjetaEntity = new HttpEntity<Object>(tar,headers);
		ResponseEntity<Tarjeta> result = restTemplate.exchange(url1, HttpMethod.POST, tarjetaEntity, Tarjeta.class);
		
		if (result == null) {
			String url2 = "http://localhost:8082/logs/notfound";
			Logs log = new Logs();
			log.setId_venta(venta.getId());
			HttpEntity<Logs> logEntity = new HttpEntity<Logs>(log,headers);
			restTemplate.exchange(url2, HttpMethod.POST, logEntity, Logs.class);
		}else {
			addLog(venta);
		}
		
		if (repository.save(venta) == null) {
			return null;
		}else {
			addLog(venta);
			return venta;
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
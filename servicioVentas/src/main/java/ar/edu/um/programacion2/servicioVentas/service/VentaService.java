package ar.edu.um.programacion2.servicioVentas.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
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
		
		//sends to servicioTarjeta TarjetaController
		String checkTarjeta = "http://localhost:8081/tarjeta/checkTarjeta";
		Tarjeta tar = new Tarjeta();
		Logs log = new Logs();
		tar.setId(venta.getTarjeta_id());
		tar.setMonto(venta.getMonto());
		ResponseEntity<Object> re = restTemplate.postForEntity(checkTarjeta, tar, Object.class);
		if (re.getBody() == null) {
			return null;
		}else {
			Venta v = repository.save(venta);
			log.setId_venta(v.getId());
			String ventaSuccess = "http://localhost:8082/logs/ventaSuccess";
			restTemplate.postForEntity(ventaSuccess, log, Object.class);
			return v;
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
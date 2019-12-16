package ar.edu.um.programacion2.servicioLogs.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ar.edu.um.programacion2.servicioLogs.model.Logs;
import ar.edu.um.programacion2.servicioLogs.repository.ILogsRepository;

@Service
public class LogsService {
	@Autowired
	private ILogsRepository repository;

	public Logs add(Logs log) {
		repository.save(log);
		return log;
	}
	
	public Logs tarjetaNotFound(Logs log) {
		log.setExplicacion("tarjeta no existe");
		log.setPaso("checkeo de tarjeta");
		log.setResultado("fallo");
		return repository.save(log);		
	}
	
	public Logs ventaSuccess(Logs log) {
		log.setExplicacion("Venta exitosa");
		log.setPaso("Venta");
		log.setResultado("OK");
		return repository.save(log);
	}
}
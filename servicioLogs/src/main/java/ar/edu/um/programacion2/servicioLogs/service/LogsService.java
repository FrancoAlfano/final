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
}
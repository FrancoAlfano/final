package ar.edu.um.programacion2.servicioLogs.repository;



import org.springframework.data.jpa.repository.JpaRepository;

import ar.edu.um.programacion2.servicioLogs.model.Logs;



public interface ILogsRepository extends JpaRepository<Logs, Long> {
}

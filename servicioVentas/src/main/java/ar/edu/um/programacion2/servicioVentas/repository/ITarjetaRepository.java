package ar.edu.um.programacion2.servicioVentas.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import ar.edu.um.programacion2.servicioVentas.model.Tarjeta;


public interface ITarjetaRepository extends JpaRepository<Tarjeta, Long> {

	public List<Tarjeta> findAllById(Long id);

}
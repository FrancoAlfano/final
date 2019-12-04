package ar.edu.um.programacion2.tarjeta.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import ar.edu.um.programacion2.tarjeta.model.Tarjeta;


public interface ITarjetaRepository extends JpaRepository<Tarjeta, Long> {

	public List<Tarjeta> findAllById(Long id);

}
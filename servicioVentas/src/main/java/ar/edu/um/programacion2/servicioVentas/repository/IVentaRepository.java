package ar.edu.um.programacion2.servicioVentas.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ar.edu.um.programacion2.servicioVentas.model.Venta;


public interface IVentaRepository extends JpaRepository<Venta, Long> {

	public List<Venta> findAllById(Long id);
}
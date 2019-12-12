package ar.edu.um.programacion2.servicioVentas.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ar.edu.um.programacion2.servicioVentas.model.Cliente;


public interface IClienteRepository extends JpaRepository<Cliente, Long> {

	public List<Cliente> findAllById(Long id);
	public Cliente findByNombreAndApellido(String nombre, String apellido);
}
package ar.edu.um.programacion2.servicioVentas.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.um.programacion2.servicioVentas.exception.ClienteNotFoundException;
import ar.edu.um.programacion2.servicioVentas.model.Cliente;
import ar.edu.um.programacion2.servicioVentas.repository.IClienteRepository;

@Service
public class ClienteService {
	@Autowired
	private IClienteRepository repository;

	public List<Cliente> findAll() {
		
		return repository.findAll();
	}

	public Cliente add(Cliente cliente) {
		repository.save(cliente);
		return cliente;
	}

	public Cliente findById(Long clienteId) {
		
		return repository.findById(clienteId).orElseThrow(()-> new ClienteNotFoundException(clienteId));
	}
	
	public Long findByNombre(String nombre) {
		Cliente cliente = repository.findByNombre(nombre).get(0);
		Long id = cliente.getId();
		return id;

	}
	

	public Void delete(Long clienteId) {
		repository.deleteById(clienteId);
		return null;
	}

	public Cliente update(Cliente newcliente, Long clienteId) {
		
		return repository.findById(clienteId).map(cliente -> {
			cliente.setApellido(newcliente.getApellido());
			cliente.setNombre(newcliente.getNombre());
			return repository.save(cliente);
		}).orElseThrow(() -> new ClienteNotFoundException(clienteId));
	}



}
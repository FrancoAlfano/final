package ar.edu.um.programacion2.servicioTarjeta.repository;



import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import ar.edu.um.programacion2.servicioTarjeta.model.Tarjeta;



public interface ITarjetaRepository extends JpaRepository<Tarjeta, Long> {

	Tarjeta findByIdAndMonto(Long tarjetaId, Double monto);

	Optional<Tarjeta> findByIdAndClienteId(Long tarjeta_id, Long cliente_id);


}

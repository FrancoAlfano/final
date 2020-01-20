package com.mycompany.myapp.service.impl;

import com.mycompany.myapp.service.TarjetaService;
import com.mycompany.myapp.domain.Tarjeta;
import com.mycompany.myapp.repository.TarjetaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Tarjeta}.
 */
@Service
@Transactional
public class TarjetaServiceImpl implements TarjetaService {

    private final Logger log = LoggerFactory.getLogger(TarjetaServiceImpl.class);

    private final TarjetaRepository tarjetaRepository;

    public TarjetaServiceImpl(TarjetaRepository tarjetaRepository) {
        this.tarjetaRepository = tarjetaRepository;
    }

    /**
     * Save a tarjeta.
     *
     * @param tarjeta the entity to save.
     * @return the persisted entity.
     */
    @Override
    public Tarjeta save(Tarjeta tarjeta) {
        log.debug("Request to save Tarjeta : {}", tarjeta);
        return tarjetaRepository.save(tarjeta);
    }

    /**
     * Get all the tarjetas.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<Tarjeta> findAll(Pageable pageable) {
        log.debug("Request to get all Tarjetas");
        return tarjetaRepository.findAll(pageable);
    }


    /**
     * Get one tarjeta by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<Tarjeta> findOne(Long id) {
        log.debug("Request to get Tarjeta : {}", id);
        return tarjetaRepository.findById(id);
    }

    /**
     * Delete the tarjeta by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Tarjeta : {}", id);
        tarjetaRepository.deleteById(id);
    }
}

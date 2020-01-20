package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.ServicioventasjhApp;
import com.mycompany.myapp.domain.Tarjeta;
import com.mycompany.myapp.repository.TarjetaRepository;
import com.mycompany.myapp.service.TarjetaService;
import com.mycompany.myapp.web.rest.errors.ExceptionTranslator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Validator;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

import static com.mycompany.myapp.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link TarjetaResource} REST controller.
 */
@SpringBootTest(classes = ServicioventasjhApp.class)
public class TarjetaResourceIT {

    private static final Long DEFAULT_TARJETA_ID = 1L;
    private static final Long UPDATED_TARJETA_ID = 2L;

    private static final Long DEFAULT_COD_SEGURIDAD = 1L;
    private static final Long UPDATED_COD_SEGURIDAD = 2L;

    private static final LocalDate DEFAULT_VENCIMIENTO = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_VENCIMIENTO = LocalDate.now(ZoneId.systemDefault());

    private static final Double DEFAULT_MONTO = 1D;
    private static final Double UPDATED_MONTO = 2D;

    private static final Long DEFAULT_NUMERO = 1L;
    private static final Long UPDATED_NUMERO = 2L;

    private static final String DEFAULT_TIPO = "AAAAAAAAAA";
    private static final String UPDATED_TIPO = "BBBBBBBBBB";

    @Autowired
    private TarjetaRepository tarjetaRepository;

    @Autowired
    private TarjetaService tarjetaService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    @Autowired
    private Validator validator;

    private MockMvc restTarjetaMockMvc;

    private Tarjeta tarjeta;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final TarjetaResource tarjetaResource = new TarjetaResource(tarjetaService);
        this.restTarjetaMockMvc = MockMvcBuilders.standaloneSetup(tarjetaResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter)
            .setValidator(validator).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Tarjeta createEntity(EntityManager em) {
        Tarjeta tarjeta = new Tarjeta()
            .tarjeta_id(DEFAULT_TARJETA_ID)
            .cod_seguridad(DEFAULT_COD_SEGURIDAD)
            .vencimiento(DEFAULT_VENCIMIENTO)
            .monto(DEFAULT_MONTO)
            .numero(DEFAULT_NUMERO)
            .tipo(DEFAULT_TIPO);
        return tarjeta;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Tarjeta createUpdatedEntity(EntityManager em) {
        Tarjeta tarjeta = new Tarjeta()
            .tarjeta_id(UPDATED_TARJETA_ID)
            .cod_seguridad(UPDATED_COD_SEGURIDAD)
            .vencimiento(UPDATED_VENCIMIENTO)
            .monto(UPDATED_MONTO)
            .numero(UPDATED_NUMERO)
            .tipo(UPDATED_TIPO);
        return tarjeta;
    }

    @BeforeEach
    public void initTest() {
        tarjeta = createEntity(em);
    }

    @Test
    @Transactional
    public void createTarjeta() throws Exception {
        int databaseSizeBeforeCreate = tarjetaRepository.findAll().size();

        // Create the Tarjeta
        restTarjetaMockMvc.perform(post("/api/tarjetas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tarjeta)))
            .andExpect(status().isCreated());

        // Validate the Tarjeta in the database
        List<Tarjeta> tarjetaList = tarjetaRepository.findAll();
        assertThat(tarjetaList).hasSize(databaseSizeBeforeCreate + 1);
        Tarjeta testTarjeta = tarjetaList.get(tarjetaList.size() - 1);
        assertThat(testTarjeta.getTarjeta_id()).isEqualTo(DEFAULT_TARJETA_ID);
        assertThat(testTarjeta.getCod_seguridad()).isEqualTo(DEFAULT_COD_SEGURIDAD);
        assertThat(testTarjeta.getVencimiento()).isEqualTo(DEFAULT_VENCIMIENTO);
        assertThat(testTarjeta.getMonto()).isEqualTo(DEFAULT_MONTO);
        assertThat(testTarjeta.getNumero()).isEqualTo(DEFAULT_NUMERO);
        assertThat(testTarjeta.getTipo()).isEqualTo(DEFAULT_TIPO);
    }

    @Test
    @Transactional
    public void createTarjetaWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = tarjetaRepository.findAll().size();

        // Create the Tarjeta with an existing ID
        tarjeta.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restTarjetaMockMvc.perform(post("/api/tarjetas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tarjeta)))
            .andExpect(status().isBadRequest());

        // Validate the Tarjeta in the database
        List<Tarjeta> tarjetaList = tarjetaRepository.findAll();
        assertThat(tarjetaList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkTarjeta_idIsRequired() throws Exception {
        int databaseSizeBeforeTest = tarjetaRepository.findAll().size();
        // set the field null
        tarjeta.setTarjeta_id(null);

        // Create the Tarjeta, which fails.

        restTarjetaMockMvc.perform(post("/api/tarjetas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tarjeta)))
            .andExpect(status().isBadRequest());

        List<Tarjeta> tarjetaList = tarjetaRepository.findAll();
        assertThat(tarjetaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllTarjetas() throws Exception {
        // Initialize the database
        tarjetaRepository.saveAndFlush(tarjeta);

        // Get all the tarjetaList
        restTarjetaMockMvc.perform(get("/api/tarjetas?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(tarjeta.getId().intValue())))
            .andExpect(jsonPath("$.[*].tarjeta_id").value(hasItem(DEFAULT_TARJETA_ID.intValue())))
            .andExpect(jsonPath("$.[*].cod_seguridad").value(hasItem(DEFAULT_COD_SEGURIDAD.intValue())))
            .andExpect(jsonPath("$.[*].vencimiento").value(hasItem(DEFAULT_VENCIMIENTO.toString())))
            .andExpect(jsonPath("$.[*].monto").value(hasItem(DEFAULT_MONTO.doubleValue())))
            .andExpect(jsonPath("$.[*].numero").value(hasItem(DEFAULT_NUMERO.intValue())))
            .andExpect(jsonPath("$.[*].tipo").value(hasItem(DEFAULT_TIPO)));
    }
    
    @Test
    @Transactional
    public void getTarjeta() throws Exception {
        // Initialize the database
        tarjetaRepository.saveAndFlush(tarjeta);

        // Get the tarjeta
        restTarjetaMockMvc.perform(get("/api/tarjetas/{id}", tarjeta.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(tarjeta.getId().intValue()))
            .andExpect(jsonPath("$.tarjeta_id").value(DEFAULT_TARJETA_ID.intValue()))
            .andExpect(jsonPath("$.cod_seguridad").value(DEFAULT_COD_SEGURIDAD.intValue()))
            .andExpect(jsonPath("$.vencimiento").value(DEFAULT_VENCIMIENTO.toString()))
            .andExpect(jsonPath("$.monto").value(DEFAULT_MONTO.doubleValue()))
            .andExpect(jsonPath("$.numero").value(DEFAULT_NUMERO.intValue()))
            .andExpect(jsonPath("$.tipo").value(DEFAULT_TIPO));
    }

    @Test
    @Transactional
    public void getNonExistingTarjeta() throws Exception {
        // Get the tarjeta
        restTarjetaMockMvc.perform(get("/api/tarjetas/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateTarjeta() throws Exception {
        // Initialize the database
        tarjetaService.save(tarjeta);

        int databaseSizeBeforeUpdate = tarjetaRepository.findAll().size();

        // Update the tarjeta
        Tarjeta updatedTarjeta = tarjetaRepository.findById(tarjeta.getId()).get();
        // Disconnect from session so that the updates on updatedTarjeta are not directly saved in db
        em.detach(updatedTarjeta);
        updatedTarjeta
            .tarjeta_id(UPDATED_TARJETA_ID)
            .cod_seguridad(UPDATED_COD_SEGURIDAD)
            .vencimiento(UPDATED_VENCIMIENTO)
            .monto(UPDATED_MONTO)
            .numero(UPDATED_NUMERO)
            .tipo(UPDATED_TIPO);

        restTarjetaMockMvc.perform(put("/api/tarjetas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedTarjeta)))
            .andExpect(status().isOk());

        // Validate the Tarjeta in the database
        List<Tarjeta> tarjetaList = tarjetaRepository.findAll();
        assertThat(tarjetaList).hasSize(databaseSizeBeforeUpdate);
        Tarjeta testTarjeta = tarjetaList.get(tarjetaList.size() - 1);
        assertThat(testTarjeta.getTarjeta_id()).isEqualTo(UPDATED_TARJETA_ID);
        assertThat(testTarjeta.getCod_seguridad()).isEqualTo(UPDATED_COD_SEGURIDAD);
        assertThat(testTarjeta.getVencimiento()).isEqualTo(UPDATED_VENCIMIENTO);
        assertThat(testTarjeta.getMonto()).isEqualTo(UPDATED_MONTO);
        assertThat(testTarjeta.getNumero()).isEqualTo(UPDATED_NUMERO);
        assertThat(testTarjeta.getTipo()).isEqualTo(UPDATED_TIPO);
    }

    @Test
    @Transactional
    public void updateNonExistingTarjeta() throws Exception {
        int databaseSizeBeforeUpdate = tarjetaRepository.findAll().size();

        // Create the Tarjeta

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTarjetaMockMvc.perform(put("/api/tarjetas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tarjeta)))
            .andExpect(status().isBadRequest());

        // Validate the Tarjeta in the database
        List<Tarjeta> tarjetaList = tarjetaRepository.findAll();
        assertThat(tarjetaList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteTarjeta() throws Exception {
        // Initialize the database
        tarjetaService.save(tarjeta);

        int databaseSizeBeforeDelete = tarjetaRepository.findAll().size();

        // Delete the tarjeta
        restTarjetaMockMvc.perform(delete("/api/tarjetas/{id}", tarjeta.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Tarjeta> tarjetaList = tarjetaRepository.findAll();
        assertThat(tarjetaList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Tarjeta.class);
        Tarjeta tarjeta1 = new Tarjeta();
        tarjeta1.setId(1L);
        Tarjeta tarjeta2 = new Tarjeta();
        tarjeta2.setId(tarjeta1.getId());
        assertThat(tarjeta1).isEqualTo(tarjeta2);
        tarjeta2.setId(2L);
        assertThat(tarjeta1).isNotEqualTo(tarjeta2);
        tarjeta1.setId(null);
        assertThat(tarjeta1).isNotEqualTo(tarjeta2);
    }
}

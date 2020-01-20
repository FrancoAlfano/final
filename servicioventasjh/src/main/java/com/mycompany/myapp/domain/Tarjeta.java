package com.mycompany.myapp.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * A Tarjeta.
 */
@Entity
@Table(name = "tarjeta")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Tarjeta implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "tarjeta_id", nullable = false, unique = true)
    private Long tarjeta_id;

    @Column(name = "cod_seguridad")
    private Long cod_seguridad;

    @Column(name = "vencimiento")
    private LocalDate vencimiento;

    @Column(name = "monto")
    private Double monto;

    @Column(name = "numero")
    private Long numero;

    @Column(name = "tipo")
    private String tipo;

    @ManyToOne
    private Cliente cliente;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTarjeta_id() {
        return tarjeta_id;
    }

    public Tarjeta tarjeta_id(Long tarjeta_id) {
        this.tarjeta_id = tarjeta_id;
        return this;
    }

    public void setTarjeta_id(Long tarjeta_id) {
        this.tarjeta_id = tarjeta_id;
    }

    public Long getCod_seguridad() {
        return cod_seguridad;
    }

    public Tarjeta cod_seguridad(Long cod_seguridad) {
        this.cod_seguridad = cod_seguridad;
        return this;
    }

    public void setCod_seguridad(Long cod_seguridad) {
        this.cod_seguridad = cod_seguridad;
    }

    public LocalDate getVencimiento() {
        return vencimiento;
    }

    public Tarjeta vencimiento(LocalDate vencimiento) {
        this.vencimiento = vencimiento;
        return this;
    }

    public void setVencimiento(LocalDate vencimiento) {
        this.vencimiento = vencimiento;
    }

    public Double getMonto() {
        return monto;
    }

    public Tarjeta monto(Double monto) {
        this.monto = monto;
        return this;
    }

    public void setMonto(Double monto) {
        this.monto = monto;
    }

    public Long getNumero() {
        return numero;
    }

    public Tarjeta numero(Long numero) {
        this.numero = numero;
        return this;
    }

    public void setNumero(Long numero) {
        this.numero = numero;
    }

    public String getTipo() {
        return tipo;
    }

    public Tarjeta tipo(String tipo) {
        this.tipo = tipo;
        return this;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public Tarjeta cliente(Cliente cliente) {
        this.cliente = cliente;
        return this;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Tarjeta)) {
            return false;
        }
        return id != null && id.equals(((Tarjeta) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Tarjeta{" +
            "id=" + getId() +
            ", tarjeta_id=" + getTarjeta_id() +
            ", cod_seguridad=" + getCod_seguridad() +
            ", vencimiento='" + getVencimiento() + "'" +
            ", monto=" + getMonto() +
            ", numero=" + getNumero() +
            ", tipo='" + getTipo() + "'" +
            "}";
    }
}

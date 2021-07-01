package com.calidad.nominasoft.dominio.entidades;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "conceptos")
public class OtrosConceptos implements Serializable {

    @Id
    private Long id;
    private float montoAdelantos;
    private float montoHorasAusente;
    private float montoHorasExtra;
    private float montoOtrosDescuentos;
    private float montoOtrosIngresos;
    private float montoReintegro;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "contrato_id", referencedColumnName = "id")
    private Contrato contrato;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "periodoDePago_id", referencedColumnName = "id")
    private PeriodoDePago periodoDePago;

    public OtrosConceptos(float montoAdelantos, float montoHorasAusente, float montoHorasExtra,
            float montoOtrosDescuentos, float montoOtrosIngresos, float montoReintegro) {
        super();
        this.montoAdelantos = montoAdelantos;
        this.montoHorasAusente = montoHorasAusente;
        this.montoHorasExtra = montoHorasExtra;
        this.montoOtrosDescuentos = montoOtrosDescuentos;
        this.montoOtrosIngresos = montoOtrosIngresos;
        this.montoReintegro = montoReintegro;
    }

    public OtrosConceptos() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public float getMontoAdelantos() {
        return montoAdelantos;
    }

    public void setMontoAdelantos(float montoAdelantos) {
        this.montoAdelantos = montoAdelantos;
    }

    public float getMontoHorasAusente() {
        return montoHorasAusente;
    }

    public void setMontoHorasAusente(float montoHorasAusente) {
        this.montoHorasAusente = montoHorasAusente;
    }

    public float getMontoHorasExtra() {
        return montoHorasExtra;
    }

    public void setMontoHorasExtra(float montoHorasExtra) {
        this.montoHorasExtra = montoHorasExtra;
    }

    public float getMontoOtrosDescuentos() {
        return montoOtrosDescuentos;
    }

    public void setMontoOtrosDescuentos(float montoOtrosDescuentos) {
        this.montoOtrosDescuentos = montoOtrosDescuentos;
    }

    public float getMontoOtrosIngresos() {
        return montoOtrosIngresos;
    }

    public void setMontoOtrosIngresos(float montoOtrosIngresos) {
        this.montoOtrosIngresos = montoOtrosIngresos;
    }

    public float getMontoReintegro() {
        return montoReintegro;
    }

    public void setMontoReintegro(float montoReintegro) {
        this.montoReintegro = montoReintegro;
    }

    public Contrato getContrato() {
        return contrato;
    }

    public void setContrato(Contrato contrato) {
        this.contrato = contrato;
    }

    public PeriodoDePago getPeriodos() {
        return periodoDePago;
    }

    public void setPeriodos(PeriodoDePago periodoDePago) {
        this.periodoDePago = periodoDePago;
    }

    // REGLAS DE NEGOCIO

    public float calcularTotalConceptosIngresos() {
        return montoHorasExtra + montoReintegro + montoOtrosIngresos;

    }

    public float calcularTotalConceptosDescuentos() {
        return montoHorasAusente + montoAdelantos + montoOtrosDescuentos;
    }
}

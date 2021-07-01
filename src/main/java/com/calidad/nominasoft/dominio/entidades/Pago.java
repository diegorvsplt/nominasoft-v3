package com.calidad.nominasoft.dominio.entidades;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "pagos")
public class Pago implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime fechaActual;
    private int totalDeHoras;
    private int valorHora;
    private float montoPorAsignacionFamiliar;
    private float porcentajeDescuentoAFP;

    @ManyToOne
    @JoinColumn(name = "periododepago_id", referencedColumnName = "id")
    private PeriodoDePago periodoDePago;

    @ManyToOne
    @JoinColumn(name = "contrato_id", referencedColumnName = "id")
    private Contrato contrato;

    @OneToOne
    @JoinColumn(name = "otrosConceptos_id", referencedColumnName = "id")
    private OtrosConceptos otrosConceptos;

    public Pago() {
    }

    public Pago(PeriodoDePago periodoDePago, Contrato contrato, OtrosConceptos otrosConceptos) {
        this.fechaActual = LocalDateTime.now();
        this.periodoDePago = periodoDePago;
        this.contrato = contrato;
        this.totalDeHoras = calcularTotalDeHoras();
        this.valorHora = contrato.getValorHora();
        this.montoPorAsignacionFamiliar = contrato.calcularMontoAsignacionFamiliar();
        this.porcentajeDescuentoAFP = contrato.getAfp().getDescuento();
        this.otrosConceptos = otrosConceptos;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getFechaActual() {
        return fechaActual;
    }

    public void setFechaActual(LocalDateTime fechaActual) {
        this.fechaActual = fechaActual;
    }

    public int getTotalDeHoras() {
        return totalDeHoras;
    }

    public void setTotalDeHoras(int totalDeHoras) {
        this.totalDeHoras = totalDeHoras;
    }

    public int getValorHora() {
        return valorHora;
    }

    public void setValorHora(int valorHora) {
        this.valorHora = valorHora;
    }

    public float getMontoPorAsignacionFamiliar() {
        return montoPorAsignacionFamiliar;
    }

    public void setMontoPorAsignacionFamiliar(float montoPorAsignacionFamiliar) {
        this.montoPorAsignacionFamiliar = montoPorAsignacionFamiliar;
    }

    public float getPorcentajeDescuentoAFP() {
        return porcentajeDescuentoAFP;
    }

    public void setPorcentajeDescuentoAFP(float porcentajeDescuentoAFP) {
        this.porcentajeDescuentoAFP = porcentajeDescuentoAFP;
    }

    public PeriodoDePago getPeriodoDePago() {
        return periodoDePago;
    }

    public void setPeriodoDePago(PeriodoDePago periodoDePago) {
        this.periodoDePago = periodoDePago;
    }

    public Contrato getContrato() {
        return contrato;
    }

    public void setContrato(Contrato contrato) {
        this.contrato = contrato;
    }

    public OtrosConceptos getOtrosConceptos() {
        return otrosConceptos;
    }

    public void setOtrosConceptos(OtrosConceptos otrosConceptos) {
        this.otrosConceptos = otrosConceptos;
    }

    // REGLAS DE NEGOCIO

    public int calcularSueldoBasico() {
        return totalDeHoras * valorHora;
    }

    public float calcularTotalIngresos() {
        if (otrosConceptos == null)
            return calcularSueldoBasico() + contrato.calcularMontoAsignacionFamiliar();
        return calcularSueldoBasico() + contrato.calcularMontoAsignacionFamiliar()
                + otrosConceptos.calcularTotalConceptosIngresos();
    }

    public float calcularDescuentoAFP() {
        return calcularSueldoBasico() * (porcentajeDescuentoAFP / 100);
    }

    public float calcularSueldoNeto() {
        return calcularTotalIngresos() - calcularTotalDescuentos();
    }

    public float calcularTotalDescuentos() {
        if (otrosConceptos == null) {
            return calcularDescuentoAFP();
        }
        return calcularDescuentoAFP() + otrosConceptos.calcularTotalConceptosDescuentos();
    }

    public int calcularTotalDeHoras() {
        return periodoDePago.getSemanasDelPeriodo() * contrato.getHorasContratadasPorSemana();
    }
}

package com.calidad.nominasoft.dominio.entidades;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;

import java.time.LocalDate;

@Entity
@Table(name = "contratos")
public class Contrato implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private Long id;
    private boolean asignacionFamiliar;
    private String cargo;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate fechaInicio;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate fechaFin;
    private int horasContratadasPorSemana;
    private int valorHora;
    private boolean anulado = false;

    @ManyToOne
    @JoinColumn(name = "afp_id", referencedColumnName = "id")
    private Afp afp;

    @ManyToOne
    @JoinColumn(name = "empleado_id", referencedColumnName = "id")
    private Empleado empleado;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean getAsignacionFamiliar() {
        return asignacionFamiliar;
    }

    public void setAsignacionFamiliar(Boolean asignacionFamiliar) {
        this.asignacionFamiliar = asignacionFamiliar;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(LocalDate fechaFin) {
        this.fechaFin = fechaFin;
    }

    public int getHorasContratadasPorSemana() {
        return horasContratadasPorSemana;
    }

    public void setHorasContratadasPorSemana(int horasContratadasPorSemana) {
        this.horasContratadasPorSemana = horasContratadasPorSemana;
    }

    public int getValorHora() {
        return valorHora;
    }

    public void setValorHora(int valorHora) {
        this.valorHora = valorHora;
    }

    public Afp getAfp() {
        return afp;
    }

    public void setAfp(Afp afp) {
        this.afp = afp;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    public boolean isAnulado() {
        return anulado;
    }

    public void setAnulado(boolean anulado) {
        this.anulado = anulado;
    }

    // REGLAS DE NEGOCIO

    public boolean estaVigente() {
        var fechaActual = LocalDate.now();

        return ((fechaFin.isAfter(fechaActual) || fechaFin.isEqual(fechaActual)) && !anulado);
    }

    public boolean esValidaFechaFin() {
        LocalDate fechaInicio3MesesDespues = fechaInicio.plusMonths(3);
        LocalDate fechaInicio1AnoDespues = fechaInicio.plusYears(1);

        return ((fechaFin.isAfter(fechaInicio3MesesDespues) || (fechaFin.equals(fechaInicio3MesesDespues)))
                && ((fechaFin.isBefore(fechaInicio1AnoDespues)) || (fechaFin.equals(fechaInicio1AnoDespues))));
    }

    public boolean esValidoHorasContratadas() {
        return (horasContratadasPorSemana >= 8 && horasContratadasPorSemana <= 40
                && horasContratadasPorSemana % 4 == 0);
    }

    public boolean esValidoValorHora() {
        return (valorHora >= 10 && valorHora <= 60);
    }

    public float calcularMontoAsignacionFamiliar() {

        if (Boolean.TRUE.equals(asignacionFamiliar))
            return horasContratadasPorSemana * valorHora * 0.1f;
        else
            return 0;
    }

}

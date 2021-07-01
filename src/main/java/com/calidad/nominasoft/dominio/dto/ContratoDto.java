package com.calidad.nominasoft.dominio.dto;

import java.time.LocalDate;

import com.calidad.nominasoft.dominio.entidades.Afp;
import com.calidad.nominasoft.dominio.entidades.Empleado;

import org.springframework.format.annotation.DateTimeFormat;

public class ContratoDto {

  private Long id;
  private boolean asignacionFamiliar;
  private String cargo;
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private LocalDate fechaInicio;
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private LocalDate fechaFin;
  private int horasContratadasPorSemana;
  private int valorHora;
  private boolean anulado;
  private Afp afp;
  private Empleado empleado;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public boolean isAsignacionFamiliar() {
    return asignacionFamiliar;
  }

  public void setAsignacionFamiliar(boolean asignacionFamiliar) {
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

  public void setFechaInicio(String fechaInicio) {
    this.fechaInicio = LocalDate.parse(fechaInicio);
  }

  public LocalDate getFechaFin() {
    return fechaFin;
  }

  public void setFechaFin(String fechaFin) {
    this.fechaFin = LocalDate.parse(fechaFin);
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

  public boolean isAnulado() {
    return anulado;
  }

  public void setAnulado(boolean anulado) {
    this.anulado = anulado;
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

}

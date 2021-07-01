package com.calidad.nominasoft.dominio.dto;

import java.time.LocalDate;

public class PeriodoDto {

  private Long idDto;
  private boolean estadoDto;
  private LocalDate fechaInicioDto;
  private LocalDate fechaFinDto;

  public Long getIdDto() {
    return idDto;
  }

  public void setIdDto(Long idDto) {
    this.idDto = idDto;
  }

  public boolean isEstadoDto() {
    return estadoDto;
  }

  public void setEstadoDto(boolean estadoDto) {
    this.estadoDto = estadoDto;
  }

  public LocalDate getFechaInicioDto() {
    return fechaInicioDto;
  }

  public void setFechaInicioDto(String fechaInicioDto) {
    this.fechaInicioDto = LocalDate.parse(fechaInicioDto);
  }

  public LocalDate getFechaFinDto() {
    return fechaFinDto;
  }

  public void setFechaFinDto(String fechaFinDto) {
    this.fechaFinDto = LocalDate.parse(fechaFinDto);
  }

}
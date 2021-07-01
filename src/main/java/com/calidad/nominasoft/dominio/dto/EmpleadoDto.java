package com.calidad.nominasoft.dominio.dto;

import java.time.LocalDate;

public class EmpleadoDto {

  private Long idDto;
  private String nombreDto;
  private Long dniDto;
  private String gradoAcademicoDto;
  private String direccionDto;
  private String estadoCivilDto;
  private LocalDate fechaNacimientoDto;
  private String telefonoDto;

  public Long getIdDto() {
    return idDto;
  }

  public void setIdDto(Long idDto) {
    this.idDto = idDto;
  }

  public String getNombreDto() {
    return nombreDto;
  }

  public void setNombreDto(String nombreDto) {
    this.nombreDto = nombreDto;
  }

  public Long getDniDto() {
    return dniDto;
  }

  public void setDniDto(Long dniDto) {
    this.dniDto = dniDto;
  }

  public String getGradoAcademicoDto() {
    return gradoAcademicoDto;
  }

  public void setGradoAcademicoDto(String gradoAcademicoDto) {
    this.gradoAcademicoDto = gradoAcademicoDto;
  }

  public String getDireccionDto() {
    return direccionDto;
  }

  public void setDireccionDto(String direccionDto) {
    this.direccionDto = direccionDto;
  }

  public String getEstadoCivilDto() {
    return estadoCivilDto;
  }

  public void setEstadoCivilDto(String estadoCivilDto) {
    this.estadoCivilDto = estadoCivilDto;
  }

  public LocalDate getFechaNacimientoDto() {
    return fechaNacimientoDto;
  }

  public void setFechaNacimientoDto(String fechaNacimientoDto) {
    this.fechaNacimientoDto = LocalDate.parse(fechaNacimientoDto);
  }

  public String getTelefonoDto() {
    return telefonoDto;
  }

  public void setTelefonoDto(String telefonoDto) {
    this.telefonoDto = telefonoDto;
  }

}

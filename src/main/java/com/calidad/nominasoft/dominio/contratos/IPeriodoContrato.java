package com.calidad.nominasoft.dominio.contratos;

import com.calidad.nominasoft.dominio.entidades.PeriodoDePago;

public interface IPeriodoContrato {
  public PeriodoDePago buscarActivo();

  public PeriodoDePago buscarUltimo();
}
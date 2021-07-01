package com.calidad.nominasoft.dominio.contratos;

import java.util.List;

import com.calidad.nominasoft.dominio.entidades.Pago;

public interface IPagoContrato {
  public List<Pago> buscarPagosPorPeriodoId(Long id);
}

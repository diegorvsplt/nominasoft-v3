package com.calidad.nominasoft.persistencia.repositorio;

import java.util.List;

import com.calidad.nominasoft.dominio.entidades.Pago;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IPagoRepo extends JpaRepository<Pago, Long> {
  public List<Pago> findAllByPeriodoDePago_Id(Long id);
}

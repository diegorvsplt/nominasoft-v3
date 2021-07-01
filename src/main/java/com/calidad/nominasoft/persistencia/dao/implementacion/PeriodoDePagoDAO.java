package com.calidad.nominasoft.persistencia.dao.implementacion;

import com.calidad.nominasoft.persistencia.repositorio.IPeriodoDePagoRepo;
import com.calidad.nominasoft.dominio.contratos.IPeriodoContrato;
import com.calidad.nominasoft.dominio.entidades.PeriodoDePago;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public class PeriodoDePagoDAO extends CRUDDAO<PeriodoDePago, Long> implements IPeriodoContrato {

  @Autowired
  private IPeriodoDePagoRepo periodoRepo;

  @Override
  public PeriodoDePago buscarActivo() {
    return periodoRepo.findFirstByEstadoTrueOrderByFechaInicioDesc().orElse(null);
  }

  @Override
  public JpaRepository<PeriodoDePago, Long> getDao() {
    return periodoRepo;
  }

  public PeriodoDePago buscarUno(Long id) {
    return periodoRepo.findById(id).orElse(null);
  }

  @Override
  public PeriodoDePago buscarUltimo() {
    return periodoRepo.findFirstByOrderByIdDesc();
  }

}
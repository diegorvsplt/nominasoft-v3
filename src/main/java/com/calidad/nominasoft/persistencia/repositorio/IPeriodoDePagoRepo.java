package com.calidad.nominasoft.persistencia.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

import com.calidad.nominasoft.dominio.entidades.PeriodoDePago;

public interface IPeriodoDePagoRepo extends JpaRepository<PeriodoDePago, Long> {
    public Optional<PeriodoDePago> findFirstByEstadoTrueOrderByFechaInicioDesc();

    public PeriodoDePago findFirstByOrderByIdDesc();
}

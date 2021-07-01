package com.calidad.nominasoft.persistencia.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

import com.calidad.nominasoft.dominio.entidades.OtrosConceptos;

public interface IOtrosConceptosRepo extends JpaRepository<OtrosConceptos, Long> {
    public Optional<OtrosConceptos> findOtrosConceptosByContrato_Id(Long id);

}

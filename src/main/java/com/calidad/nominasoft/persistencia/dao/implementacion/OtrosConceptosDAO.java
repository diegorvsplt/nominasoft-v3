package com.calidad.nominasoft.persistencia.dao.implementacion;

import com.calidad.nominasoft.persistencia.repositorio.IOtrosConceptosRepo;
import com.calidad.nominasoft.dominio.contratos.IOtrosConceptosContrato;
import com.calidad.nominasoft.dominio.entidades.OtrosConceptos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public class OtrosConceptosDAO extends CRUDDAO<OtrosConceptos, Long> implements IOtrosConceptosContrato {

    @Autowired
    private IOtrosConceptosRepo conceptosRepo;

    @Override
    public JpaRepository<OtrosConceptos, Long> getDao() {
        return conceptosRepo;
    }

    @Override
    public OtrosConceptos buscarPorContratoId(Long id) {
        return conceptosRepo.findOtrosConceptosByContrato_Id(id).orElse(null);
    }
}

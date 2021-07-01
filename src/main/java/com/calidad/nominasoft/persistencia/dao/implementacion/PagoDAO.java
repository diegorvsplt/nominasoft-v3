package com.calidad.nominasoft.persistencia.dao.implementacion;

import java.util.List;

import com.calidad.nominasoft.persistencia.repositorio.IPagoRepo;
import com.calidad.nominasoft.dominio.contratos.IPagoContrato;
import com.calidad.nominasoft.dominio.entidades.Pago;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public class PagoDAO extends CRUDDAO<Pago, Long> implements IPagoContrato {

    @Autowired
    private IPagoRepo pagoRepo;

    @Override
    public JpaRepository<Pago, Long> getDao() {
        return pagoRepo;
    }

    @Override
    public List<Pago> buscarPagosPorPeriodoId(Long id) {
        return pagoRepo.findAllByPeriodoDePago_Id(id);
    }
}

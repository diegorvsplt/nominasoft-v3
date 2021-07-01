package com.calidad.nominasoft.aplicacion.servicios;

import com.calidad.nominasoft.persistencia.dao.implementacion.ContratoDAO;
import com.calidad.nominasoft.persistencia.dao.implementacion.OtrosConceptosDAO;
import com.calidad.nominasoft.persistencia.dao.implementacion.PagoDAO;
import com.calidad.nominasoft.persistencia.dao.implementacion.PeriodoDePagoDAO;
import com.calidad.nominasoft.dominio.entidades.Contrato;
import com.calidad.nominasoft.dominio.entidades.OtrosConceptos;
import com.calidad.nominasoft.dominio.entidades.Pago;
import com.calidad.nominasoft.dominio.entidades.PeriodoDePago;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProcesarPagosServicio {

    @Autowired
    private PeriodoDePagoDAO periodoDao;
    @Autowired
    private ContratoDAO contratoDAO;
    @Autowired
    private OtrosConceptosDAO conceptosDAO;
    @Autowired
    private PagoDAO pagoDAO;

    public PeriodoDePago buscarPeriodoDePagoActivo() {
        return periodoDao.buscarActivo();
    }

    public List<Contrato> consultarContratosAProcesar(PeriodoDePago periodo) {
        List<Contrato> listaDeContratosAProcesar = new ArrayList<>();
        if (periodo != null && periodo.esFechaActualMayor()) {
            List<Contrato> listaContratosActivos = contratoDAO.buscarContratosActivos();
            for (Contrato contrato : listaContratosActivos) {
                if (contrato.getFechaFin().isAfter(periodo.getFechaInicio()))
                    listaDeContratosAProcesar.add(contrato);
            }
        }
        return listaDeContratosAProcesar;
    }

    public List<Pago> registrarPagos(PeriodoDePago periodo, List<Contrato> listaContratosActivos) {

        List<Pago> listaPagos = new ArrayList<>();
        OtrosConceptos otrosConceptos;
        for (Contrato contrato : listaContratosActivos) {
            otrosConceptos = conceptosDAO.buscarPorContratoId(contrato.getId());
            listaPagos.add(new Pago(periodo, contrato, otrosConceptos));
        }
        return listaPagos;
    }

    public void guardarPagos(List<Pago> listaPagos, PeriodoDePago periodo) {

        for (Pago pago : listaPagos) {
            pagoDAO.guardar(pago);
        }
        periodo.setEstado(false);
        periodoDao.guardar(periodo);
    }

    public PeriodoDePago buscarPeriodo() {
        return periodoDao.buscarUltimo();
    }

    public List<Pago> buscarPagos(Long periodoId) {
        return pagoDAO.buscarPagosPorPeriodoId(periodoId);
    }
}
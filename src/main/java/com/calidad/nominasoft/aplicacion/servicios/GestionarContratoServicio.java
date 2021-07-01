package com.calidad.nominasoft.aplicacion.servicios;

import com.calidad.nominasoft.persistencia.dao.implementacion.AfpDAO;
import com.calidad.nominasoft.persistencia.dao.implementacion.ContratoDAO;
import com.calidad.nominasoft.persistencia.dao.implementacion.EmpleadoDAO;
import com.calidad.nominasoft.dominio.entidades.Afp;
import com.calidad.nominasoft.dominio.entidades.Contrato;
import com.calidad.nominasoft.dominio.entidades.Empleado;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GestionarContratoServicio {

    @Autowired
    private ContratoDAO contratoDAO;
    @Autowired
    private EmpleadoDAO empleadoDAO;
    @Autowired
    private AfpDAO afpDAO;

    public Contrato obtenerUltimoContrato(Long dni) {
        return contratoDAO.buscarUltimoContratoEmpleado(dni);
    }

    public boolean validarFechaInicio(Contrato contratoNuevo, Contrato contratoAnterior) {
        return (contratoNuevo.getFechaInicio().isAfter(contratoAnterior.getFechaFin()));
    }

    public boolean guardarContrato(Contrato contrato) {
        if (contrato.esValidaFechaFin() && contrato.esValidoHorasContratadas() && contrato.esValidoValorHora()) {
            contratoDAO.guardar(contrato);
            return true;
        }
        return false;
    }

    public void anularContrato(Long id) {
        var contrato = contratoDAO.buscarPorId(id);
        contrato.setAnulado(true);
        contratoDAO.guardar(contrato);
    }

    public Empleado buscarEmpleado(Long dni) {
        return empleadoDAO.buscarPorDni(dni);
    }

    public List<Afp> buscarTodosAfp() {
        return afpDAO.buscarTodos();
    }

}

package com.calidad.nominasoft.dominio.contratos;

import java.util.List;

import com.calidad.nominasoft.dominio.entidades.Contrato;

public interface IContratoContrato {

    public Contrato buscarUltimoContratoEmpleado(Long dni);

    public List<Contrato> buscarContratosActivos();

}

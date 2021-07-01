package com.calidad.nominasoft.dominio.contratos;

import com.calidad.nominasoft.dominio.entidades.Empleado;

public interface IEmpleadoContrato {
    public Empleado buscarPorDni(Long dni);
}

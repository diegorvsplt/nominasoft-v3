package com.calidad.nominasoft.persistencia.dao.implementacion;

import com.calidad.nominasoft.persistencia.repositorio.IEmpleadoRepo;
import com.calidad.nominasoft.dominio.contratos.IEmpleadoContrato;
import com.calidad.nominasoft.dominio.entidades.Empleado;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public class EmpleadoDAO extends CRUDDAO<Empleado, Long> implements IEmpleadoContrato {

    @Autowired
    private IEmpleadoRepo empleadoRepo;

    @Override
    public JpaRepository<Empleado, Long> getDao() {
        return empleadoRepo;
    }

    @Override
    public Empleado buscarPorDni(Long dni) {
        return empleadoRepo.findByDni(dni).orElse(null);

    }

    public Empleado buscarUno(Long id) {
        return empleadoRepo.findById(id).orElse(null);
    }
}

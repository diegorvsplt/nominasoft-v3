package com.calidad.nominasoft.persistencia.dao.implementacion;

import com.calidad.nominasoft.persistencia.repositorio.IAfpRepositorio;
import com.calidad.nominasoft.dominio.contratos.IAfpContrato;
import com.calidad.nominasoft.dominio.entidades.Afp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public class AfpDAO extends CRUDDAO<Afp, Long> implements IAfpContrato {

    @Autowired
    private IAfpRepositorio afpRepo;

    @Override
    public JpaRepository<Afp, Long> getDao() {
        return afpRepo;
    }
}

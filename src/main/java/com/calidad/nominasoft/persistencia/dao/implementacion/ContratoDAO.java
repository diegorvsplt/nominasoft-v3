package com.calidad.nominasoft.persistencia.dao.implementacion;

import com.calidad.nominasoft.persistencia.repositorio.IContratoRepo;
import com.calidad.nominasoft.dominio.contratos.IContratoContrato;
import com.calidad.nominasoft.dominio.entidades.Contrato;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ContratoDAO extends CRUDDAO<Contrato, Long> implements IContratoContrato {

    @Autowired
    private IContratoRepo contratoRepo;

    @Override
    public Contrato buscarUltimoContratoEmpleado(Long dni) {
        return contratoRepo.findFirstByEmpleado_DniAndAnuladoFalse(dni).orElse(null);
    }

    @Override
    public List<Contrato> buscarContratosActivos() {
        return contratoRepo.findAllByAnuladoFalse();
    }

    @Override
    public JpaRepository<Contrato, Long> getDao() {
        return contratoRepo;
    }

    public void eliminarNUltimos(int cantidadDeContratosAEliminar) {
        var i = 0;
        for (Contrato contrato : buscarTodos()) {
            if (i < cantidadDeContratosAEliminar)
                eliminar(contrato.getId());
            else {
                break;
            }
            i++;
        }
    }
}

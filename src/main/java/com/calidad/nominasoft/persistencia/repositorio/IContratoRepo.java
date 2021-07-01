package com.calidad.nominasoft.persistencia.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

import com.calidad.nominasoft.dominio.entidades.Contrato;

public interface IContratoRepo extends JpaRepository<Contrato, Long> {
    
    public Optional<Contrato> findFirstByEmpleado_DniAndAnuladoFalse(Long dni);

    public List<Contrato> findAllByAnuladoFalse();

    public List<Contrato> findAllByEmpleado_Id(Long id);
}

package com.calidad.nominasoft.persistencia.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

import com.calidad.nominasoft.dominio.entidades.Empleado;

public interface IEmpleadoRepo extends JpaRepository<Empleado, Long> {
    public Optional<Empleado> findByDni(Long dni);

}

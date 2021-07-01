package com.calidad.nominasoft.persistencia.repositorio;

import com.calidad.nominasoft.dominio.entidades.Afp;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IAfpRepositorio extends JpaRepository<Afp,Long> {
}
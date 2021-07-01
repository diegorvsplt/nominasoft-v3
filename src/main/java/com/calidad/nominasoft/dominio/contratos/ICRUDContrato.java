package com.calidad.nominasoft.dominio.contratos;

import java.io.Serializable;
import java.util.List;

public interface ICRUDContrato<T, ID extends Serializable> {
    public T guardar(T entity);

    public List<T> buscarTodos();

    public void eliminar(ID id);

    public T buscarPorId(ID id);
}

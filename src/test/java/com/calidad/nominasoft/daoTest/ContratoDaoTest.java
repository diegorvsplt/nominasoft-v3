package com.calidad.nominasoft.daoTest;

import java.util.List;

import com.calidad.nominasoft.dominio.entidades.Contrato;
import com.calidad.nominasoft.dominio.entidades.Empleado;
import com.calidad.nominasoft.persistencia.dao.implementacion.ContratoDAO;
import com.calidad.nominasoft.persistencia.dao.implementacion.EmpleadoDAO;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(value = SpringRunner.class)
@SpringBootTest
class ContratoDaoTest {

  @Autowired
  private ContratoDAO contratoDAO;
  @Autowired
  private EmpleadoDAO empleadoDAO;

  @Test
  void Test_buscarUltimoContratoporEmpleadoDni() {
    Contrato contrato = new Contrato();
    Empleado empleado = empleadoDAO.buscarPorId(1L);

    contrato.setEmpleado(empleado);

    contratoDAO.guardar(contrato);
    Contrato contratoDB = contratoDAO.buscarUltimoContratoEmpleado(empleado.getDni());

    Assert.assertEquals(contrato.getEmpleado().getDni(), contratoDB.getEmpleado().getDni());
    contratoDAO.eliminar(contratoDB.getId());
  }

  @Test
  void Test_buscarContratosActivos() {
    Contrato contrato1 = new Contrato();
    Contrato contrato2 = new Contrato();
    Contrato contrato3 = new Contrato();

    contrato1.setAnulado(false);
    contrato2.setAnulado(false);
    contrato3.setAnulado(true);

    contratoDAO.guardar(contrato1);
    contratoDAO.guardar(contrato2);
    contratoDAO.guardar(contrato3);

    List<Contrato> listaContratos = contratoDAO.buscarContratosActivos();

    Assert.assertEquals(2, listaContratos.size());

    contratoDAO.eliminarNUltimos(3);
  }

}

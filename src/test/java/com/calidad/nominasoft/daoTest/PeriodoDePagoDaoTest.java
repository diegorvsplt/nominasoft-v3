package com.calidad.nominasoft.daoTest;

import java.time.LocalDate;
import java.util.List;

import com.calidad.nominasoft.dominio.entidades.PeriodoDePago;
import com.calidad.nominasoft.persistencia.dao.implementacion.PeriodoDePagoDAO;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(value = SpringRunner.class)
@SpringBootTest
class PeriodoDePagoDaoTest {

  @Autowired
  private PeriodoDePagoDAO periodoDePagoDAO;

  @Test
  void Test_buscarPeriodoActivo() {
    PeriodoDePago periodo = new PeriodoDePago();
    periodo.setFechaInicio(LocalDate.of(2021, 05, 30));
    periodo.setFechaFin(LocalDate.of(2021, 06, 30));
    periodoDePagoDAO.guardar(periodo);
    PeriodoDePago periodoDB = periodoDePagoDAO.buscarActivo();
    Assert.assertEquals(periodo.getId(), periodoDB.getId());
    Assert.assertEquals(periodo.getFechaInicio(), periodoDB.getFechaInicio());
    Assert.assertEquals(periodo.getFechaFin(), periodoDB.getFechaFin());
    Assert.assertEquals(periodo.isEstado(), periodoDB.isEstado());
    periodoDePagoDAO.eliminar(periodoDB.getId());
  }

  @Test
  void Test2_buscarPeriodoActivo() {
    PeriodoDePago periodoDB = periodoDePagoDAO.buscarActivo();
    Assert.assertEquals(null, periodoDB);
  }

  @Test
  void Test_buscarUnPeriodoActivo() {
    PeriodoDePago periodo = new PeriodoDePago();
    periodo.setId(1L);
    periodo.setFechaInicio(LocalDate.of(2021, 05, 30));
    periodo.setFechaFin(LocalDate.of(2021, 06, 30));
    periodo.setEstado(false);

    PeriodoDePago periodoDB = periodoDePagoDAO.buscarUno(1L);
    Assert.assertEquals(periodo.getId(), periodoDB.getId());
    Assert.assertEquals(periodo.getFechaInicio(), periodoDB.getFechaInicio());
    Assert.assertEquals(periodo.getFechaFin(), periodoDB.getFechaFin());
    Assert.assertEquals(periodo.isEstado(), periodoDB.isEstado());

  }

  @Test
  void Test2_buscarUnPeriodoActivo() {
    PeriodoDePago periodoDB = periodoDePagoDAO.buscarUno(100L);
    Assert.assertEquals(null, periodoDB);
  }

  @Test
  void Test_buscarUltimoPeriodo() {
    PeriodoDePago periodo = new PeriodoDePago();
    periodo.setFechaInicio(LocalDate.of(2021, 05, 30));
    periodo.setFechaFin(LocalDate.of(2021, 06, 30));
    periodoDePagoDAO.guardar(periodo);
    PeriodoDePago periodoDB = periodoDePagoDAO.buscarUltimo();
    Assert.assertEquals(periodo.getId(), periodoDB.getId());
    Assert.assertEquals(periodo.getFechaInicio(), periodoDB.getFechaInicio());
    Assert.assertEquals(periodo.getFechaFin(), periodoDB.getFechaFin());
    Assert.assertEquals(periodo.isEstado(), periodoDB.isEstado());
    periodoDePagoDAO.eliminar(periodoDB.getId());
  }

  @Test
  void Test_buscarTodosPeriodos() {
    List<PeriodoDePago> listaPeriodos = periodoDePagoDAO.buscarTodos();
    Assert.assertEquals(1, listaPeriodos.size());
  }
}

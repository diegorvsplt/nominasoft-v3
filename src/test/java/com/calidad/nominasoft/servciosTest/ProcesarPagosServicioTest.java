package com.calidad.nominasoft.servciosTest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import com.calidad.nominasoft.aplicacion.servicios.ProcesarPagosServicio;
import com.calidad.nominasoft.dominio.entidades.Afp;
import com.calidad.nominasoft.dominio.entidades.Contrato;
import com.calidad.nominasoft.dominio.entidades.Pago;
import com.calidad.nominasoft.dominio.entidades.PeriodoDePago;
import com.calidad.nominasoft.persistencia.dao.implementacion.ContratoDAO;
import com.calidad.nominasoft.persistencia.dao.implementacion.PagoDAO;
import com.calidad.nominasoft.persistencia.dao.implementacion.PeriodoDePagoDAO;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(value = SpringRunner.class)
@SpringBootTest
class ProcesarPagosServicioTest {

  @Autowired
  private ProcesarPagosServicio servicio;
  @Autowired
  private PeriodoDePagoDAO periodoDao;
  @Autowired
  private ContratoDAO contratoDao;
  @Autowired
  private PagoDAO pagoDao;

  @Test
  void Test_buscarPeriodoActivo() {
    PeriodoDePago periodo = new PeriodoDePago();
    periodo.setEstado(true);
    periodo.setFechaInicio(LocalDate.now());
    periodoDao.guardar(periodo);
    PeriodoDePago periodoDB = servicio.buscarPeriodoDePagoActivo();
    Assert.assertEquals(periodo.isEstado(), periodoDB.isEstado());
    Assert.assertEquals(periodo.getFechaInicio(), periodoDB.getFechaInicio());

    periodoDao.eliminar(periodoDB.getId());
  }

  @ParameterizedTest
  @MethodSource("generarData")
  void Test_consultarContratos(PeriodoDePago periodo, int tamanoEsperado, List<LocalDate> fechasEsperadas) {
    Contrato contrato1 = new Contrato();
    Contrato contrato2 = new Contrato();
    Contrato contrato3 = new Contrato();
    Contrato contrato4 = new Contrato();
    contrato1.setAnulado(false);
    contrato2.setAnulado(false);
    contrato3.setAnulado(false);
    contrato4.setAnulado(true);
    contrato1.setFechaFin(LocalDate.of(2021, 7, 2));
    contrato2.setFechaFin(LocalDate.of(2021, 7, 3));
    contrato3.setFechaFin(LocalDate.of(2021, 5, 30));
    contratoDao.guardar(contrato1);
    contratoDao.guardar(contrato2);
    contratoDao.guardar(contrato3);
    contratoDao.guardar(contrato4);
    List<Contrato> listaContratos = servicio.consultarContratosAProcesar(periodo);
    contratoDao.eliminarNUltimos(4);

    Assert.assertEquals(tamanoEsperado, listaContratos.size());

    if (tamanoEsperado != 0) {
      Assert.assertEquals(listaContratos.get(1).getFechaFin(), fechasEsperadas.get(0));
      Assert.assertEquals(listaContratos.get(0).getFechaFin(), fechasEsperadas.get(1));
    }

  }

  @Test
  void Test_registrarPagos() {
    PeriodoDePago periodo = new PeriodoDePago();
    periodo.setFechaInicio(LocalDate.of(2021, 6, 1));
    periodo.setFechaFin(LocalDate.of(2021, 7, 1));
    Afp afp = new Afp();
    afp.setDescuento(10);
    List<Contrato> listaContratos = new ArrayList<>();
    Contrato contrato1 = new Contrato();
    Contrato contrato2 = new Contrato();
    contrato1.setId(1L);
    contrato1.setAsignacionFamiliar(false);
    contrato1.setValorHora(10);
    contrato1.setHorasContratadasPorSemana(48);
    contrato1.setAfp(afp);
    contrato2.setId(2L);
    contrato2.setAsignacionFamiliar(false);
    contrato2.setValorHora(10);
    contrato2.setHorasContratadasPorSemana(48);
    contrato2.setAfp(afp);
    listaContratos.add(contrato1);
    listaContratos.add(contrato2);
    List<Pago> listaPagos = servicio.registrarPagos(periodo, listaContratos);

    Assert.assertEquals(2, listaPagos.size());
  }

  @Test
  void Test_buscarPeriodo() {
    PeriodoDePago periodo = new PeriodoDePago();
    periodo.setEstado(true);
    periodo.setFechaInicio(LocalDate.of(2021, 6, 1));
    periodo.setFechaFin(LocalDate.of(2021, 7, 1));
    periodoDao.guardar(periodo);
    PeriodoDePago periodoDB = servicio.buscarPeriodo();
    periodoDao.eliminar(periodoDB.getId());
    Assert.assertEquals(periodo.getFechaInicio(), periodoDB.getFechaInicio());
    Assert.assertEquals(periodo.getFechaFin(), periodoDB.getFechaFin());
  }

  @Test
  void Test_buscarPagos() {
    PeriodoDePago periodo = new PeriodoDePago();
    periodo.setEstado(true);
    periodo.setFechaInicio(LocalDate.of(2021, 07, 01));
    Pago pago1 = new Pago();
    Pago pago2 = new Pago();
    Pago pago3 = new Pago();
    pago1.setPeriodoDePago(periodo);
    pago2.setPeriodoDePago(periodo);
    pago3.setPeriodoDePago(periodo);
    periodoDao.guardar(periodo);
    pagoDao.guardar(pago1);
    pagoDao.guardar(pago2);
    pagoDao.guardar(pago3);
    PeriodoDePago periodoDB = periodoDao.buscarUltimo();
    List<Pago> listaPagos = servicio.buscarPagos(periodoDB.getId());
    pagoDao.eliminar(listaPagos.get(0).getId());
    pagoDao.eliminar(listaPagos.get(1).getId());
    pagoDao.eliminar(listaPagos.get(2).getId());
    periodoDao.eliminar(periodoDB.getId());
    Assert.assertEquals(3, listaPagos.size());
  }

  @Test
  void Test_guardarPagos() {
    PeriodoDePago periodo = new PeriodoDePago();
    periodo.setEstado(true);
    Pago pago1 = new Pago();
    Pago pago2 = new Pago();
    Pago pago3 = new Pago();
    List<Pago> listaPagos = new ArrayList<>();
    listaPagos.add(pago1);
    listaPagos.add(pago2);
    listaPagos.add(pago3);
    servicio.guardarPagos(listaPagos, periodo);
    List<Pago> listaPagosDB = pagoDao.buscarTodos();
    pagoDao.eliminar(listaPagosDB.get(0).getId());
    pagoDao.eliminar(listaPagosDB.get(1).getId());
    pagoDao.eliminar(listaPagosDB.get(2).getId());
    periodoDao.eliminar(servicio.buscarPeriodo().getId());
    Assert.assertEquals(3, listaPagosDB.size());
  }

  static Stream<Arguments> generarData() {
    PeriodoDePago periodo = new PeriodoDePago();
    PeriodoDePago periodo2 = new PeriodoDePago();
    periodo.setId(1L);
    periodo.setFechaInicio(LocalDate.of(2021, 06, 1));
    periodo.setFechaFin(LocalDate.of(2021, 07, 1));
    periodo.setEstado(true);

    periodo2.setId(1L);
    periodo2.setFechaInicio(LocalDate.of(2021, 6, 1));
    periodo2.setFechaFin(LocalDate.of(2021, 6, 29));
    periodo2.setEstado(true);

    List<LocalDate> fechasEsperadas = new ArrayList<>();
    fechasEsperadas.add(LocalDate.of(2021, 07, 2));
    fechasEsperadas.add(LocalDate.of(2021, 07, 3));

    return Stream.of(Arguments.of(periodo, 2, fechasEsperadas), Arguments.of(periodo2, 0, (Object) null),
        Arguments.of((Object) null, 0, (Object) null));
  }

}

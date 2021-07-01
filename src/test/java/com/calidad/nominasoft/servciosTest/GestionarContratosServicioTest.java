package com.calidad.nominasoft.servciosTest;

import java.time.LocalDate;

import com.calidad.nominasoft.aplicacion.servicios.GestionarContratoServicio;
import com.calidad.nominasoft.dominio.entidades.Contrato;
import com.calidad.nominasoft.dominio.entidades.Empleado;
import com.calidad.nominasoft.persistencia.dao.implementacion.ContratoDAO;
import com.calidad.nominasoft.persistencia.dao.implementacion.EmpleadoDAO;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(value = SpringRunner.class)
@SpringBootTest
class GestionarContratosServicioTest {

  @Autowired
  private GestionarContratoServicio servicio;

  @Autowired
  private ContratoDAO contratoDao;

  @Autowired
  private EmpleadoDAO empleadoDao;

  @Test
  void Test_buscarContratoPorEmpleadoDni() {
    Empleado empleado = new Empleado();
    empleado.setDni(00000000L);
    empleadoDao.guardar(empleado);
    Empleado empleadoDB = empleadoDao.buscarPorDni(00000000L);
    Contrato contrato = new Contrato();
    contrato.setCargo("Gerente");
    contrato.setEmpleado(empleadoDB);
    contrato.setAnulado(false);
    contratoDao.guardar(contrato);
    Contrato contratoDB = servicio.obtenerUltimoContrato(00000000L);
    contratoDao.eliminar(contratoDB.getId());
    empleadoDao.eliminar(empleadoDB.getId());

    Assert.assertEquals(empleado.getDni(), contratoDB.getEmpleado().getDni());
    Assert.assertEquals(contrato.getCargo(), contratoDB.getCargo());
  }

  @ParameterizedTest
  @CsvSource({ "2021-06-30,2021-07-01,true", "2021-07-01,2021-06-30,false" })
  void Test_compararFechasContratoAnteriorContratoActual(String fechaContratoAnterior, String fechaContratoNuevo,
      boolean esperado) {
    Contrato contratoNuevo = new Contrato();
    contratoNuevo.setFechaInicio(LocalDate.parse(fechaContratoNuevo));
    Contrato contratoAnterior = new Contrato();
    contratoAnterior.setFechaFin(LocalDate.parse(fechaContratoAnterior));
    boolean obtenido = servicio.validarFechaInicio(contratoNuevo, contratoAnterior);
    Assert.assertEquals(esperado, obtenido);
  }

  @ParameterizedTest
  @CsvSource({ "2021-07-01,40,10", "2021-04-01,40,10", "2023-07-01,7,9" })
  void Test_guardarContratoConValidaciones(String fechaFin, int horasContratadas, int valorHora) {
    Contrato contrato = new Contrato();
    contrato.setAnulado(false);
    contrato.setFechaInicio(LocalDate.of(2021, 03, 01));
    contrato.setFechaFin(LocalDate.parse(fechaFin));
    contrato.setHorasContratadasPorSemana(horasContratadas);
    contrato.setValorHora(valorHora);

    if (servicio.guardarContrato(contrato)) {
      Contrato contratoDB = contratoDao.buscarTodos().get(0);
      contratoDao.eliminar(contratoDB.getId());
      Assert.assertEquals(contrato.getFechaFin(), contratoDB.getFechaFin());
      Assert.assertEquals(contrato.getHorasContratadasPorSemana(), contratoDB.getHorasContratadasPorSemana());
      Assert.assertEquals(contrato.getValorHora(), contratoDB.getValorHora());
    }
  }

  @Test
  void Test_buscarEmpleadoPorDni() {
    Empleado empleado = new Empleado();
    empleado.setDni(00000000L);
    empleadoDao.guardar(empleado);
    Empleado empleadoDB = servicio.buscarEmpleado(00000000L);
    empleadoDao.eliminar(empleadoDB.getId());
    Assert.assertEquals(empleado.getDni(), empleadoDB.getDni());
  }

  @Test
  void Test_buscarTodasAfp() {
    Assert.assertEquals(3, servicio.buscarTodosAfp().size());
  }

  @Test
  void Test_anularContrato() {
    Contrato contrato = new Contrato();
    contrato.setAnulado(false);
    contratoDao.guardar(contrato);
    servicio.anularContrato(contratoDao.buscarContratosActivos().get(0).getId());
    Contrato contratoDB = contratoDao.buscarTodos().get(0);
    contratoDao.eliminar(contratoDB.getId());
    Assert.assertEquals(true, contratoDB.isAnulado());

  }
}

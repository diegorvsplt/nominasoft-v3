package com.calidad.nominasoft.daoTest;

import java.time.LocalDate;
import com.calidad.nominasoft.dominio.entidades.Empleado;
import com.calidad.nominasoft.persistencia.dao.implementacion.EmpleadoDAO;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(value = SpringRunner.class)
@SpringBootTest
class EmpleadoDaoTest {

  @Autowired
  private EmpleadoDAO empleadoDao;

  @Test
  void Test_buscarEmpleado() {

    Empleado empleado = new Empleado();
    // empleado.setId(1L);
    empleado.setNombre("Diego Rivasplata");
    empleado.setDni(99999999L);
    empleado.setDireccion("San Isidro, Trujillo");
    empleado.setEstadoCivil("Soltero");
    empleado.setFechaNacimiento(LocalDate.of(2003, 03, 05));
    empleado.setGradoAcademico("Primaria");
    empleado.setTelefono("999999999");
    empleadoDao.guardar(empleado);
    Empleado empleadoDB = empleadoDao.buscarPorDni(99999999L);
    // Assert.assertEquals(empleadoDB.getId(), empleado.getId());
    Assert.assertEquals(empleadoDB.getNombre(), empleado.getNombre());
    Assert.assertEquals(empleadoDB.getDni(), empleado.getDni());
    Assert.assertEquals(empleadoDB.getDireccion(), empleado.getDireccion());
    Assert.assertEquals(empleadoDB.getEstadoCivil(), empleado.getEstadoCivil());
    Assert.assertEquals(empleadoDB.getFechaNacimiento(), empleado.getFechaNacimiento());
    Assert.assertEquals(empleadoDB.getGradoAcademico(), empleado.getGradoAcademico());
    Assert.assertEquals(empleadoDB.getTelefono(), empleado.getTelefono());
    empleadoDao.eliminar(empleadoDB.getId());
  }

  @Test
  void Test2_buscarEmpleado() {
    Empleado empleadoDB = empleadoDao.buscarPorDni(12345679L);
    Assert.assertEquals(null, empleadoDB);
  }

  @Test
  void Test_buscarUnEmpleado() {
    Empleado empleado = new Empleado();
    empleado.setId(1L);
    empleado.setNombre("Diego Rivasplata");
    empleado.setDni(12345678L);
    empleado.setDireccion("San Isidro, Trujillo");
    empleado.setEstadoCivil("Soltero");
    empleado.setFechaNacimiento(LocalDate.of(2003, 03, 05));
    empleado.setGradoAcademico("Primaria");
    empleado.setTelefono("999999999");

    Empleado empleadoDB = empleadoDao.buscarUno(1L);
    Assert.assertEquals(empleadoDB.getId(), empleado.getId());
    Assert.assertEquals(empleadoDB.getNombre(), empleado.getNombre());
    Assert.assertEquals(empleadoDB.getDni(), empleado.getDni());
    Assert.assertEquals(empleadoDB.getDireccion(), empleado.getDireccion());
    Assert.assertEquals(empleadoDB.getEstadoCivil(), empleado.getEstadoCivil());
    Assert.assertEquals(empleadoDB.getFechaNacimiento(), empleado.getFechaNacimiento());
    Assert.assertEquals(empleadoDB.getGradoAcademico(), empleado.getGradoAcademico());
    Assert.assertEquals(empleadoDB.getTelefono(), empleado.getTelefono());
  }

  @Test
  void Test2_buscarUnEmpleado() {
    Empleado empleadoDB = empleadoDao.buscarUno(100L);
    Assert.assertEquals(null, empleadoDB);
  }

}

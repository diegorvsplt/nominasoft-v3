package com.calidad.nominasoft;

import com.calidad.nominasoft.dominio.dto.AfpDto;
import com.calidad.nominasoft.dominio.dto.ContratoDto;
import com.calidad.nominasoft.dominio.dto.EmpleadoDto;
import com.calidad.nominasoft.dominio.dto.PeriodoDto;
import com.calidad.nominasoft.dominio.entidades.Afp;
import com.calidad.nominasoft.dominio.entidades.Contrato;
import com.calidad.nominasoft.dominio.entidades.Empleado;
import com.calidad.nominasoft.dominio.entidades.PeriodoDePago;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(value = SpringRunner.class)
@SpringBootTest
class DtoTest {

  private ModelMapper modelMapper = new ModelMapper();

  @Test
  void convertirAfpDtoAAfpEntidad() {
    AfpDto afpDto = new AfpDto();
    afpDto.setIdDto(1L);
    afpDto.setNombreDto("Diego");
    afpDto.setDescuentoDto(12345678L);

    Afp afp = modelMapper.map(afpDto, Afp.class);
    Assert.assertEquals(afp.getId(), afpDto.getIdDto());
    Assert.assertEquals(afp.getNombre(), afpDto.getNombreDto());
    Assert.assertEquals(afp.getDescuento(), afpDto.getDescuentoDto(), 0);
  }

  @Test
  void convertirEmpleadoDtoAEmpleadoEntidad() {
    EmpleadoDto empleadoDto = new EmpleadoDto();
    empleadoDto.setIdDto(1L);
    empleadoDto.setNombreDto("Diego");
    empleadoDto.setDniDto(12345678L);
    empleadoDto.setGradoAcademicoDto("Bachiller");
    empleadoDto.setDireccionDto("Covicorti, Trujillo");
    empleadoDto.setEstadoCivilDto("Soltero");
    empleadoDto.setFechaNacimientoDto("2003-01-01");
    empleadoDto.setTelefonoDto("987865432");

    Empleado empleado = modelMapper.map(empleadoDto, Empleado.class);
    Assert.assertEquals(empleado.getId(), empleadoDto.getIdDto());
    Assert.assertEquals(empleado.getNombre(), empleadoDto.getNombreDto());
    Assert.assertEquals(empleado.getDni(), empleadoDto.getDniDto());
    Assert.assertEquals(empleado.getGradoAcademico(), empleadoDto.getGradoAcademicoDto());
    Assert.assertEquals(empleado.getDireccion(), empleadoDto.getDireccionDto());
    Assert.assertEquals(empleado.getEstadoCivil(), empleadoDto.getEstadoCivilDto());
    Assert.assertEquals(empleado.getFechaNacimiento(), empleadoDto.getFechaNacimientoDto());
    Assert.assertEquals(empleado.getTelefono(), empleadoDto.getTelefonoDto());
  }

  @Test
  void convertirContratoDtoAContratoEntidad() {
    ContratoDto contratoDto = new ContratoDto();
    contratoDto.setId(1L);
    contratoDto.setAsignacionFamiliar(true);
    contratoDto.setCargo("Administrador");
    contratoDto.setFechaInicio("2021-04-28");
    contratoDto.setFechaFin("2021-08-28");
    contratoDto.setHorasContratadasPorSemana(40);
    contratoDto.setValorHora(10);
    contratoDto.setAnulado(false);
    contratoDto.setAfp(new Afp());
    contratoDto.setEmpleado(new Empleado());

    Contrato contrato = modelMapper.map(contratoDto, Contrato.class);
    Assert.assertEquals(contrato.getId(), contratoDto.getId());
    Assert.assertEquals(contrato.getAsignacionFamiliar(), contratoDto.isAsignacionFamiliar());
    Assert.assertEquals(contrato.getCargo(), contratoDto.getCargo());
    Assert.assertEquals(contrato.getFechaInicio(), contratoDto.getFechaInicio());
    Assert.assertEquals(contrato.getFechaFin(), contratoDto.getFechaFin());
    Assert.assertEquals(contrato.getHorasContratadasPorSemana(), contratoDto.getHorasContratadasPorSemana());
    Assert.assertEquals(contrato.getValorHora(), contratoDto.getValorHora());
    Assert.assertEquals(contrato.isAnulado(), contratoDto.isAnulado());
    Assert.assertEquals(contrato.getAfp(), contratoDto.getAfp());
    Assert.assertEquals(contrato.getEmpleado(), contratoDto.getEmpleado());
  }

  @Test
  void convertirPeriodoDtoAPeriodoEntidad() {
    PeriodoDto periodoDto = new PeriodoDto();
    periodoDto.setIdDto(1L);
    periodoDto.setEstadoDto(true);
    periodoDto.setFechaInicioDto("2021-05-28");
    periodoDto.setFechaFinDto("2021-06-28");

    PeriodoDePago periodo = modelMapper.map(periodoDto, PeriodoDePago.class);
    Assert.assertEquals(periodo.getId(), periodoDto.getIdDto());
    Assert.assertEquals(periodo.isEstado(), periodoDto.isEstadoDto());
    Assert.assertEquals(periodo.getFechaInicio(), periodoDto.getFechaInicioDto());
    Assert.assertEquals(periodo.getFechaFin(), periodoDto.getFechaFinDto());
  }
}

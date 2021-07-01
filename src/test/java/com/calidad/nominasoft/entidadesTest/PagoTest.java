package com.calidad.nominasoft.entidadesTest;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import java.time.LocalDate;
import java.util.stream.Stream;

import com.calidad.nominasoft.dominio.entidades.Afp;
import com.calidad.nominasoft.dominio.entidades.Contrato;
import com.calidad.nominasoft.dominio.entidades.OtrosConceptos;
import com.calidad.nominasoft.dominio.entidades.Pago;
import com.calidad.nominasoft.dominio.entidades.PeriodoDePago;

@RunWith(value = SpringRunner.class)
@SpringBootTest
class PagoTest {

    @Test
    void Test_validarConstrutor() {
        PeriodoDePago periodo = new PeriodoDePago();
        Contrato contrato = new Contrato();
        OtrosConceptos otrosConceptos = new OtrosConceptos();
        Afp afp = new Afp();
        afp.setDescuento(10);
        periodo.setFechaInicio(LocalDate.parse("2021-05-20"));
        periodo.setFechaFin(LocalDate.parse("2021-06-20"));
        contrato.setAfp(afp);
        contrato.setHorasContratadasPorSemana(40);
        contrato.setValorHora(60);
        contrato.setAsignacionFamiliar(false);
        Pago pago = new Pago(periodo, contrato, otrosConceptos);
        Long id = 1L;
        pago.setId(id);
        Assert.assertEquals(pago.getId(), id);
        Assert.assertEquals(pago.getPeriodoDePago(), periodo);
        Assert.assertEquals(pago.getContrato(), contrato);
        Assert.assertEquals(pago.getOtrosConceptos(), otrosConceptos);
        Assert.assertEquals(160, pago.getTotalDeHoras());
        Assert.assertEquals(60, pago.getValorHora());
        Assert.assertEquals(0, pago.getMontoPorAsignacionFamiliar(), 0);
        Assert.assertEquals(10, pago.getPorcentajeDescuentoAFP(), 0);
    }

    @Test
    void Test_calcularSueldoBasico() {
        Pago pago = new Pago();
        pago.setTotalDeHoras(80);
        pago.setValorHora(40);
        int sueldoBasico_esperado = 3200;
        int sueldooBasico_obtenido = pago.calcularSueldoBasico();
        Assert.assertEquals(sueldoBasico_esperado, sueldooBasico_obtenido);
    }

    @Test
    void Test_calcularDescuentoAfp() {
        Pago pago = new Pago();
        pago.setTotalDeHoras(80);
        pago.setValorHora(40);
        pago.setPorcentajeDescuentoAFP(10);
        float descuentoAfp_esperado = 320f;
        float descuentoAfp_obtenido = pago.calcularDescuentoAFP();
        Assert.assertEquals(descuentoAfp_esperado, descuentoAfp_obtenido, 0);
    }

    @ParameterizedTest
    @MethodSource("generarDataIngresos")
    void Test1_calcularTotalIngresos(OtrosConceptos concepto, float esperado) {

        Contrato contrato = new Contrato();
        Pago pago = new Pago();
        contrato.setValorHora(20);
        contrato.setHorasContratadasPorSemana(40);
        pago.setOtrosConceptos(concepto);
        contrato.setAsignacionFamiliar(true);
        pago.setTotalDeHoras(80);
        pago.setValorHora(40);
        pago.setContrato(contrato);

        float totalDeIngresos_esperado = esperado;
        float totalDeIngresos_obtenido = pago.calcularTotalIngresos();
        Assert.assertEquals(totalDeIngresos_esperado, totalDeIngresos_obtenido, 0);
    }

    @ParameterizedTest
    @MethodSource("generarDataDescuentos")
    void Test_calcularTotalDescuentos(OtrosConceptos concepto, float esperado) {

        Contrato contrato = new Contrato();

        Pago pago = new Pago();
        pago.setTotalDeHoras(80);
        pago.setValorHora(40);
        pago.setPorcentajeDescuentoAFP(10);
        pago.setContrato(contrato);
        pago.setOtrosConceptos(concepto);
        float totalDeIngresos_esperado = esperado;
        float totalDeIngresos_obtenido = pago.calcularTotalDescuentos();
        Assert.assertEquals(totalDeIngresos_esperado, totalDeIngresos_obtenido, 0);
    }

    @Test
    void Test_calcularSueldoNeto() {
        OtrosConceptos conceptos = new OtrosConceptos(30, 80, 50, 10, 30, 80);
        Contrato contrato = new Contrato();
        contrato.setAsignacionFamiliar(true);
        PeriodoDePago periodo = new PeriodoDePago();
        Pago pago = new Pago();
        pago.setTotalDeHoras(80);
        pago.setValorHora(40);
        pago.setPorcentajeDescuentoAFP(5);
        pago.setContrato(contrato);
        pago.setPeriodoDePago(periodo);
        pago.setOtrosConceptos(conceptos);

        float sueldoNeto_esperado = 3080f;
        float sueldoNeto_obtenido = pago.calcularSueldoNeto();
        Assert.assertEquals(sueldoNeto_esperado, sueldoNeto_obtenido, 0);
    }

    @Test
    void Test_calcularTotalDeHoras() {
        LocalDate fechaInicio = LocalDate.of(2021, 1, 2);
        LocalDate fechaFin = LocalDate.of(2021, 2, 2);
        PeriodoDePago periodo = new PeriodoDePago();
        periodo.setFechaInicio(fechaInicio);
        periodo.setFechaFin(fechaFin);
        Contrato contrato = new Contrato();
        contrato.setHorasContratadasPorSemana(40);
        Pago pago = new Pago();
        pago.setPeriodoDePago(periodo);
        pago.setContrato(contrato);
        int totalHoras_esperado = 160;
        int totalHoras_obtenido = pago.calcularTotalDeHoras();
        Assert.assertEquals(totalHoras_esperado, totalHoras_obtenido);
    }

    static Stream<Arguments> generarDataDescuentos() {
        return Stream.of(Arguments.of(new OtrosConceptos(30, 80, 50, 10, 30, 80), 440f),
                Arguments.of((Object) null, 320f));
    }

    static Stream<Arguments> generarDataIngresos() {
        return Stream.of(Arguments.of(new OtrosConceptos(30, 80, 50, 10, 30, 80), 3440f),
                Arguments.of((Object) null, 3280f));
    }
}

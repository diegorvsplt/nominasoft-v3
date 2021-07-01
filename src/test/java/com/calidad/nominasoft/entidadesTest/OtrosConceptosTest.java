package com.calidad.nominasoft.entidadesTest;

import com.calidad.nominasoft.dominio.entidades.Contrato;
import com.calidad.nominasoft.dominio.entidades.OtrosConceptos;
import com.calidad.nominasoft.dominio.entidades.PeriodoDePago;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(value = SpringRunner.class)
@SpringBootTest
class OtrosConceptosTest {

    @Test
    void Test_validarSetterAndGetter() {
        OtrosConceptos conceptos = new OtrosConceptos();
        Contrato contrato = new Contrato();
        PeriodoDePago periodo = new PeriodoDePago();
        Long id = 1L;
        conceptos.setId(id);
        conceptos.setMontoAdelantos(1);
        conceptos.setMontoHorasAusente(2);
        conceptos.setMontoHorasExtra(3);
        conceptos.setMontoOtrosDescuentos(4);
        conceptos.setMontoOtrosIngresos(5);
        conceptos.setMontoReintegro(6);
        conceptos.setPeriodos(periodo);
        conceptos.setContrato(contrato);
        Assert.assertEquals(id, conceptos.getId());
        Assert.assertEquals(1, conceptos.getMontoAdelantos(), 0);
        Assert.assertEquals(2, conceptos.getMontoHorasAusente(), 0);
        Assert.assertEquals(3, conceptos.getMontoHorasExtra(), 0);
        Assert.assertEquals(4, conceptos.getMontoOtrosDescuentos(), 0);
        Assert.assertEquals(5, conceptos.getMontoOtrosIngresos(), 0);
        Assert.assertEquals(6, conceptos.getMontoReintegro(), 0);
    }

    @Test
    void Test_calcularTotalConceptosIngresos() {
        OtrosConceptos conceptos = new OtrosConceptos();
        conceptos.setMontoHorasExtra(40);
        conceptos.setMontoOtrosIngresos(80);
        conceptos.setMontoReintegro(50);
        float totalConceptosDeIngresos_esperado = 170;
        float totalConceptosDeIngresos_obtenido = conceptos.calcularTotalConceptosIngresos();
        Assert.assertEquals(totalConceptosDeIngresos_esperado, totalConceptosDeIngresos_obtenido, 0);
    }

    @Test
    void Test_calcularTotalConceptosDescuentos() {
        OtrosConceptos conceptos = new OtrosConceptos();
        conceptos.setMontoAdelantos(200);
        conceptos.setMontoOtrosDescuentos(120);
        conceptos.setMontoHorasAusente(80);
        float totalConceptosDeDescuentos_esperado = 400;
        float totalConceptosDeDescuentos_obtenido = conceptos.calcularTotalConceptosDescuentos();
        Assert.assertEquals(totalConceptosDeDescuentos_esperado, totalConceptosDeDescuentos_obtenido, 0);
    }
}

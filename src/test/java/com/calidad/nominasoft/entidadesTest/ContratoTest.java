package com.calidad.nominasoft.entidadesTest;

import java.time.LocalDate;

import com.calidad.nominasoft.dominio.entidades.Contrato;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(value = SpringRunner.class)
@SpringBootTest
class ContratoTest {

    @ParameterizedTest
    @CsvSource({ "2021-08-06,true,false", "2021-08-06,false,true", "2021-07-01,true,false", "2021-07-01,false,true",
            "2021-06-06,false,false", "2021-06-06,true,false" })
    void Test1_estaVigente(String fechaFin, boolean anulado, boolean esperado) {
        Contrato contrato = new Contrato();
        contrato.setFechaFin(LocalDate.parse(fechaFin));
        contrato.setAnulado(anulado);
        boolean estaVigente_esperado = esperado;
        boolean estaVigente_obtenido = contrato.estaVigente();
        Assert.assertEquals(estaVigente_esperado, estaVigente_obtenido);
    }

    @ParameterizedTest
    @CsvSource({ "2021-07-06,false", "2021-11-06,true", "2022-06-06,false", "2021-08-06,true", "2022-05-06,true" })
    void Test1_esValidaFechaFin(String arg, boolean esperado) {
        Contrato contrato = new Contrato();
        LocalDate fechaInicio = LocalDate.of(2021, 5, 6);
        contrato.setFechaInicio(fechaInicio);
        contrato.setFechaFin(LocalDate.parse(arg));
        boolean esValidoFechaFin_esperado = esperado;
        boolean esValidoFechaFin_obtenido = contrato.esValidaFechaFin();
        Assert.assertEquals(esValidoFechaFin_esperado, esValidoFechaFin_obtenido);
    }

    @ParameterizedTest
    @CsvSource({ "7,false", "8,true", "40,true", "39,false", "41,false" })
    void Test1_esValidoHorasContratadas(int horasContratadas, boolean esperado) {
        Contrato contrato = new Contrato();
        contrato.setHorasContratadasPorSemana(horasContratadas);
        boolean esValidoHorasContratadas_esperado = esperado;
        boolean esValidoHorasContratadas_obtenido = contrato.esValidoHorasContratadas();
        Assert.assertEquals(esValidoHorasContratadas_esperado, esValidoHorasContratadas_obtenido);
    }

    @ParameterizedTest
    @CsvSource({ "9,false", "10,true", "61,false", "60,true" })
    void Test1_esValidoValorHora(int valorHora, boolean esperado) {
        Contrato contrato = new Contrato();
        contrato.setValorHora(valorHora);
        boolean esValidoValorHora_esperado = esperado;
        boolean esValidoValorHora_obtenido = contrato.esValidoValorHora();
        Assert.assertEquals(esValidoValorHora_esperado, esValidoValorHora_obtenido);
    }

    @Test
    void Test1_calcularMontoAsignacionFamiliar() {
        Contrato contrato = new Contrato();
        contrato.setValorHora(50);
        contrato.setHorasContratadasPorSemana(8);
        contrato.setAsignacionFamiliar(true);
        float montoAsignacionFamiliar_esperado = 40;
        float montoAsignacionFamiliar_obtenido = contrato.calcularMontoAsignacionFamiliar();
        Assert.assertEquals(montoAsignacionFamiliar_esperado, montoAsignacionFamiliar_obtenido, 0);
    }

    @Test
    void Test2_calcularMontoAsignacionFamiliar() {
        Contrato contrato = new Contrato();
        contrato.setValorHora(50);
        contrato.setHorasContratadasPorSemana(8);
        contrato.setAsignacionFamiliar(false);
        float montoAsignacionFamiliar_esperado = 0;
        float montoAsignacionFamiliar_obtenido = contrato.calcularMontoAsignacionFamiliar();
        Assert.assertEquals(montoAsignacionFamiliar_esperado, montoAsignacionFamiliar_obtenido, 0);
    }

}

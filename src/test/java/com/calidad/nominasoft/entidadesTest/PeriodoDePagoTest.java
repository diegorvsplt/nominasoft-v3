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

import com.calidad.nominasoft.dominio.entidades.PeriodoDePago;

@RunWith(value = SpringRunner.class)
@SpringBootTest
class PeriodoDePagoTest {

    @ParameterizedTest
    @MethodSource("generarData")
    void Test_esFechaActualMayor(LocalDate fechaFin, boolean esperado) {
        PeriodoDePago periodo = new PeriodoDePago();
        periodo.setFechaFin(fechaFin);
        boolean fechaActualMayor_esperado = esperado;
        boolean fechaActualMayor_obtenido = periodo.esFechaActualMayor();
        Assert.assertEquals(fechaActualMayor_esperado, fechaActualMayor_obtenido);
    }

    @Test
    void Test_calcularTotalSemanas() {
        PeriodoDePago periodo = new PeriodoDePago();
        LocalDate fechaInicio = LocalDate.of(2021, 04, 15);
        LocalDate fechaFin = LocalDate.of(2021, 05, 15);
        periodo.setFechaInicio(fechaInicio);
        periodo.setFechaFin(fechaFin);
        int totalSemanas_esperado = 4;
        int totalSemanas_obtenido = periodo.getSemanasDelPeriodo();
        Assert.assertEquals(totalSemanas_esperado, totalSemanas_obtenido);
    }

    static Stream<Arguments> generarData() {
        return Stream.of(Arguments.of(LocalDate.of(2021, 7, 15), true), Arguments.of(LocalDate.of(2021, 5, 15), false),
                Arguments.of(LocalDate.now(), true));
    }
}

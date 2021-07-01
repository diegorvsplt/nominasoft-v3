package com.calidad.nominasoft.presentacion.controlador;

import java.util.List;

import com.calidad.nominasoft.aplicacion.servicios.ProcesarPagosServicio;
import com.calidad.nominasoft.dominio.entidades.Contrato;
import com.calidad.nominasoft.dominio.entidades.Pago;
import com.calidad.nominasoft.dominio.entidades.PeriodoDePago;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/pagos")
public class PagosController {

  @Autowired
  private ProcesarPagosServicio servicio;

  PeriodoDePago periodo;
  List<Contrato> contratos;

  @GetMapping("/")
  public String index(Model model) {

    periodo = servicio.buscarPeriodoDePagoActivo();
    contratos = servicio.consultarContratosAProcesar(periodo);

    model.addAttribute("periodo", periodo);
    model.addAttribute("contratos", contratos);
    return "procesarPago/index";
  }

  @PostMapping("/procesar")
  public String registrarPagos() {
    List<Pago> pagos = servicio.registrarPagos(periodo, contratos);
    servicio.guardarPagos(pagos, periodo);
    return "redirect:/pagos/registrados";
  }

  @GetMapping("/registrados")
  public String pagosRegistrados(Model model) {
    periodo = servicio.buscarPeriodo();
    List<Pago> pagos = servicio.buscarPagos(periodo.getId());

    model.addAttribute("listaPagos", pagos);
    return "procesarPago/registrados";
  }

}

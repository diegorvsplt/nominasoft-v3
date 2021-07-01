package com.calidad.nominasoft.presentacion.controlador;

import com.calidad.nominasoft.persistencia.dao.implementacion.PeriodoDePagoDAO;
import com.calidad.nominasoft.dominio.dto.PeriodoDto;
import com.calidad.nominasoft.dominio.entidades.PeriodoDePago;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequestMapping("/periodo")
public class PeriodoController {

  @Autowired
  private PeriodoDePagoDAO periodoDao;

  @GetMapping("/")
  public String index(Model model) {
    model.addAttribute("listaPeriodos", periodoDao.buscarTodos());
    model.addAttribute("periodo", new PeriodoDePago());
    return "periodo/index";
  }

  @PostMapping("/form")
  public String agregar(PeriodoDto periodoDto) {
    var periodo = convertirDTOaEntidad(periodoDto);
    periodoDao.guardar(periodo);
    return "redirect:/periodo/";
  }

  @GetMapping("/eliminar/{id}")
  public String eliminar(@PathVariable Long id) {
    periodoDao.eliminar(id);
    return "redirect:/periodo/";
  }

  @GetMapping("/buscarUno")
  @ResponseBody
  public PeriodoDePago buscarUno(Long id) {
    return periodoDao.buscarUno(id);
  }

  private PeriodoDePago convertirDTOaEntidad(PeriodoDto periodoDto) {
    return new ModelMapper().map(periodoDto, PeriodoDePago.class);
  }
}

package com.calidad.nominasoft.presentacion.controlador;

import com.calidad.nominasoft.aplicacion.servicios.GestionarContratoServicio;
import com.calidad.nominasoft.dominio.dto.ContratoDto;
import com.calidad.nominasoft.dominio.entidades.Contrato;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller()
@RequestMapping("/contrato")
public class GestionarContratoController {

    @Autowired
    private GestionarContratoServicio servicio;

    private static final String ULTIMOCONTRATO = "ultimoContrato";

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute(ULTIMOCONTRATO, new ContratoDto());
        return "gestionarContrato/index";
    }

    @GetMapping("/empleado")
    public String buscarEmpleado(@RequestParam String dni, Model model) {
        var empleado = servicio.buscarEmpleado(Long.parseLong(dni));
        var contrato = servicio.obtenerUltimoContrato(Long.parseLong(dni));
        if (contrato == null)
            model.addAttribute(ULTIMOCONTRATO, new ContratoDto());
        else {
            model.addAttribute(ULTIMOCONTRATO, contrato);
        }
        model.addAttribute("empleado", empleado);
        model.addAttribute("listaAfp", servicio.buscarTodosAfp());
        return "gestionarContrato/index";
    }

    @PostMapping("/form")
    public String agregar(ContratoDto contratoDto) {
        var contrato = convertirDtoAEntidad(contratoDto);
        servicio.guardarContrato(contrato);
        return "redirect:/contrato/";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable long id) {
        servicio.anularContrato(id);
        return "redirect:/contrato/";
    }

    private Contrato convertirDtoAEntidad(ContratoDto contratoDto) {
        return new ModelMapper().map(contratoDto, Contrato.class);
    }
}

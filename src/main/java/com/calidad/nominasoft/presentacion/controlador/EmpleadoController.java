package com.calidad.nominasoft.presentacion.controlador;

import com.calidad.nominasoft.persistencia.dao.implementacion.EmpleadoDAO;
import com.calidad.nominasoft.dominio.dto.EmpleadoDto;

import com.calidad.nominasoft.dominio.entidades.Empleado;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/empleado")
public class EmpleadoController {

    @Autowired
    private EmpleadoDAO empleadoDAO;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("listaEmpleados", empleadoDAO.buscarTodos());
        model.addAttribute("empleadoDto", new EmpleadoDto());
        return "empleado/index";
    }

    @PostMapping("/form")
    public String agregar(EmpleadoDto empleadoDto) {
        var empleado = convertirDTOaEntidad(empleadoDto);
        empleadoDAO.guardar(empleado);
        return "redirect:/empleado/";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id) {
        empleadoDAO.eliminar(id);
        return "redirect:/empleado/";
    }

    @GetMapping("/buscarUno")
    @ResponseBody
    public Empleado buscarUno(Long id) {
        return empleadoDAO.buscarUno(id);
    }

    private Empleado convertirDTOaEntidad(EmpleadoDto empleadoDto) {
        return new ModelMapper().map(empleadoDto, Empleado.class);
    }
}

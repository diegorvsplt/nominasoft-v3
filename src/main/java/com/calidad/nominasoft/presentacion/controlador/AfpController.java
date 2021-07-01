package com.calidad.nominasoft.presentacion.controlador;

import com.calidad.nominasoft.persistencia.dao.implementacion.AfpDAO;
import com.calidad.nominasoft.dominio.dto.AfpDto;
import com.calidad.nominasoft.dominio.entidades.Afp;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/afp")
public class AfpController {

    @Autowired
    private AfpDAO afpDAO;

    @GetMapping("/todos")
    public ResponseEntity<List<Afp>> getAllAfp() {
        List<Afp> listAfp = afpDAO.buscarTodos();
        return new ResponseEntity<>(listAfp, HttpStatus.OK);
    }

    @PostMapping("/agregar")
    public ResponseEntity<Afp> addAfp(@RequestBody AfpDto afpDto) {
        var nuevaAfp = convertirDTOaEntidad(afpDto);
        return new ResponseEntity<>(nuevaAfp, HttpStatus.CREATED);
    }

    private Afp convertirDTOaEntidad(AfpDto afpDto) {
        return new ModelMapper().map(afpDto, Afp.class);
    }
}

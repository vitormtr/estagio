package com.projeto.estagio.controller;

import com.projeto.estagio.model.RegistroModel;
import com.projeto.estagio.repository.RegistroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/registros")
public class RegistroController {

    @Autowired
    private RegistroRepository registroRepository;

    @GetMapping
    public List<RegistroModel> getAllRegistros() {
        return registroRepository.findAll();
    }
}


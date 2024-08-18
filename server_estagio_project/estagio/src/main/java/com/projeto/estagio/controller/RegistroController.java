package com.projeto.estagio.controller;

import com.projeto.estagio.model.RegistroModel;
import com.projeto.estagio.repository.RegistroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/registros")
public class RegistroController {
    @Autowired
    private RegistroRepository registroRepository;

    @GetMapping
    public ResponseEntity<List<RegistroModel>> getRegistros(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<RegistroModel> registros = registroRepository.findAll(pageable);
        List<RegistroModel> registrosList = registros.getContent();
        return new ResponseEntity<>(registrosList, HttpStatus.OK);
    }
    @GetMapping("/filtrar")
    public List<RegistroModel> getRegistroFiltradoPorNome(@RequestParam(required = false) String nome) {
        if (nome == null || nome.isEmpty()) {
            return registroRepository.findAll();
        } else {
            return registroRepository.findByNome(nome);
        }
    }
    @PostMapping("/adicionar")
    public ResponseEntity<RegistroModel> createRegistro(@RequestBody RegistroModel registro) {
        RegistroModel novoRegistro = registroRepository.save(registro);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoRegistro);
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<RegistroModel> atualizarRegistro(@PathVariable Long id, @RequestBody RegistroModel registro) {
        Optional<RegistroModel> registroExistente = registroRepository.findById(id);

        if (registroExistente.isPresent()) {
            RegistroModel novoRegistro = registroExistente.get();
            novoRegistro.setNome(registro.getNome());
            novoRegistro.setIdade(registro.getIdade());
            novoRegistro.setEmail(registro.getEmail());

            RegistroModel registroAtualizado = registroRepository.save(novoRegistro);
            return ResponseEntity.ok(registroAtualizado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @DeleteMapping("/deletar/{id}")
    @CrossOrigin(origins = "http://localhost:5173")
    public ResponseEntity<String> deletarRegistro(@PathVariable Long id) {
        if (registroRepository.existsById(id)) {
            registroRepository.deleteById(id);
            return ResponseEntity.ok("Registro deletado com sucesso.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Registro não encontrado.");
        }
    }



}


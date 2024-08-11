package com.projeto.estagio.controller;

import com.projeto.estagio.model.RegistroModel;
import com.projeto.estagio.repository.RegistroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/registros")
public class RegistroController {

    @Autowired
    private RegistroRepository registroRepository;

    @GetMapping
    public List<RegistroModel> getAllRegistros() {
        return registroRepository.findAll();
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
    public ResponseEntity<String> deletarRegistro(@PathVariable Long id) {
        if (registroRepository.existsById(id)) {
            registroRepository.deleteById(id);
            return ResponseEntity.ok("Registro deletado com sucesso.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Registro não encontrado.");
        }
    }



}


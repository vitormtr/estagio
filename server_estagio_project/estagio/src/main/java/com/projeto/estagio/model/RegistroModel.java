package com.projeto.estagio.model;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table (name = "registros")
public class RegistroModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String nome;
    private int idade;
    private String email;

}

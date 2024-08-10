package com.projeto.estagio.model;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table (name = "registros")
public class RegistroModel {
    @Id
    private long id;
    private String nome;
    private int idade;
    private String email;

}

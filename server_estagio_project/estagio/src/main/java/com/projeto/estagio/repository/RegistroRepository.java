package com.projeto.estagio.repository;
import com.projeto.estagio.model.RegistroModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "registros", path = "registros")

public interface RegistroRepository extends JpaRepository <RegistroModel, Long> {
    List<RegistroModel> findByNome(String nome);

}

package com.projeto.estagio.repository;
import com.projeto.estagio.model.RegistroModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
@RepositoryRestResource(collectionResourceRel = "registros", path = "registros")

public interface RegistroRepository extends JpaRepository <RegistroModel, Long> {

}

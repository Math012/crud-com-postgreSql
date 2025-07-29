package com.math012.crudpostgressql.infra.repository;

import com.math012.crudpostgressql.infra.entity.Categorias;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriasRepository extends JpaRepository<Categorias, Long> {
    Categorias findByNome(String nome);
}

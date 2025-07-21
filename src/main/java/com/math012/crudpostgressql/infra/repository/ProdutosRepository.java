package com.math012.crudpostgressql.infra.repository;

import com.math012.crudpostgressql.infra.entity.Produtos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutosRepository extends JpaRepository<Produtos, Long> {
}

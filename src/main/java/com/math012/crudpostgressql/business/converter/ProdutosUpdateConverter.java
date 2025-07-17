package com.math012.crudpostgressql.business.converter;

import com.math012.crudpostgressql.business.DTO.request.ProdutosRequestDTO;
import com.math012.crudpostgressql.infra.entity.Produtos;
import org.springframework.stereotype.Component;

@Component
public class ProdutosUpdateConverter {

    public Produtos atualizarProdutoEntity(ProdutosRequestDTO dto, Produtos entity){
        return Produtos.builder()
                .id(entity.getId())
                .nome(dto.getNome() != null ? dto.getNome() : entity.getNome())
                .descricao(dto.getDescricao() != null ? dto.getDescricao() : entity.getDescricao())
                .preco(dto.getPreco() != null ? dto.getPreco() : entity.getPreco())
                .build();
    }
}

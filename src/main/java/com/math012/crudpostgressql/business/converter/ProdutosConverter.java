package com.math012.crudpostgressql.business.converter;

import com.math012.crudpostgressql.business.DTO.request.ProdutosRequestDTO;
import com.math012.crudpostgressql.business.DTO.response.ProdutosResponseDTO;
import com.math012.crudpostgressql.infra.entity.Produtos;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProdutosConverter {

    public Produtos paraProdutoVindoDeProdutosRequestDTO(ProdutosRequestDTO produtosRequestDTO){
        return Produtos.builder()
                .nome(produtosRequestDTO.getNome())
                .descricao(produtosRequestDTO.getDescricao())
                .preco(produtosRequestDTO.getPreco())
                .build();
    }

    public ProdutosResponseDTO paraProdutosResponseVindoDeProduto(Produtos produtos){
        return ProdutosResponseDTO
                .builder()
                .id(produtos.getId())
                .nome(produtos.getNome())
                .descricao(produtos.getDescricao())
                .preco(produtos.getPreco())
                .build();
    }


    public List<ProdutosResponseDTO> paraListaProdutosResponseDTOVindoDeListaProdutos(List<Produtos> produtosList){
        return produtosList.stream().map(this::paraProdutosResponseVindoDeProduto).toList();
    }
}
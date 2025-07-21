package com.math012.crudpostgressql.business.converter;

import com.math012.crudpostgressql.business.DTO.request.ProdutosRequestDTO;
import com.math012.crudpostgressql.infra.entity.Produtos;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class ProdutosUpdateConverterTest {

    @InjectMocks
    ProdutosUpdateConverter produtosUpdateConverter;

    ProdutosRequestDTO produtosRequestDTO;

    Produtos produtos;

    Produtos produtosAtualizado;

    @BeforeEach
    void setup(){
        produtosRequestDTO = new ProdutosRequestDTO("Tv atualizada", "Tv full hd atualizada", null);
        produtos = new Produtos(1L, "Tv", "Tv full hd", 3000.00);
        produtosAtualizado = new Produtos(1L, "Tv atualizada", "Tv full hd atualizada", 3000.00);
    }

    @Test
    void deveAtualizarProdutoEntityComSucesso(){
        Produtos response = produtosUpdateConverter.atualizarProdutoEntity(produtosRequestDTO,produtos);
        assertEquals(produtosAtualizado, response);
    }
}

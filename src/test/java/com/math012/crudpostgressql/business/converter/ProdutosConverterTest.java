package com.math012.crudpostgressql.business.converter;

import com.math012.crudpostgressql.business.DTO.request.ProdutosRequestDTO;
import com.math012.crudpostgressql.business.DTO.request.ProdutosRequestDTOFixture;
import com.math012.crudpostgressql.business.DTO.response.ProdutosResponseDTO;
import com.math012.crudpostgressql.business.DTO.response.ProdutosResponseDTOFixture;
import com.math012.crudpostgressql.infra.entity.Produtos;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProdutosConverterTest {

    @InjectMocks
    ProdutosConverter converter;

    Produtos produtos;
    ProdutosRequestDTO produtosRequestDTO;
    ProdutosResponseDTO produtosResponseDTO;
    List<ProdutosResponseDTO> produtosResponseDTOList = new ArrayList<>();
    List<Produtos> produtosList = new ArrayList<>();

    @BeforeEach
    void setup(){
        produtos = new Produtos(1L, "Monitor", "Monitor full HD", 2000.00);
        produtosRequestDTO = ProdutosRequestDTOFixture.build("Monitor", "Monitor full HD", 2000.00);
        produtosResponseDTO = ProdutosResponseDTOFixture.build(1L, "Monitor", "Monitor full HD", 2000.00);
    }

    @Test
    void deveConverterParaProdutoVindoDeProdutosRequestDTOComSucesso(){
        Produtos response = converter.paraProdutoVindoDeProdutosRequestDTO(produtosRequestDTO);
        response.setId(1L);
        assertEquals(produtos, response);
    }

    @Test
    void deveConverterParaProdutosResponseVindoDeProdutoComSucesso(){
        ProdutosResponseDTO response = converter.paraProdutosResponseVindoDeProduto(produtos);
        assertEquals(produtosResponseDTO,response);
    }

    @Test
    void deveConverterParaListaProdutosResponseDTOVindoDeListaProdutos(){
        List<ProdutosResponseDTO> response = converter.paraListaProdutosResponseDTOVindoDeListaProdutos(produtosList);
        assertEquals(produtosResponseDTOList,response);
    }
}
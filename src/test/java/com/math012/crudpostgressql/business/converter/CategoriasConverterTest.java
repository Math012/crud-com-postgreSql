package com.math012.crudpostgressql.business.converter;

import com.math012.crudpostgressql.business.DTO.request.CategoriasRequestDTO;
import com.math012.crudpostgressql.business.DTO.request.CategoriasRequestDTOFixture;
import com.math012.crudpostgressql.business.DTO.response.CategoriasResponseDTO;
import com.math012.crudpostgressql.business.DTO.response.CategoriasResponseDTOFixture;
import com.math012.crudpostgressql.business.DTO.response.ProdutosResponseDTO;
import com.math012.crudpostgressql.infra.entity.Categorias;
import com.math012.crudpostgressql.infra.entity.Produtos;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CategoriasConverterTest {

    @InjectMocks
    CategoriasConverter converter;

    @Mock
    ProdutosConverter produtosConverter;

    CategoriasRequestDTO categoriasRequestDTO;
    CategoriasResponseDTO categoriasResponseDTOComListaProdutosNula;
    Categorias categoriasComListaProdutosNula;
    Categorias categoriasComListaProdutos;
    CategoriasResponseDTO categoriasResponseDTOComListaProdutos;
    List<Produtos> produtosList = new ArrayList<>();
    List<ProdutosResponseDTO> produtosResponseDTOList = new ArrayList<>();
    List<CategoriasResponseDTO> categoriasResponseDTOList = new ArrayList<>();
    List<Categorias> categoriasList = new ArrayList<>();

    @BeforeEach
    void setup(){
        produtosList.add(new Produtos(1L,"monitor","monitor full hd",1200.00));
        produtosResponseDTOList.add(new ProdutosResponseDTO(1L,"monitor","monitor full hd",1200.00));
        categoriasRequestDTO = CategoriasRequestDTOFixture.build("eletronico");

        categoriasComListaProdutosNula = new Categorias(1L,"eletronico",null);
        categoriasResponseDTOComListaProdutosNula = CategoriasResponseDTOFixture.build(1L,"eletronico",null);

        categoriasComListaProdutos = new Categorias(1L,"eletronico",produtosList);
        categoriasResponseDTOComListaProdutos =  CategoriasResponseDTOFixture.build(1L,"eletronico",produtosResponseDTOList);

        categoriasList.add(new Categorias(1L,"eletronico",produtosList));
        categoriasResponseDTOList.add(new CategoriasResponseDTO(1L,"eletronico",produtosResponseDTOList));
    }

    @Test
    void deveConverterDeCategoriasRequestDTOParaCategoriasComSucesso(){
        Categorias response = converter.paraCategoriasVindoDeCategoriasRequestDTO(categoriasRequestDTO);
        assertEquals(categoriasRequestDTO.getNome(), response.getNome());
    }

    @Test
    void deveConverterParaCategoriasResponseDTOVindoDeCategoriasComListaDeProdutosNulaComSucesso(){
        CategoriasResponseDTO response = converter.paraCategoriasResponseDTOVindoDeCategorias(categoriasComListaProdutosNula);
        assertEquals(categoriasResponseDTOComListaProdutosNula,response);
    }

    @Test
    void deveConverterParaCategoriasResponseDTOVindoDeCategoriasComListaDeProdutosComSucesso(){
        when(produtosConverter.paraListaProdutosResponseDTOVindoDeListaProdutos(produtosList)).thenReturn(produtosResponseDTOList);
        CategoriasResponseDTO response = converter.paraCategoriasResponseDTOVindoDeCategorias(categoriasComListaProdutos);
        assertEquals(categoriasResponseDTOComListaProdutos,response);
        assertEquals(categoriasResponseDTOComListaProdutos.getProdutos(),response.getProdutos());
    }

    @Test
    void deveConverterParaListaCategoriasResponseDTOVindoDeListaCategoriasComSucesso(){
        when(produtosConverter.paraListaProdutosResponseDTOVindoDeListaProdutos(produtosList)).thenReturn(produtosResponseDTOList);
        List<CategoriasResponseDTO> response = converter.paraListaCategoriasResponseDTOVindoDeListaCategorias(categoriasList);
        assertEquals(categoriasResponseDTOList, response);
    }
}
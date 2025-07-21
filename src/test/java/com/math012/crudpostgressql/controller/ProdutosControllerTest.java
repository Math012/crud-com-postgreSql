package com.math012.crudpostgressql.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.math012.crudpostgressql.business.DTO.request.ProdutosRequestDTO;
import com.math012.crudpostgressql.business.DTO.request.ProdutosRequestDTOFixture;
import com.math012.crudpostgressql.business.DTO.response.ProdutosResponseDTO;
import com.math012.crudpostgressql.business.DTO.response.ProdutosResponseDTOFixture;
import com.math012.crudpostgressql.business.service.ProdutosService;
import com.math012.crudpostgressql.infra.entity.Categorias;
import com.math012.crudpostgressql.infra.entity.Produtos;
import com.math012.crudpostgressql.infra.exceptions.ResourceNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class ProdutosControllerTest {

    @InjectMocks
    ProdutosController controller;

    @Mock
    ProdutosService service;

    private final ObjectMapper objectMapper = new ObjectMapper();

    private MockMvc mockMvc;

    ProdutosRequestDTO produtosRequestDTO;

    ProdutosRequestDTO produtosRequestDTOUpdate;

    ProdutosRequestDTO produtosRequestDTONulo;

    ProdutosRequestDTO produtosRequestDTOVazio;

    ProdutosResponseDTO produtosResponseDTO;

    ProdutosResponseDTO produtosResponseDTOUpdate;

    List<ProdutosResponseDTO> produtosResponseDTOList = new ArrayList<>();

    Produtos produtosEntity;

    Produtos produtosEntityUpdate;

    List<Produtos> produtosList = new ArrayList<>();

    Categorias categoriasEntity;

    Long idCategoria;

    Long idProduto;

    String json;

    String url;

    @BeforeEach
    void setup() throws JsonProcessingException {
        url = "/produtos";
        mockMvc = MockMvcBuilders.standaloneSetup(controller).alwaysDo(print()).build();
        idCategoria = 1L;
        idProduto = 1L;
        produtosRequestDTO = ProdutosRequestDTOFixture.build("Tv", "Tv full hd", 3000.00);
        produtosResponseDTO = ProdutosResponseDTOFixture.build(1L, "Tv", "Tv full hd", 3000.00);
        produtosResponseDTOList.add(new ProdutosResponseDTO(1L, "Tv", "Tv full hd", 3000.00));
        produtosResponseDTOUpdate = ProdutosResponseDTOFixture.build(1L, "Tv atualizada", "Tv full hd", 3000.00);
        produtosList.add(new Produtos(1L, "Tv", "Tv full hd", 3000.00));
        categoriasEntity = new Categorias(1L, "Eletronico",produtosList);
        produtosEntity = new Produtos(1L, "Tv", "Tv full hd", 3000.00);
        produtosEntityUpdate = new Produtos(1L, "Tv atualizada", "Tv full hd", 3000.00);
        produtosRequestDTONulo = ProdutosRequestDTOFixture.build(null, "Tv full hd", 3000.00);
        produtosRequestDTOVazio = ProdutosRequestDTOFixture.build("Tv", "  ", 3000.00);
        produtosRequestDTOUpdate = ProdutosRequestDTOFixture.build("Tv atualizada", null, null);
        json = objectMapper.writeValueAsString(produtosRequestDTO);
    }

    @Test
    void deveCriarProdutoComSucesso() throws Exception {
        when(service.criarProduto(produtosRequestDTO,idCategoria)).thenReturn(produtosResponseDTO);
        mockMvc.perform(post(url+"/{id}",idCategoria)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(json)
        ).andExpect(status().isOk());
    }

    @Test
    void deveFalharAoCriarProdutoComIdCategoriaNulo() throws Exception {
        Long id = null;
        mockMvc.perform(post(url+"/{id}", id)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(json)
        ).andExpect(status().isNotFound());
    }

    @Test
    void deveListarTodosProdutos() throws Exception {
        when(service.todosProdutos()).thenReturn(produtosResponseDTOList);
        mockMvc.perform(get(url+"/todos")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(json)
        ).andExpect(status().isOk());
    }

    @Test
    void deveEncontrarProdutoViaIdComSucesso() throws Exception {
        when(service.encontrarProdutoViaId(idProduto)).thenReturn(produtosResponseDTO);
        mockMvc.perform(get(url+"/id/{id}",idProduto)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(json)
        ).andExpect(status().isOk());
    }

    @Test
    void deveFalharAoEncontrarProdutoViaIdComIdNulo() throws Exception {
        Long id = null;
        mockMvc.perform(get(url+"/id/{id}",id)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(json)
        ).andExpect(status().isNotFound());
    }

    @Test
    void deveAtualizarProdutoPorIdComSucesso() throws Exception {
        when(service.atualizarProdutoPorId(idProduto,produtosRequestDTO)).thenReturn(produtosResponseDTO);
        mockMvc.perform(put(url+"/atualizar/{id}",idProduto)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(json)
        ).andExpect(status().isOk());
    }

    @Test
    void deveFalharAoAtualizarProdutoPorIdComIdNulo() throws Exception {
        Long id = null;
        mockMvc.perform(put(url+"/atualizar/{id}",id)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(json)
        ).andExpect(status().isNotFound());
    }

    @Test
    void deveDeletarProdutoViaIdComSucesso() throws Exception {
        doNothing().when(service).deletarProdutoViaId(idProduto);
        mockMvc.perform(delete(url+"/deletar/{id}",idProduto)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(json)
        ).andExpect(status().isOk());
    }

    @Test
    void deveFalharAoDeletarProdutoViaIdComIdNulo() throws Exception {
        Long id = null;
        mockMvc.perform(delete(url+"/deletar/{id}",id)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(json)
        ).andExpect(status().isNotFound());
    }
}
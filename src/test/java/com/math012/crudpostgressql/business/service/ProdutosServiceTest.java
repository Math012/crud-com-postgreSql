package com.math012.crudpostgressql.business.service;

import com.math012.crudpostgressql.business.DTO.request.ProdutosRequestDTO;
import com.math012.crudpostgressql.business.DTO.request.ProdutosRequestDTOFixture;
import com.math012.crudpostgressql.business.DTO.response.ProdutosResponseDTO;
import com.math012.crudpostgressql.business.DTO.response.ProdutosResponseDTOFixture;
import com.math012.crudpostgressql.business.converter.ProdutosConverter;
import com.math012.crudpostgressql.business.converter.ProdutosUpdateConverter;
import com.math012.crudpostgressql.infra.entity.Categorias;
import com.math012.crudpostgressql.infra.entity.Produtos;
import com.math012.crudpostgressql.infra.exceptions.RequestException;
import com.math012.crudpostgressql.infra.exceptions.ResourceNotFoundException;
import com.math012.crudpostgressql.infra.repository.CategoriasRepository;
import com.math012.crudpostgressql.infra.repository.ProdutosRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProdutosServiceTest {

    @InjectMocks
    ProdutosService produtosService;

    @Mock
    CategoriasRepository categoriasRepository;

    @Mock
    ProdutosRepository produtosRepository;

    @Mock
    ProdutosConverter produtosConverter;

    @Mock
    ProdutosUpdateConverter produtosUpdateConverter;

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

    @BeforeEach
    void setup(){
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
    }

    @Test
    void deveCriarProdutoComSucesso(){
        when(categoriasRepository.findById(idCategoria)).thenReturn(Optional.ofNullable(categoriasEntity));
        when(produtosConverter.paraProdutoVindoDeProdutosRequestDTO(produtosRequestDTO)).thenReturn(produtosEntity);
        when(categoriasRepository.save(categoriasEntity)).thenReturn(categoriasEntity);
        when(produtosConverter.paraProdutosResponseVindoDeProduto(produtosEntity)).thenReturn(produtosResponseDTO);
        ProdutosResponseDTO response = produtosService.criarProduto(produtosRequestDTO,idCategoria);
        assertEquals(produtosResponseDTO,response);
        verify(categoriasRepository,times(1)).findById(idCategoria);
        verify(produtosConverter,times(1)).paraProdutoVindoDeProdutosRequestDTO(produtosRequestDTO);
        verify(categoriasRepository,times(1)).save(categoriasEntity);
        verify(produtosConverter,times(1)).paraProdutosResponseVindoDeProduto(produtosEntity);
    }

    @Test
    void deveFalharAoCriarProdutoComIdCategoriaNulo(){
        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () ->{
            produtosService.criarProduto(produtosRequestDTO,idCategoria);
        });
        assertEquals("Erro: o id da categoria: " + idCategoria + " não foi encontrado, tente novamente!",exception.getMessage());
    }

    @Test
    void deveFalharAoCriarProdutoComProdutosRequestDTONulo(){
        RequestException exception = assertThrows(RequestException.class, () ->{
            when(categoriasRepository.findById(idCategoria)).thenReturn(Optional.ofNullable(categoriasEntity));
            produtosService.criarProduto(produtosRequestDTONulo,idCategoria);
        });
        assertEquals("Erro ao salvar o produto: campos inválidos",exception.getMessage());
    }

    @Test
    void deveFalharAoCriarProdutoComProdutosRequestDTOVazio(){
        RequestException exception = assertThrows(RequestException.class, () ->{
            when(categoriasRepository.findById(idCategoria)).thenReturn(Optional.ofNullable(categoriasEntity));
            produtosService.criarProduto(produtosRequestDTOVazio,idCategoria);
        });
        assertEquals("Erro ao salvar o produto: campos inválidos",exception.getMessage());
    }

    @Test
    void deveListarTodosProdutosComSucesso(){
        when(produtosRepository.findAll()).thenReturn(produtosList);
        when(produtosConverter.paraListaProdutosResponseDTOVindoDeListaProdutos(produtosList)).thenReturn(produtosResponseDTOList);
        List<ProdutosResponseDTO> response = produtosService.todosProdutos();
        assertEquals(produtosResponseDTOList,response);
    }

    @Test
    void deveEncontrarProdutoViaIdComSucesso(){
        when(produtosRepository.findById(idProduto)).thenReturn(Optional.ofNullable(produtosEntity));
        when(produtosConverter.paraProdutosResponseVindoDeProduto(produtosEntity)).thenReturn(produtosResponseDTO);
        ProdutosResponseDTO response = produtosService.encontrarProdutoViaId(idProduto);
        assertEquals(produtosResponseDTO,response);
    }

    @Test
    void deveFalharAoEncontrarProdutoViaIdNulo() {
        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () -> {

            produtosService.encontrarProdutoViaId(idProduto);
            ;
        });
        assertEquals("Erro: o id do produto: " + idProduto + " não foi encontrado, tente novamente!", exception.getMessage());
    }

    @Test
    void deveAtualizarProdutoPorIdComSucesso(){
        when(produtosRepository.findById(idProduto)).thenReturn(Optional.ofNullable(produtosEntity));
        when(produtosUpdateConverter.atualizarProdutoEntity(produtosRequestDTOUpdate,produtosEntity)).thenReturn(produtosEntityUpdate);
        when(produtosRepository.save(produtosEntityUpdate)).thenReturn(produtosEntityUpdate);
        when(produtosConverter.paraProdutosResponseVindoDeProduto(produtosEntityUpdate)).thenReturn(produtosResponseDTOUpdate);
        ProdutosResponseDTO response = produtosService.atualizarProdutoPorId(idProduto,produtosRequestDTOUpdate);
        assertEquals(produtosResponseDTOUpdate,response);
    }

    @Test
    void deveFalharAoAtualizarProdutoComIdNulo() {
        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () -> {

            produtosService.atualizarProdutoPorId(idProduto,produtosRequestDTOUpdate);
            ;
        });
        assertEquals("Erro: o id do produto: " + idProduto + " não foi encontrado, tente novamente!", exception.getMessage());
    }

    @Test
    void deveDeletarProdutosViaIdComSucesso(){
        when(produtosRepository.findById(idProduto)).thenReturn(Optional.ofNullable(produtosEntity));
        doNothing().when(produtosRepository).delete(produtosEntity);
        produtosService.deletarProdutoViaId(idProduto);
        verify(produtosRepository,times(1)).delete(produtosEntity);
        verifyNoMoreInteractions(produtosRepository);
    }

    @Test
    void deveFalharAoDeletarProdutosViaIdComIdNulo() {
        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () -> {
            produtosService.deletarProdutoViaId(idProduto);
        });
        assertEquals("Erro: o id do produto: " + idProduto + " não foi encontrado, tente novamente!", exception.getMessage());
    }
}
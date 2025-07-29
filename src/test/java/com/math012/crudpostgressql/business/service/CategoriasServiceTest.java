package com.math012.crudpostgressql.business.service;

import com.math012.crudpostgressql.business.DTO.request.CategoriasRequestDTO;
import com.math012.crudpostgressql.business.DTO.request.CategoriasRequestDTOFixture;
import com.math012.crudpostgressql.business.DTO.response.CategoriasResponseDTO;
import com.math012.crudpostgressql.business.DTO.response.CategoriasResponseDTOFixture;
import com.math012.crudpostgressql.business.converter.CategoriasConverter;
import com.math012.crudpostgressql.infra.entity.Categorias;
import com.math012.crudpostgressql.infra.exceptions.RequestException;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CategoriasServiceTest {

    @InjectMocks
    CategoriasService service;

    @Mock
    CategoriasConverter converter;

    @Mock
    CategoriasRepository categoriasRepository;

    Categorias categorias;
    List<Categorias> categoriasList = new ArrayList<>();
    List<CategoriasResponseDTO> categoriasResponseDTOList = new ArrayList<>();
    CategoriasRequestDTO categoriasRequestDTO;
    CategoriasRequestDTO categoriasRequestDTONulo;
    CategoriasRequestDTO categoriasRequestDTOVazio;
    CategoriasResponseDTO categoriasResponseDTO;

    @BeforeEach
    void setup(){
        categorias = new Categorias(1L,"eletronico", null);
        categoriasRequestDTO = CategoriasRequestDTOFixture.build("eletronico");
        categoriasRequestDTONulo = CategoriasRequestDTOFixture.build(null);
        categoriasRequestDTOVazio = CategoriasRequestDTOFixture.build(" ");
        categoriasResponseDTO = CategoriasResponseDTOFixture.build(1L,"eletronico", null);
        categoriasList.add(new Categorias(1L,"eletronico", null));
        categoriasResponseDTOList.add(new CategoriasResponseDTO(1L,"eletronico", null));
    }

    @Test
    void deveCriarCategoriaComSucesso(){
        when(converter.paraCategoriasVindoDeCategoriasRequestDTO(categoriasRequestDTO)).thenReturn(categorias);
        when(categoriasRepository.save(categorias)).thenReturn(categorias);
        when(converter.paraCategoriasResponseDTOVindoDeCategorias(categorias)).thenReturn(categoriasResponseDTO);
        CategoriasResponseDTO response = service.criarCategoria(categoriasRequestDTO);
        assertEquals(categoriasResponseDTO,response);
        verify(converter,times(1)).paraCategoriasVindoDeCategoriasRequestDTO(categoriasRequestDTO);
        verify(categoriasRepository,times(1)).save(categorias);
        verify(converter,times(1)).paraCategoriasResponseDTOVindoDeCategorias(categorias);
    }

    @Test
    void deveFalharAoCriarCategoriaComCamposNulos(){
        RequestException requestException = assertThrows(RequestException.class,()->{
            service.criarCategoria(categoriasRequestDTONulo);
        });
        assertEquals("Erro ao salvar a categoria: campos inválidos",requestException.getMessage());
    }

    @Test
    void deveFalharAoCriarCategoriaComCamposVazios(){
        RequestException requestException = assertThrows(RequestException.class,()->{
            service.criarCategoria(categoriasRequestDTOVazio);
        });
        assertEquals("Erro ao salvar a categoria: campos inválidos",requestException.getMessage());
    }

    @Test
    void deveListarProdutosComSucesso(){
        when(categoriasRepository.findAll()).thenReturn(categoriasList);
        when(converter.paraListaCategoriasResponseDTOVindoDeListaCategorias(categoriasList)).thenReturn(categoriasResponseDTOList);
        List<CategoriasResponseDTO> response = service.listaProdutos();
        assertEquals(categoriasResponseDTOList, response);
        verify(categoriasRepository,times(1)).findAll();
        verify(converter,times(1)).paraListaCategoriasResponseDTOVindoDeListaCategorias(categoriasList);
    }

    @Test
    void verifyFieldsDeveRetornarFalseComCategoriasRequestDTONulo(){
        Boolean response = service.verifyFields(categoriasRequestDTONulo);
        assertEquals(false, response);
    }

    @Test
    void verifyFieldsDeveRetornarFalseComCategoriasRequestDTOVazio(){
        Boolean response = service.verifyFields(categoriasRequestDTOVazio);
        assertEquals(false, response);
    }

    @Test
    void verifyFieldsDeveRetornarTrueComCategoriasRequestDTOValido(){
        Boolean response = service.verifyFields(categoriasRequestDTO);
        assertEquals(true, response);
    }
}
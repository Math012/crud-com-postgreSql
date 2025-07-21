package com.math012.crudpostgressql.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.math012.crudpostgressql.business.DTO.request.CategoriasRequestDTO;
import com.math012.crudpostgressql.business.DTO.request.CategoriasRequestDTOFixture;
import com.math012.crudpostgressql.business.DTO.response.CategoriasResponseDTO;
import com.math012.crudpostgressql.business.DTO.response.CategoriasResponseDTOFixture;
import com.math012.crudpostgressql.business.service.CategoriasService;
import com.math012.crudpostgressql.infra.entity.Categorias;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.http.MediaType;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class CategoriasControllerTest {

    @InjectMocks
    CategoriasController controller;

    @Mock
    CategoriasService service;

    private final ObjectMapper objectMapper = new ObjectMapper();

    private MockMvc mockMvc;

    String url = "/categorias";
    String json;
    List<CategoriasResponseDTO> categoriasResponseDTOList = new ArrayList<>();
    CategoriasResponseDTO categoriasResponseDTO;
    CategoriasRequestDTO categoriasRequestDTO;

    @BeforeEach
    void setup() throws JsonProcessingException {

        mockMvc = MockMvcBuilders.standaloneSetup(controller).alwaysDo(print()).build();
        categoriasRequestDTO = CategoriasRequestDTOFixture.build("eletronico");
        categoriasResponseDTO = CategoriasResponseDTOFixture.build(1L,"eletronico", null);
        categoriasResponseDTOList.add(new CategoriasResponseDTO(1L,"eletronico", null));
        json = objectMapper.writeValueAsString(categoriasResponseDTO);
    }

    @Test
    void deveListarTodasCategoriasComSucesso() throws Exception {
        when(service.listaProdutos()).thenReturn(categoriasResponseDTOList);
        mockMvc.perform(get(url)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk());
        verify(service,times(1)).listaProdutos();
    }

    @Test
    void deveCriarCategoriaComSucesso() throws Exception {
        when(service.criarCategoria(categoriasRequestDTO)).thenReturn(categoriasResponseDTO);
        mockMvc.perform(post(url+"/create")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(json)
        ).andExpect(status().isOk());
    }

    @Test
    void deveFalharAoCriarCategoriaComBodyNulo() throws Exception {
        mockMvc.perform(post(url+"/create")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        ).andExpect(status().isBadRequest());
    }
}
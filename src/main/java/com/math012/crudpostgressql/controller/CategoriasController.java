package com.math012.crudpostgressql.controller;

import com.math012.crudpostgressql.business.DTO.request.CategoriasRequestDTO;
import com.math012.crudpostgressql.business.DTO.response.CategoriasResponseDTO;
import com.math012.crudpostgressql.business.service.CategoriasService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/categorias")
public class CategoriasController {

    private final CategoriasService service;

    @GetMapping
    @Operation(summary = "Endpoint para Listar todas as categorias", description = "Listando todas as categorias registradas")
    @ApiResponse(responseCode = "200", description = "Lista retornada com sucesso")
    public ResponseEntity<List<CategoriasResponseDTO>> todasCategorias(){
        return ResponseEntity.ok(service.listaProdutos());
    }

    @PostMapping("/create")
    @Operation(summary = "Endpoint para Criar uma categoria", description = "Criando uma categoria para produtos")
    @ApiResponse(responseCode = "200", description = "Categoria criada com sucesso")
    @ApiResponse(responseCode = "400", description = "Erro ao criar categoria, campos inválidos")
    @ApiResponse(responseCode = "500", description = "Erro no servidor")
    public ResponseEntity<CategoriasResponseDTO> criarCategoria(@RequestBody CategoriasRequestDTO categorias){
        return ResponseEntity.ok(service.criarCategoria(categorias));
    }
}
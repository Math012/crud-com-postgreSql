package com.math012.crudpostgressql.controller;

import com.math012.crudpostgressql.business.DTO.request.CategoriasRequestDTO;
import com.math012.crudpostgressql.business.DTO.response.CategoriasResponseDTO;
import com.math012.crudpostgressql.business.service.CategoriasService;
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
    public ResponseEntity<List<CategoriasResponseDTO>> todasCategorias(){
        return ResponseEntity.ok(service.listaProdutos());
    }

    @PostMapping("/create")
    public ResponseEntity<CategoriasResponseDTO> criarCategoria(@RequestBody CategoriasRequestDTO categorias){
        return ResponseEntity.ok(service.criarCategoria(categorias));
    }
}
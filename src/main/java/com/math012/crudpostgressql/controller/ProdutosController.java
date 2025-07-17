package com.math012.crudpostgressql.controller;

import com.math012.crudpostgressql.business.DTO.request.ProdutosRequestDTO;
import com.math012.crudpostgressql.business.DTO.response.ProdutosResponseDTO;
import com.math012.crudpostgressql.business.service.ProdutosService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/produtos")
public class ProdutosController {

    private final ProdutosService service;


    @PostMapping("/{id}")
    public ResponseEntity<ProdutosResponseDTO> criarProduto(@RequestBody ProdutosRequestDTO produtos, @PathVariable Long id){
        return ResponseEntity.ok(service.criarProduto(produtos,id));
    }

    @GetMapping("/todos")
    public ResponseEntity<List<ProdutosResponseDTO>> todosProdutos(){
        return ResponseEntity.ok(service.todosProdutos());
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<ProdutosResponseDTO> encontrarProdutoViaId(@PathVariable Long id){
        return ResponseEntity.ok(service.encontrarProdutoViaId(id));
    }


    @PutMapping("/atualizar/{id}")
    public ResponseEntity<ProdutosResponseDTO> atualizarProdutoPorId(@PathVariable Long id, @RequestBody ProdutosRequestDTO produtosRequestDTO){
        return ResponseEntity.ok(service.atualizarProdutoPorId(id, produtosRequestDTO));
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Void> deletarProdutoViaId(@PathVariable Long id){
        service.deletarProdutoViaId(id);
        return ResponseEntity.ok().build();
    }


}

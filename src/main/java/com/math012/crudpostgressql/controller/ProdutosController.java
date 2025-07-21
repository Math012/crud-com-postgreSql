package com.math012.crudpostgressql.controller;

import com.math012.crudpostgressql.business.DTO.request.ProdutosRequestDTO;
import com.math012.crudpostgressql.business.DTO.response.ProdutosResponseDTO;
import com.math012.crudpostgressql.business.service.ProdutosService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
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
    @Operation(summary = "Endpoint para criar um produto", description = "Criando um produto")
    @ApiResponse(responseCode = "200", description = "Produto criado com sucesso")
    @ApiResponse(responseCode = "400", description = "Erro ao criar o produto, campos inválidos")
    @ApiResponse(responseCode = "404", description = "Erro ao criar o produto, id da categoria não encontrado")
    @ApiResponse(responseCode = "500", description = "Erro no servidor")
    public ResponseEntity<ProdutosResponseDTO> criarProduto(@RequestBody ProdutosRequestDTO produtos, @PathVariable Long id){
        return ResponseEntity.ok(service.criarProduto(produtos,id));
    }

    @GetMapping("/todos")
    @Operation(summary = "Endpoint para listar todos os produtos", description = "Listando todos os produtos registrados")
    @ApiResponse(responseCode = "200", description = "Lista de produtos retornada com sucesso")
    public ResponseEntity<List<ProdutosResponseDTO>> todosProdutos(){
        return ResponseEntity.ok(service.todosProdutos());
    }

    @GetMapping("/id/{id}")
    @Operation(summary = "Endpoint para encontrar um produto via id", description = "Buscando produto via id")
    @ApiResponse(responseCode = "200", description = "Produto encontrado com sucesso")
    @ApiResponse(responseCode = "404", description = "Erro ao buscar o produto, id do produto não encontrado")
    @ApiResponse(responseCode = "500", description = "Erro no servidor")
    public ResponseEntity<ProdutosResponseDTO> encontrarProdutoViaId(@PathVariable Long id){
        return ResponseEntity.ok(service.encontrarProdutoViaId(id));
    }


    @PutMapping("/atualizar/{id}")
    @Operation(summary = "Endpoint para atualizar produto via id", description = "Atualizando produto via id")
    @ApiResponse(responseCode = "200", description = "Produto atualizado com sucesso")
    @ApiResponse(responseCode = "400", description = "Erro ao atualizar o produto, campos inválidos")
    @ApiResponse(responseCode = "404", description = "Erro ao buscar o produto, id do produto não encontrado")
    @ApiResponse(responseCode = "500", description = "Erro no servidor")
    public ResponseEntity<ProdutosResponseDTO> atualizarProdutoPorId(@PathVariable Long id, @RequestBody ProdutosRequestDTO produtosRequestDTO){
        return ResponseEntity.ok(service.atualizarProdutoPorId(id, produtosRequestDTO));
    }

    @DeleteMapping("/deletar/{id}")
    @Operation(summary = "Endpoint para deletar um produto via id", description = "Exclusão de produtos via id")
    @ApiResponse(responseCode = "200", description = "Produto deletado com sucesso")
    @ApiResponse(responseCode = "404", description = "Erro ao deletar o produto, id do produto não encontrado")
    @ApiResponse(responseCode = "500", description = "Erro no servidor")
    public ResponseEntity<Void> deletarProdutoViaId(@PathVariable Long id){
        service.deletarProdutoViaId(id);
        return ResponseEntity.ok().build();
    }
}
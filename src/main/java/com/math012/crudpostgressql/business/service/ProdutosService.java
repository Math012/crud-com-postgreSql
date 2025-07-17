package com.math012.crudpostgressql.business.service;

import com.math012.crudpostgressql.business.DTO.request.ProdutosRequestDTO;
import com.math012.crudpostgressql.business.DTO.response.ProdutosResponseDTO;
import com.math012.crudpostgressql.business.converter.ProdutosConverter;
import com.math012.crudpostgressql.business.converter.ProdutosUpdateConverter;
import com.math012.crudpostgressql.infra.entity.Categorias;
import com.math012.crudpostgressql.infra.entity.Produtos;
import com.math012.crudpostgressql.infra.exceptions.RequestException;
import com.math012.crudpostgressql.infra.exceptions.ResourceNotFoundException;
import com.math012.crudpostgressql.infra.repository.CategoriasRepository;
import com.math012.crudpostgressql.infra.repository.ProdutosRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class ProdutosService {

    private final CategoriasRepository categoriasRepository;

    private final ProdutosRepository produtosRepository;

    private final ProdutosConverter produtosConverter;

    private final ProdutosUpdateConverter produtosUpdateConverter;

    public ProdutosResponseDTO criarProduto(ProdutosRequestDTO produtos, Long idCategoria){
        Categorias entity = categoriasRepository.findById(idCategoria).orElseThrow(
                () -> new ResourceNotFoundException("Erro: o id da categoria: " + idCategoria + " não foi encontrado, tente novamente!")
        );

        if (!verifyFields(produtos)){
            throw new RequestException("Erro ao salvar o produto: campos inválidos");
        }

        Produtos entityProdutos = produtosConverter.paraProdutoVindoDeProdutosRequestDTO(produtos);
        List<Produtos> categoriasList = entity.getProdutos();
        categoriasList.add(entityProdutos);
        entity.setProdutos(categoriasList);
        categoriasRepository.save(entity);
        entityProdutos.setId(categoriasList.get(categoriasList.size()-1).getId());
        return produtosConverter.paraProdutosResponseVindoDeProduto(entityProdutos);
    }

    public List<ProdutosResponseDTO> todosProdutos(){
        return produtosConverter.paraListaProdutosResponseDTOVindoDeListaProdutos(produtosRepository.findAll());
    }

    public ProdutosResponseDTO encontrarProdutoViaId(Long id){
        Produtos entity = produtosRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Erro: o id do produto: " + id + " não foi encontrado, tente novamente!")
        );
        return produtosConverter.paraProdutosResponseVindoDeProduto(entity);
    }

    public ProdutosResponseDTO atualizarProdutoPorId(Long id, ProdutosRequestDTO produtosRequestDTO){
        Produtos entity = produtosRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Erro: o id do produto: " + id + " não foi encontrado, tente novamente!")
        );

        if (!verifyFields(produtosRequestDTO)){
            throw new ResourceNotFoundException("Erro ao salvar o produto: campos inválidos");
        }

        return produtosConverter.paraProdutosResponseVindoDeProduto(
                produtosRepository.save(produtosUpdateConverter.atualizarProdutoEntity(produtosRequestDTO,entity)
                ));
    }

    public void deletarProdutoViaId(Long id){
        Produtos entity = produtosRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Erro: o id do produto: " + id + " não foi encontrado, tente novamente!")
        );

        produtosRepository.delete(entity);
    }

    public boolean verifyFields(ProdutosRequestDTO dto) {
        if (dto == null) {
            return false;
        }
        if (dto.getNome() == null
                || dto.getNome().isBlank()
                || dto.getDescricao() == null
                || dto.getDescricao().isBlank()
                || dto.getPreco() == null
        ) {
            return false;
        } else {
            return true;
        }
    }
}

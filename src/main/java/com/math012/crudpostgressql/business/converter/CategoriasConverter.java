package com.math012.crudpostgressql.business.converter;

import com.math012.crudpostgressql.business.DTO.request.CategoriasRequestDTO;
import com.math012.crudpostgressql.business.DTO.response.CategoriasResponseDTO;
import com.math012.crudpostgressql.infra.entity.Categorias;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@AllArgsConstructor
@Component
public class CategoriasConverter {

    private final ProdutosConverter converter;

    public Categorias paraCategoriasVindoDeCategoriasRequestDTO(CategoriasRequestDTO categoriasRequestDTO){
        return Categorias.builder()
                .nome(categoriasRequestDTO.getNome())
                .build();
    }

    public CategoriasResponseDTO paraCategoriasResponseDTOVindoDeCategorias(Categorias categorias){
        return CategoriasResponseDTO.builder()
                .id(categorias.getId())
                .nome(categorias.getNome())
                .produtos(categorias.getProdutos() != null ? converter.paraListaProdutosResponseDTOVindoDeListaProdutos(categorias.getProdutos()): null)
                .build();
    }

    public List<CategoriasResponseDTO> paraListaCategoriasResponseDTOVindoDeListaCategorias(List<Categorias> categoriasList){
        return categoriasList.stream().map(this::paraCategoriasResponseDTOVindoDeCategorias).toList();
    }
}
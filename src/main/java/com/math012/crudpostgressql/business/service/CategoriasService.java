package com.math012.crudpostgressql.business.service;

import com.math012.crudpostgressql.business.DTO.request.CategoriasRequestDTO;
import com.math012.crudpostgressql.business.DTO.response.CategoriasResponseDTO;
import com.math012.crudpostgressql.business.converter.CategoriasConverter;
import com.math012.crudpostgressql.infra.entity.Categorias;
import com.math012.crudpostgressql.infra.repository.CategoriasRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class CategoriasService {

    private final CategoriasRepository repository;

    private final CategoriasConverter converter;

    public CategoriasResponseDTO criarCategoria(CategoriasRequestDTO categoria){
        Categorias entity = repository.save(converter.paraCategoriasVindoDeCategoriasRequestDTO(categoria));

        return converter.paraCategoriasResponseDTOVindoDeCategorias(entity);

    }

    public List<CategoriasResponseDTO> listaProdutos(){
        return converter.paraListaCategoriasResponseDTOVindoDeListaCategorias(repository.findAll());
    }
}
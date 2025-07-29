package com.math012.crudpostgressql.business.service;

import com.math012.crudpostgressql.business.DTO.request.CategoriasRequestDTO;
import com.math012.crudpostgressql.business.DTO.response.CategoriasResponseDTO;
import com.math012.crudpostgressql.business.converter.CategoriasConverter;
import com.math012.crudpostgressql.infra.entity.Categorias;
import com.math012.crudpostgressql.infra.exceptions.RequestException;
import com.math012.crudpostgressql.infra.repository.CategoriasRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class CategoriasService {

    private final CategoriasRepository repository;

    private final CategoriasConverter converter;

    public CategoriasResponseDTO criarCategoria(CategoriasRequestDTO categoria) {
        if (!verifyFields(categoria)) {
            throw new RequestException("Erro ao salvar a categoria: campos inválidos");
        }

        if (repository.findByNome(categoria.getNome()) != null) {
            throw new RequestException("Erro, a categoria: " + categoria.getNome() + " já está registrada!");
        }

        Categorias entity = repository.save(converter.paraCategoriasVindoDeCategoriasRequestDTO(categoria));
        return converter.paraCategoriasResponseDTOVindoDeCategorias(entity);
    }

    public List<CategoriasResponseDTO> listaProdutos() {
        return converter.paraListaCategoriasResponseDTOVindoDeListaCategorias(repository.findAll());
    }

    public boolean verifyFields(CategoriasRequestDTO dto) {
        if (dto == null) {
            return false;
        }
        if (dto.getNome() == null
                || dto.getNome().isBlank()
        ) {
            return false;
        } else {
            return true;
        }
    }
}
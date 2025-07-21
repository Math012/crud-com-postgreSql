package com.math012.crudpostgressql.business.DTO.response;

import java.util.List;

public class CategoriasResponseDTOFixture {
    public static CategoriasResponseDTO build(
            Long id,
            String nome,
            List<ProdutosResponseDTO> produtos){
        return new CategoriasResponseDTO(id,nome,produtos);
    }
}

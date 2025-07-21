package com.math012.crudpostgressql.business.DTO.response;

public class ProdutosResponseDTOFixture {
    public static ProdutosResponseDTO build(
            Long id,
            String nome,
            String descricao,
            Double preco){
        return new ProdutosResponseDTO(id,nome,descricao,preco);
    }
}

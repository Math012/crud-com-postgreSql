package com.math012.crudpostgressql.business.DTO.request;

public class ProdutosRequestDTOFixture {
    public static  ProdutosRequestDTO build(
            String nome,
            String descricao,
            Double preco){
        return new ProdutosRequestDTO(nome, descricao,preco);
    }

}

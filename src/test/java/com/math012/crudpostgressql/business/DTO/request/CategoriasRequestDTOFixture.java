package com.math012.crudpostgressql.business.DTO.request;

public class CategoriasRequestDTOFixture {
    public static CategoriasRequestDTO build(String nome){
        return new CategoriasRequestDTO(nome);
    }
}

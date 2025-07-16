package com.math012.crudpostgressql.business.DTO.request;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Builder
public class ProdutosRequestDTO {
    private String nome;
    private String descricao;
    private String preco;
}
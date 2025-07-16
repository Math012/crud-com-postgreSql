package com.math012.crudpostgressql.business.DTO.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Builder
public class ProdutosResponseDTO {
    private Long id;
    private String nome;
    private String descricao;
    private Double preco;
}
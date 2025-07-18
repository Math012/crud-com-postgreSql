package com.math012.crudpostgressql.business.DTO.response;

import lombok.*;

import java.util.List;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Builder
public class CategoriasResponseDTO {
    private Long id;
    private String nome;
    private List<ProdutosResponseDTO> produtos;
}
package com.math012.crudpostgressql.infra.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Builder
@Entity(name = "produto")
public class Produtos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "preço")
    private String preco;

    @JsonIgnore
    @Column(name = "categoria_id")
    private Long categoriaId;

    @ManyToOne
    private Categorias categorias;
}

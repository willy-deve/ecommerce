package com.growdev.ecommerce.entities;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@Entity
@Table(name = "tb_produtos")
public class Produto implements Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String nome;
  @Column(columnDefinition = "TEXT")
  private String descricao;
  private Double preco;
  private Instant criado;
  private Instant atualizado;

  @PrePersist
  public void prePersist() {
    criado = Instant.now();
  }

  @PreUpdate
  public void preUpdate() {
    atualizado = Instant.now();
  }

  //PRODUTO
  @ManyToMany
  @JoinTable(
    name = "tb_produto_categoria",
    joinColumns = @JoinColumn(name = "produto_id"),
    inverseJoinColumns = @JoinColumn(name = "categoria_id")
  )
  private Set<Categoria> categorias = new HashSet<>();
}

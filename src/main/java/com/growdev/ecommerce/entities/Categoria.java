package com.growdev.ecommerce.entities;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@Entity
@Table(name = "tb_categorias")
public class Categoria implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String nome;
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

  @ManyToMany(mappedBy = "categorias")
  private Set<Produto> produtos = new HashSet<>();
}

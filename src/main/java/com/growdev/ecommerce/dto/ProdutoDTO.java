package com.growdev.ecommerce.dto;

import com.growdev.ecommerce.entities.Categoria;
import com.growdev.ecommerce.entities.Produto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ProdutoDTO {
  private Long id;
  private String nome;
  private String descricao;
  private Double preco;

  private Set<CategoriaDTO> categoriaDTO = new HashSet<>();

  public ProdutoDTO(Produto entity) {
    this.id = entity.getId();
    this.nome = entity.getNome();
    this.preco = entity.getPreco();
    this.descricao = entity.getDescricao();
  }

  public ProdutoDTO(Produto entity, Set<Categoria> categoria) {
    this(entity);
    categoria.forEach(c -> this.getCategoriaDTO().add(new CategoriaDTO(c)));
  }


  public void setId(Long id) {
    this.id = id;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public void setDescricao(String descricao) {
    this.descricao = descricao;
  }

  public void setPreco(Double preco) {
    this.preco = preco;
  }
}

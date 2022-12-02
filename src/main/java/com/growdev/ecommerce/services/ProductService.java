package com.growdev.ecommerce.services;

import com.growdev.ecommerce.dto.ProdutoDTO;
import com.growdev.ecommerce.entities.Produto;
import com.growdev.ecommerce.repositories.CategoriaRepository;
import com.growdev.ecommerce.repositories.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ProductService {

  @Autowired
  private ProdutoRepository repository;

  @Autowired
  private CategoriaRepository categoriaRepository;

  @Transactional(readOnly = true)
  public Page<ProdutoDTO> findAll(PageRequest pageRequest) {//pageRequest
    //ele consegue entender o pageRequest (Jpa)
    Page<Produto> list = repository.findAll(pageRequest);
    return list.map(produto -> new ProdutoDTO(produto));
  }

}

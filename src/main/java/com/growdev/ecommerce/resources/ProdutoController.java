package com.growdev.ecommerce.resources;

import com.growdev.ecommerce.dto.ProdutoDTO;
import com.growdev.ecommerce.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

  @Autowired
  private ProductService service;

  @GetMapping
  public ResponseEntity<Page<ProdutoDTO>> findAll(
    @RequestParam(value = "pagina", defaultValue = "0") Integer pagina,
    @RequestParam(value = "linhasPorPagina", defaultValue = "10") Integer linhasPorPagina,
    @RequestParam(value = "ordenado", defaultValue = "nome") String nome
  ) {
    //qual pagina que eu quero (0), quantidade linhas, ordenação
    //qual pagina que eu quero (0), quantidade linhas, direação, ordenação
    PageRequest list = PageRequest.of(pagina, linhasPorPagina, Sort.Direction.valueOf(nome));
    Page<ProdutoDTO> dtos = service.findAll(list);
    return ResponseEntity.ok().body(dtos);
  }
}

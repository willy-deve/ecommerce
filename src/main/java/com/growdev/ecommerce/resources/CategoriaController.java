package com.growdev.ecommerce.resources;

import com.growdev.ecommerce.dto.CategoriaDTO;
import com.growdev.ecommerce.dto.ProdutoDTO;
import com.growdev.ecommerce.services.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/categorias")
public class CategoriaController {

  @Autowired
  private CategoriaService service;

  @GetMapping
  public ResponseEntity<Page<CategoriaDTO>> findAll(
    @RequestParam(value = "pagina", defaultValue = "0") Integer pagina,//Primeira página
    @RequestParam(value = "linhasPorPagina", defaultValue = "1") Integer linhasPorPagina,//quantidade de registros por pagina
    @RequestParam(value = "direction", defaultValue = "ASC") String direction,//direção Crescente
    @RequestParam(value = "ordenado", defaultValue = "nome") String nome //Ordem
    //exemplo de URL: /categorias?pagina=0&linhasPorPagina=12&nome=teste
  ) {
    PageRequest list = PageRequest.of(pagina, linhasPorPagina, Sort.Direction.valueOf(direction), nome);
    Page<CategoriaDTO> dtos = service.findAllPaged(list);
    return ResponseEntity.ok().body(dtos);
  }

  @GetMapping("/lista")
  public ResponseEntity<List<CategoriaDTO>> findAll() {
    List<CategoriaDTO> dtoList = service.findAll();
    return ResponseEntity.ok().body(dtoList);
  }

  @GetMapping("/{id}")
  public ResponseEntity<CategoriaDTO> findById(@PathVariable("id") Long id) {
    CategoriaDTO dto = service.findById(id);
    return ResponseEntity.ok().body(dto);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteById(@PathVariable("id") Long id) {
    service.delete(id);
    return ResponseEntity.noContent().build();
  }

  @PostMapping("/salvar")
  public ResponseEntity<CategoriaDTO> insert(@Valid @RequestBody CategoriaDTO dto) {
    dto = service.save(dto);
    URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
      .buildAndExpand(dto.getId()).toUri();
    return ResponseEntity.created(uri).body(dto);
  }

  @PutMapping("/atualizar/{id}")
  public ResponseEntity<CategoriaDTO> atualizar(@Valid @RequestBody CategoriaDTO dto, @PathVariable Long id) {
    dto = service.save(dto);
    return ResponseEntity.ok().body(dto);
  }
}

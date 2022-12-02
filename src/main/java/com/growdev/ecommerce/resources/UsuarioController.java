package com.growdev.ecommerce.resources;

import com.growdev.ecommerce.dto.CategoriaDTO;
import com.growdev.ecommerce.dto.UsuarioInsertDTO;
import com.growdev.ecommerce.dto.UsuarioUpdateDTO;
import com.growdev.ecommerce.dto.UsuariosDTO;
import com.growdev.ecommerce.services.UsuarioService;
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

@RestController
@RequestMapping(value = "/usuarios")
public class UsuarioController {

  @Autowired
  private UsuarioService service;

  @GetMapping
  public ResponseEntity<Page<UsuariosDTO>> findAll(Pageable pageable) {
    Page<UsuariosDTO> list = service.findAllPaged(pageable);
    return ResponseEntity.ok().body(list);
  }

  @GetMapping("/{id}")
  public ResponseEntity<UsuariosDTO> findById(Long id) {
    UsuariosDTO dto = service.findById(id);
    return ResponseEntity.ok().body(dto);
  }

  @PostMapping
  public ResponseEntity<UsuariosDTO> insert(@Valid @RequestBody UsuarioInsertDTO dto){
    UsuariosDTO newDto = service.save(dto);
    URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(newDto.getId())
      .toUri();
    return ResponseEntity.ok().body(newDto);
  }

  @PutMapping("/{id}")
  public ResponseEntity<UsuariosDTO> insert(@Valid @RequestBody UsuarioUpdateDTO dto, @PathVariable Long id){
    UsuariosDTO newDto = service.update(id, dto);
    return ResponseEntity.ok().body(newDto);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable Long id){
    service.delete(id);
    return ResponseEntity.noContent().build();
  }
}

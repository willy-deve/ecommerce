package com.growdev.ecommerce.services;

import com.growdev.ecommerce.dto.CategoriaDTO;
import com.growdev.ecommerce.dto.ProdutoDTO;
import com.growdev.ecommerce.entities.Categoria;
import com.growdev.ecommerce.entities.Produto;
import com.growdev.ecommerce.exceptions.DatabaseException;
import com.growdev.ecommerce.exceptions.ResourceNotFoundException;
import com.growdev.ecommerce.repositories.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class CategoriaService {

  @Autowired
  private CategoriaRepository repository;

  @Transactional(readOnly = true)
  public List<CategoriaDTO> findAll() {
    List<Categoria> categoriaList = repository.findAll();
    return categoriaList.stream().map(c -> new CategoriaDTO(c)).collect(Collectors.toList());
  }

  @Transactional(readOnly = true)
  public CategoriaDTO findById(Long id) {
    Optional<Categoria> obj = repository.findById(id);
    Categoria entity = obj.orElseThrow(() -> new ResourceNotFoundException("NOT FOUND " + id));
    return new CategoriaDTO();
  }


  public void delete(Long id) {
    try {
      repository.deleteById(id);
    }//é do spring e serve para reclamar que nao executou nada no banco
    //na reclamação(exceção) devemos instancia/propagar uma excecao personalizada
    catch (EmptyResultDataAccessException e) {
      throw new ResourceNotFoundException("Not found " + id);
    }//caso a categoria tenha dados vinculados a ela, não poderá ser excluida, nisso, apresentamos
    //um erro de violação dos dados integrados.
    catch (DataIntegrityViolationException e) {
      throw new DatabaseException("Intregrity Violation");
    }
  }

  public CategoriaDTO save(CategoriaDTO dto) {
    Categoria entity = new Categoria();
    entity.setNome(dto.getNome());
    entity = repository.save(entity);
    return new CategoriaDTO(entity);
  }

  public CategoriaDTO update(CategoriaDTO dto, Long id) {
    try {
      Categoria entity = repository.findById(id).get();
      entity.setNome(dto.getNome());
      entity = repository.save(entity);
      return new CategoriaDTO(entity);
    } catch (ResourceNotFoundException e) {
      throw new ResourceNotFoundException("Not Found ID " + id);
    }
  }

  @Transactional(readOnly = true)
  public Page<CategoriaDTO> findAllPaged(PageRequest pageRequest) {
    Page<Categoria> list = repository.findAll(pageRequest);
    return list.map(categoria -> new CategoriaDTO(categoria));
  }
}

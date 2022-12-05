package com.growdev.ecommerce.services;

import com.growdev.ecommerce.dto.RolesDTO;
import com.growdev.ecommerce.dto.UsuarioInsertDTO;
import com.growdev.ecommerce.dto.UsuarioUpdateDTO;
import com.growdev.ecommerce.dto.UsuariosDTO;
import com.growdev.ecommerce.entities.Roles;
import com.growdev.ecommerce.entities.Usuario;
import com.growdev.ecommerce.exceptions.DatabaseException;
import com.growdev.ecommerce.exceptions.ResourceNotFoundException;
import com.growdev.ecommerce.repositories.RoleRepository;
import com.growdev.ecommerce.repositories.UsuariosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class UsuarioService implements UserDetailsService {

  @Autowired
  private UsuariosRepository repository;
  @Autowired
  private RoleRepository roleRepository;
  //INJETO A FUNCAO DE ENCODER
  @Autowired
  private BCryptPasswordEncoder passwordEncoder;

  @Transactional(readOnly = true)
  public Page<UsuariosDTO> findAllPaged(Pageable pageable) {
    Page<Usuario> usuarios = repository.findAll(pageable);
    return usuarios.map(usuario -> new UsuariosDTO(usuario));
  }

  @Transactional(readOnly = true)
  public UsuariosDTO findById(Long id) {
    Optional<Usuario> usuario = repository.findById(id);
    Usuario entity = usuario.orElseThrow(() -> new ResourceNotFoundException("Not found " + id));
    return new UsuariosDTO(entity);
  }

  public UsuariosDTO save(UsuarioInsertDTO dto) {
    Usuario entity = new Usuario();
    setEntity(entity, dto);
    entity = repository.save(entity);
    return new UsuariosDTO(entity);
  }

  public UsuariosDTO update(Long id, UsuarioUpdateDTO dto) {
    Usuario entity = repository.findById(id).get();
    setEntytiUpdate(entity, dto);
    entity = repository.save(entity);

    return new UsuariosDTO(entity);
  }

  //Funcao criada devido para não termos repetiçao de código no save e no update
  public void setEntity(Usuario entity, UsuarioInsertDTO dto) {
    entity.setNome(dto.getNome());
    entity.setEmail(dto.getEmail());
    //vamos ter que pegar a senha e converter para bycrypt
    entity.setSenha(passwordEncoder.encode(dto.getSenha()));
    //PERCORRO O DTO que vai no endpoint(controller) e tiro dali a lista de roles
    for (RolesDTO rolesDTO : dto.getRoles()) {
      //vejo se de fato existe os roles para poder gravar no banco de dados
      Roles roles = roleRepository.findById(rolesDTO.getId()).get();
      //adiciono na lista da classe Usuario, dali ele tirara somente o id devido o JoinColumn para gravar no banco
      entity.getRoles().add(roles);
    }
  }

  public void setEntytiUpdate(Usuario entity, UsuarioUpdateDTO dto){
    entity.setNome(dto.getNome());
    entity.setEmail(dto.getEmail());
    entity.setSenha(passwordEncoder.encode(dto.getSenha()));

    for (RolesDTO rolesDTO : dto.getRoles()){
      Roles roles = roleRepository.findById(rolesDTO.getId()).get();
      entity.getRoles().add(roles);
    }
  }

  public void delete(Long id) {
    try {
      repository.deleteById(id);
    } catch (EmptyResultDataAccessException e) {
      throw new ResourceNotFoundException("Id not found " + id);
    } catch (DataIntegrityViolationException e) {
      throw new DatabaseException("Intregrity violation");
    }
  }


  //loadUserByUsername recebe o usuario e verifica se ele existe para ter acesso
  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    Usuario usuario = repository.findByEmail(username);
    if(usuario == null){
      throw new UsernameNotFoundException("E-mail, não existe");
    }
    return usuario;
  }
}

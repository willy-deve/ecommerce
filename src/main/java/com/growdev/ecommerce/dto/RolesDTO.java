package com.growdev.ecommerce.dto;

import com.growdev.ecommerce.entities.Roles;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RolesDTO {

  private Long id;

  private String authority;

  public RolesDTO(Roles entity) {
    this.id = entity.getId();
    this.authority = entity.getAuthority();
  }

}

package com.growdev.ecommerce.repositories;

import com.growdev.ecommerce.entities.Roles;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Roles, Long> {
}

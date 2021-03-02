package com.core.repository;

import com.core.model.Role;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> getRoleByRoleTitle(Role.RoleTitle roleTitle);
}

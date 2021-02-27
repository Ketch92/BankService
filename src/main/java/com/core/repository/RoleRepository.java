package com.core.repository;

import com.core.model.Role;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface RoleRepository extends JpaRepository<Role, Long> {
    @Query("from Role r where r.role = :role")
    Optional<Role> getRoleByName(@Param("role") Role.RoleTitle roleTitle);
}

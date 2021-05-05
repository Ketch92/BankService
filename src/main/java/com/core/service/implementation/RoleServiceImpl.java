package com.core.service.implementation;

import com.core.lib.DataProcessingException;
import com.core.model.Role;
import com.core.repository.RoleRepository;
import com.core.service.RoleService;
import javax.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;
    
    @PostConstruct
    private void injectBasicRoles() {
        Role user = new Role();
        user.setRoleTitle(Role.RoleTitle.USER);
        roleRepository.save(user);
        Role admin = new Role();
        admin.setRoleTitle(Role.RoleTitle.ADMIN);
        roleRepository.save(admin);
    }
    
    @Override
    public Role save(Role role) {
        return roleRepository.save(role);
    }
    
    @Override
    public Role getByName(String name) {
        return roleRepository.getRoleByRoleTitle(Role.RoleTitle.valueOf(name.toUpperCase()))
                .orElseThrow(() ->
                        new DataProcessingException(String.format("No role with %s name", name)));
    }
}

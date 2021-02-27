package com.core.service;

import com.core.model.Role;

public interface RoleService {
    Role save(Role role);
    
    Role getByName(String name);
}

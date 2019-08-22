package source.service;

import source.entity.Role;

public interface RoleService {
    Role findByName(String name);
}

package source.service.impl;

import org.springframework.stereotype.Service;
import source.entity.Role;
import source.repository.RoleRepository;
import source.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepo;

    public RoleServiceImpl(RoleRepository roleRepo) {
        this.roleRepo = roleRepo;
    }

    @Override
    public Role findByName(String name) {
        return roleRepo.findByName(name);
    }
}

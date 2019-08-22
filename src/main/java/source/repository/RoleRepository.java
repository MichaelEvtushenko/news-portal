package source.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import source.entity.Role;

@Repository
public interface RoleRepository extends CrudRepository<Role,Integer> {
    Role findByName(String name);
}

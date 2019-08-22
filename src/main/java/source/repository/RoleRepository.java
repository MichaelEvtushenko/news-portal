package source.repository;

import org.springframework.data.repository.CrudRepository;
import source.entity.Role;

public interface RoleRepository extends CrudRepository<Role,Integer> {
}

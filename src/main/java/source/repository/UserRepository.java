package source.repository;

import org.springframework.data.repository.CrudRepository;
import source.entity.User;

public interface UserRepository extends CrudRepository<User,Integer> {
    User findByName(String name);
}

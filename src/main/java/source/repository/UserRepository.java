package source.repository;

import org.springframework.data.repository.CrudRepository;
import source.entity.User;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User,Integer> {
    User findByName(String name);
}

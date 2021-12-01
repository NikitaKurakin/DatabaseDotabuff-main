package dotabuff.jwtapp.repository;

import dotabuff.jwtapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long>
{
    User findByUsername(String name);
    Optional<User> findById(long id);
    void deleteByUsername(String login);
    void deleteById(long id);
    List<User> findAll();
}

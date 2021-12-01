package dotabuff.jwtapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import dotabuff.jwtapp.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long>
{
    Role findByName(String name);
}
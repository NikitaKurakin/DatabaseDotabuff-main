package dotabuff.jwtapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import dotabuff.jwtapp.model.Hero;

import java.util.List;
import java.util.Optional;

public interface HeroRepository extends JpaRepository<Hero, Long>
{
    Hero findByName(String name);

    Optional<Hero> findById(Long id);

    List<Hero> findAll();
}

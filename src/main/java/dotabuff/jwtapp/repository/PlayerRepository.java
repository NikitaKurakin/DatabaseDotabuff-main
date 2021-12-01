package dotabuff.jwtapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import dotabuff.jwtapp.model.Player;

import java.util.List;
import java.util.Optional;

public interface PlayerRepository extends JpaRepository<Player, Long>
{
    Player findByNickname(String name);
    Optional<Player> findById(Long id);
    boolean deleteByNickname(String nickname);
    void deleteById(Long id);

    List<Player> findAll();
}

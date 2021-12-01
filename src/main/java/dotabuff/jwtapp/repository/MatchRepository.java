package dotabuff.jwtapp.repository;

import dotabuff.jwtapp.model.Hero;
import dotabuff.jwtapp.model.Match;
import dotabuff.jwtapp.model.Player;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MatchRepository extends JpaRepository<Match, Long>
{
    List<Match> findAllByPlayerId(Player player);

    List<Match> findAllByHeroId(Hero hero);

    List<Match> findAll();
}

package dotabuff.jwtapp.service.impl;

import dotabuff.jwtapp.model.Hero;
import dotabuff.jwtapp.model.Match;
import dotabuff.jwtapp.model.Player;
import dotabuff.jwtapp.repository.MatchRepository;
import dotabuff.jwtapp.service.MatchService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@Transactional
public class MatchServiceImpl implements MatchService
{
    private final MatchRepository matchRepository;

    @Autowired
    public MatchServiceImpl(MatchRepository matchRepository)
    {
        this.matchRepository = matchRepository;
    }

    @Override
    public List<Match> getAll()
    {
        List<Match> result = matchRepository.findAll();
        log.info("IN getAll - {} matches found", result.size());
        return result;
    }

    @Override
    public List<Match> getAllByPlayerId(Player player)
    {
        List<Match> result = matchRepository.findAllByPlayerId(player);
        log.info("IN getAllByPlayerId - {} matches found by playerId: {}", result.size(), player.getId());
        return result;
    }

    @Override
    public List<Match> getAllByHeroId(Hero hero)
    {
        List<Match> result = matchRepository.findAllByHeroId(hero);
        log.info("IN getAllByHeroId - {} matches found by heroId: {}", result.size(), hero.getId());
        if(result.size() != 0) return result;
        else return null;
    }
}

package dotabuff.jwtapp.service;

import dotabuff.jwtapp.model.Hero;
import dotabuff.jwtapp.model.Match;
import dotabuff.jwtapp.model.Player;

import java.util.List;

public interface MatchService
{
    List<Match> getAll();

    List<Match> getAllByPlayerId (Player player);

    List<Match> getAllByHeroId (Hero hero);
}

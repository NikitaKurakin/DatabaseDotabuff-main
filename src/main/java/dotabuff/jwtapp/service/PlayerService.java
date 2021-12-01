package dotabuff.jwtapp.service;

import dotabuff.jwtapp.model.Player;

import java.util.List;

public interface PlayerService
{
    List<Player> getAll();

    Player findByNickname(String nickname);

    boolean deleteByNickname(String nickname);

    Player findById(Long id);

    void deleteById(Long id);
}

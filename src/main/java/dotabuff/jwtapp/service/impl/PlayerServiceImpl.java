package dotabuff.jwtapp.service.impl;

import dotabuff.jwtapp.model.Player;
import dotabuff.jwtapp.repository.PlayerRepository;
import dotabuff.jwtapp.service.PlayerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@Transactional
public class PlayerServiceImpl implements PlayerService
{
    private final PlayerRepository playerRepository;

    @Autowired
    public PlayerServiceImpl(PlayerRepository playerRepository)
    {
        this.playerRepository = playerRepository;
    }

    @Override
    public List<Player> getAll()
    {
        List<Player> result = playerRepository.findAll();
        log.info("IN getAll - {} players found", result.size());
        return null;
    }

    @Override
    public Player findByNickname(String nickname)
    {
        Player result = playerRepository.findByNickname(nickname);
        log.info("IN findByNickname - player: {} found by nickname: {}", result, nickname);
        return result;
    }

    @Override
    public boolean deleteByNickname(String nickname)
    {
        if (playerRepository.findByNickname(nickname) == null)
            return false;
        playerRepository.deleteByNickname(nickname);
        return true;
    }

    @Override
    public Player findById(Long id)
    {
        Optional<Player> result = playerRepository.findById(id);
        log.info("IN findByPlayerId - player: {} found by id: {}", result, id);
        return result.orElse(null);
    }

    @Override
    public void deleteById(Long id)
    {
        playerRepository.deleteById(id);
        log.info("IN delete - player with id: {} successfully deleted", id);
    }
}

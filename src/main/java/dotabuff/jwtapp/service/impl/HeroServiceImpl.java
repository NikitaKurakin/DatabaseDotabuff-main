package dotabuff.jwtapp.service.impl;

import dotabuff.jwtapp.model.Hero;
import dotabuff.jwtapp.repository.HeroRepository;
import dotabuff.jwtapp.service.HeroService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@Transactional
public class HeroServiceImpl implements HeroService
{
    private final HeroRepository heroRepository;

    @Autowired
    public HeroServiceImpl(HeroRepository heroRepository)
    {
        this.heroRepository = heroRepository;
    }

    @Override
    public List<Hero> getAll()
    {
        List<Hero> result = heroRepository.findAll();
        log.info("IN getAll - {} heroes found", result.size());
        return result;
    }

    @Override
    public Hero findByName(String name)
    {
        Hero result = heroRepository.findByName(name);
        log.info("IN findByName - hero: {} found by name: {}", result, name);
        return result;
    }

    @Override
    public Hero findById(Long id)
    {
        Optional<Hero> result = heroRepository.findById(id);
        log.info("IN findById - hero: {} found by id: {}", result, id);
        return result.orElse(null);
    }
}

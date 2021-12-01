package dotabuff.jwtapp.service;

import dotabuff.jwtapp.model.Hero;

import java.util.List;

public interface HeroService
{
    List<Hero> getAll();

    Hero findByName (String name);

    Hero findById (Long id);
}

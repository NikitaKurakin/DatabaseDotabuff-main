package dotabuff.jwtapp.rest;

import dotabuff.jwtapp.model.*;
import dotabuff.jwtapp.repository.RoleRepository;
import dotabuff.jwtapp.repository.UserRepository;
import dotabuff.jwtapp.service.HeroService;
import dotabuff.jwtapp.service.MatchService;
import dotabuff.jwtapp.service.PlayerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = "/api/user/tables/")
public class TablesRestController
{
    private final MatchService matchService;
    private final HeroService heroService;
    private final PlayerService playerService;
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;

    @Autowired
    public TablesRestController(MatchService matchService, HeroService heroService, PlayerService playerService, RoleRepository roleRepository, UserRepository userRepository)
    {
        this.matchService = matchService;
        this.heroService = heroService;
        this.playerService = playerService;
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
    }

    @GetMapping("get_role")
    public ResponseEntity<String> getRole(HttpServletRequest request)
    {
        User user = userRepository.findByUsername(request.getParameter("name"));

        if (user != null)
        {
            if (user.getRole().getName().contentEquals("ROLE_ADMIN"))
                return new ResponseEntity<>("/admin/", HttpStatus.OK);
            else if (user.getRole().getName().contentEquals("ROLE_USER"))
                return new ResponseEntity<>("/user/", HttpStatus.OK);
            else
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("all_matches")
    public List<Match> getAllMatches() { return matchService.getAll(); }

    @GetMapping("player_id_matches")
    public ResponseEntity<List<Match>> getAllByPlayerId(HttpServletRequest request)
    {
        Player player = playerService.findByNickname(request.getParameter("nickname"));
        if (player != null)
        {
            List<Match> result = matchService.getAllByPlayerId(player);
            if (result != null)
                return new ResponseEntity<>(result, HttpStatus.OK);
            else
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("player_by_name")
    public ResponseEntity<Player> getPlayerByNickname(HttpServletRequest request)
    {
        Player player = playerService.findByNickname(request.getParameter("nickname"));
        return player != null
                ? new ResponseEntity<>(player, HttpStatus.OK):
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("hero_id_matches")
    public ResponseEntity<List<Match>> getAllByHeroId(HttpServletRequest request)
    {
        Hero hero = heroService.findByName(request.getParameter("name"));
        if (hero != null)
        {
            List<Match> result = matchService.getAllByHeroId(hero);
            if (result != null)
                return new ResponseEntity<>(result, HttpStatus.OK);
            else
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("all_heroes")
    public List<Hero> getAll() { return heroService.getAll(); }

    @GetMapping("hero_by_name")
    public ResponseEntity<Hero> findByName(HttpServletRequest request)
    {
        Hero hero =  heroService.findByName(request.getParameter("name"));
        return hero != null
                ? new ResponseEntity<>(hero, HttpStatus.OK):
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("hero_by_id")
    public Hero findById(Long id) { return heroService.findById(id); }

    @GetMapping("update_heroes")
    public void updateHeroes()
    {
        for (Hero hero : heroService.getAll())
        {
            List<Match> Matches = matchService.getAllByHeroId(hero);
            if (Matches != null)
            {
                for (Match match : Matches)
                {
                    hero.setMatchcount(0);
                    hero.setWincount(0);
                    hero.setLostcount(0);
                    hero.setTime(0);
                    hero.setKills(0);
                    hero.setDeaths(0);
                    hero.setAssistances(0);
                }

                for (Match match : Matches)
                {
                    hero.setMatchcount(hero.getMatchcount() + 1);

                    if (match.isResult())
                        hero.setWincount(hero.getWincount() + 1);
                    else
                        hero.setLostcount(hero.getLostcount() + 1);

                    hero.setTime(hero.getTime() + match.getTime());
                    hero.setKills(hero.getKills() + match.getKills());
                    hero.setDeaths(hero.getDeaths() + match.getDeaths());
                    hero.setAssistances(hero.getAssistances() + match.getAssistances());
                }
            }
        }
    }
}

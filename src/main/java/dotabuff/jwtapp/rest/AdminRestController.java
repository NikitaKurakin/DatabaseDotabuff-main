package dotabuff.jwtapp.rest;

import dotabuff.jwtapp.model.User;
import dotabuff.jwtapp.security.jwt.JwtTokenProvider;
import dotabuff.jwtapp.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = "/api/admin/")
public class AdminRestController
{
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserService userService;

    @Autowired
    public AdminRestController(AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider, UserService userService)
    {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.userService = userService;
    }

    @GetMapping("get_users")
    public List<User> getAllUsers() { return userService.getAll(); }

    @RequestMapping(value = "delete/{login}")
    public HttpStatus deleteByUsername(@PathVariable(name = "login") String username)
    {
        if (userService.deleteByUsername(username))
            return HttpStatus.OK;
        return HttpStatus.BAD_REQUEST;
    }
}
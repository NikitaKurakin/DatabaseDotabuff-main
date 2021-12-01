package dotabuff.jwtapp.security.jwt;

import dotabuff.jwtapp.model.User;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collections;

public class JwtUserFactory
{
    public JwtUserFactory() { }

    public static JwtUser create(User user)
    {
        return new JwtUser(
                user.getId(),
                user.getUsername(),
                user.getPassword(),
                Collections.singletonList(new SimpleGrantedAuthority(user.getRole().getName()))
        );
    }
}
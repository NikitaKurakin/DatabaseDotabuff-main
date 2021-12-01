package dotabuff.jwtapp.service.impl;

import dotabuff.jwtapp.model.Role;
import dotabuff.jwtapp.model.User;
import dotabuff.jwtapp.repository.RoleRepository;
import dotabuff.jwtapp.repository.UserRepository;
import dotabuff.jwtapp.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@Transactional
public class UserServiceImpl implements UserService
{
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, @Lazy BCryptPasswordEncoder passwordEncoder)
    {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User register(User user)
    {
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        System.out.println(user);

        Role role = roleRepository.findByName("ROLE_USER");
        user.setRole(role);
        User registerUser = userRepository.save(user);
        log.info("IN register - user: {} successfully registered", registerUser);

        return registerUser;
    }

    @Override
    public List<User> getAll()
    {
        List<User> result = userRepository.findAll();
        log.info("IN getAll - {} users found", result.size());

        return result;
    }

    @Override
    public User findByUsername(String username)
    {
        User result = userRepository.findByUsername(username);
        log.info("IN findByUsername - user: {} found by username: {}", result, username);
        return result;
    }

    @Override
    public User findById(Long id)
    {
        Optional<User> result = userRepository.findById(id);
        log.info("IN findById - user: {} found by id: {}", result, id);

        return result.orElse(null);
    }

    @Override
    public void deleteById(Long id)
    {
        userRepository.deleteById(id);
        log.info("IN delete - user with id: {} successfully deleted", id);
    }

    @Override
    public boolean deleteByUsername(String username)
    {
        if (userRepository.findByUsername(username) == null)
        {
            log.info("IN deleteByUsername - user with username: {} successfully deleted", username);
            return false;
        }
        userRepository.deleteByUsername(username);
        return true;
    }
}

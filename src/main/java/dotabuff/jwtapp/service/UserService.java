package dotabuff.jwtapp.service;

import dotabuff.jwtapp.model.User;

import java.util.List;

public interface UserService
{
    User register(User user);

    List<User> getAll();

    User findByUsername(String username);

    boolean deleteByUsername(String username);

    User findById(Long id);

    void deleteById(Long id);
}

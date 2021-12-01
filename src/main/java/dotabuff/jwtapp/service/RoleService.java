package dotabuff.jwtapp.service;

import dotabuff.jwtapp.model.Role;

public interface RoleService
{
    Role findByName(String name);
}

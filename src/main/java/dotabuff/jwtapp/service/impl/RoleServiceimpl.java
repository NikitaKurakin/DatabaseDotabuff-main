package dotabuff.jwtapp.service.impl;

import dotabuff.jwtapp.model.Role;
import dotabuff.jwtapp.repository.RoleRepository;
import dotabuff.jwtapp.service.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Slf4j
@Transactional
public class RoleServiceimpl implements RoleService
{
    private final RoleRepository roleRepository;

    @Autowired
    public RoleServiceimpl(RoleRepository roleRepository)
    {
        this.roleRepository = roleRepository;
    }


    @Override
    public Role findByName(String name)
    {
        Role result = roleRepository.findByName(name);
        log.info("IN findByUsername - user: {} found by username: {}", result, name);
        return result;
    }
}

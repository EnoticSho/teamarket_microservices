package com.example.teamarket.auth.services.impl;

import com.example.teamarket.auth.entities.Role;
import com.example.teamarket.auth.repository.RoleRepository;
import com.example.teamarket.auth.services.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository repository;

    public Role getRoleByName(String name) {
        Optional<Role> byName = repository.findByName(name);
        return byName.get();
    }
}

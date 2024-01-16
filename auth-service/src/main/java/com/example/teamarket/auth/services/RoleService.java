package com.example.teamarket.auth.services;

import com.example.teamarket.auth.entities.Role;
import com.example.teamarket.auth.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoleService {

    private final RoleRepository repository;

    public Role getRoleByName(String name) {
        Optional<Role> byName = repository.findByName(name);
        return byName.get();
    }
}

package com.example.teamarket.auth.services;

import com.example.teamarket.auth.entities.Role;

public interface RoleService {
    Role getRoleByName(String name);
}

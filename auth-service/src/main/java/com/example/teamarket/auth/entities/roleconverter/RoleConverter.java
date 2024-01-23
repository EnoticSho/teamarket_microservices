package com.example.teamarket.auth.entities.roleconverter;

import com.example.teamarket.auth.entities.Role;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class RoleConverter implements AttributeConverter<Role, Integer> {

    @Override
    public Integer convertToDatabaseColumn(Role role) {
        if (role == null) {
            return null;
        }
        return role.getId();
    }

    @Override
    public Role convertToEntityAttribute(Integer roleId) {
        if (roleId == null) {
            return null;
        }
        return Role.valueOf(roleId);
    }
}

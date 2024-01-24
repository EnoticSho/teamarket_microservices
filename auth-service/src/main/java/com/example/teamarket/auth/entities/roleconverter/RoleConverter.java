package com.example.teamarket.auth.entities.roleconverter;

import com.example.teamarket.auth.entities.Role;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

/**
 * This class is an AttributeConverter for converting Role enum values to and from Integer database columns.
 */
@Converter(autoApply = true)
public class RoleConverter implements AttributeConverter<Role, Integer> {

    /**
     * Converts a Role enum value to its corresponding Integer representation for database storage.
     *
     * @param role The Role enum value to be converted.
     * @return The Integer representation of the Role.
     */
    @Override
    public Integer convertToDatabaseColumn(Role role) {
        if (role == null) {
            return null;
        }
        return role.getId();
    }

    /**
     * Converts an Integer from the database back to its corresponding Role enum value.
     *
     * @param roleId The Integer representation of the Role.
     * @return The Role enum value.
     */
    @Override
    public Role convertToEntityAttribute(Integer roleId) {
        if (roleId == null) {
            return null;
        }
        return Role.valueOf(roleId);
    }
}

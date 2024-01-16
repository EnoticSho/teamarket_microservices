package com.example.teamarket.auth.mapper;

import com.example.teamarket.auth.dto.request.SignUpRequest;
import com.example.teamarket.auth.dto.response.UserInfoDto;
import com.example.teamarket.auth.entities.User;
import com.example.teamarket.auth.services.AuthenticationService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Mapper(componentModel = "spring", uses = AuthenticationService.class)
public interface UserMapper {

    UserInfoDto toUserInfoDto(User user);

    @Mapping(target = "password", ignore = true)
    User toUser(SignUpRequest signUpRequest);
}

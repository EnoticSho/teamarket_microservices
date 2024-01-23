package com.example.teamarket.auth.mapper;

import com.example.teamarket.auth.dto.request.SignUpRequest;
import com.example.teamarket.auth.dto.response.UserInfoDto;
import com.example.teamarket.auth.entities.User;
import com.example.teamarket.auth.services.impl.AuthenticationServiceImpl;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = AuthenticationServiceImpl.class)
public interface UserMapper {

    UserInfoDto toUserInfoDto(User user);

    User toUser(SignUpRequest signUpRequest);
}

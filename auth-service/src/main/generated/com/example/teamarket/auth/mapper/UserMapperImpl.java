package com.example.teamarket.auth.mapper;

import com.example.teamarket.auth.dto.request.SignUpRequest;
import com.example.teamarket.auth.dto.response.UserInfoDto;
import com.example.teamarket.auth.entities.User;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-02-23T04:08:32+0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.2 (Oracle Corporation)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public UserInfoDto toUserInfoDto(User user) {
        if ( user == null ) {
            return null;
        }

        Long id = null;
        String email = null;
        String name = null;
        String address = null;

        id = user.getId();
        email = user.getEmail();
        name = user.getName();
        address = user.getAddress();

        UserInfoDto userInfoDto = new UserInfoDto( id, email, name, address );

        return userInfoDto;
    }

    @Override
    public User toUser(SignUpRequest signUpRequest) {
        if ( signUpRequest == null ) {
            return null;
        }

        User user = new User();

        user.setEmail( signUpRequest.email() );
        user.setPassword( signUpRequest.password() );
        user.setName( signUpRequest.name() );
        user.setAddress( signUpRequest.address() );

        return user;
    }
}

package com.setxpro.zend.core.domain.dtos.userDto;

import com.setxpro.zend.core.domain.entities.User;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class UserDtoMapper implements Function<User, UserFormatDTO> {
    @Override
    public UserFormatDTO apply(User user) {
        return new UserFormatDTO(
                user.getName(),
                user.getEmail(),
                user.getLogin(),
                user.getPhoneNumber(),
                user.getGender(),
                user.getRole()
        );
    }
}

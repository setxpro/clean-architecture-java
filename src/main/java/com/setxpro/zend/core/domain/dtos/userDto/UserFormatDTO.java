package com.setxpro.zend.core.domain.dtos.userDto;

import com.setxpro.zend.core.domain.enums.GenderEnum;
import com.setxpro.zend.core.domain.enums.RoleEnum;
import lombok.Builder;

@Builder
public record UserFormatDTO(String name, String email, String login, String phoneNumber, GenderEnum gender, RoleEnum role) {
}

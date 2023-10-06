package com.setxpro.zend.services;

import com.setxpro.zend.core.domain.dtos.userDto.UserDTO;
import com.setxpro.zend.core.domain.dtos.userDto.UserDtoMapper;
import com.setxpro.zend.core.domain.dtos.userDto.UserFormatDTO;
import com.setxpro.zend.core.domain.entities.User;
import com.setxpro.zend.core.domain.repositories.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final UserDtoMapper userDtoMapper;

    public UserService(UserRepository userRepository, UserDtoMapper userDtoMapper) {
        this.userRepository = userRepository;
        this.userDtoMapper = userDtoMapper;
    }
    public ResponseEntity<Object> createUser(UserDTO userDTO) throws Exception {
      //  if(this.userRepository.findByLogin(userDTO.login()) != null) return ResponseEntity.badRequest().build();

        String encryptedPassword = new BCryptPasswordEncoder().encode(userDTO.password());
        System.out.println(encryptedPassword);
        User user = new User(
                userDTO.name(),
                userDTO.email(),
                userDTO.login(),
                encryptedPassword,
                userDTO.phoneNumber(),
                userDTO.gender(),
                userDTO.role()
        );


        //BeanUtils.copyProperties(userDTO, user);
        userRepository.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(userDTO);
    }

    public List<UserFormatDTO> findAllUsers() {

        return Optional.of(userRepository.findAll())
                .filter(list -> !list.isEmpty())
                .map(list -> list.stream().map(userDtoMapper).toList())
                .orElseThrow(() -> new RuntimeException("No users found"));
    }
}

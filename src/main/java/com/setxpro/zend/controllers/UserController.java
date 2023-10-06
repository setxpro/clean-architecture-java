package com.setxpro.zend.controllers;

import com.setxpro.zend.core.domain.dtos.userDto.UserDTO;
import com.setxpro.zend.core.domain.dtos.userDto.UserFormatDTO;
import com.setxpro.zend.core.domain.repositories.UserRepository;
import com.setxpro.zend.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserRepository userRepository;

    private final UserService userService;



    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/create")
    public ResponseEntity<Object> postUser(@RequestBody @Valid UserDTO userDTO) throws Exception {
        userService.createUser(userDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body("Usuário cadastrado com sucesso!");
    }

    @GetMapping("/find-all")
    public List<UserFormatDTO> getUsers() {
        return userService.findAllUsers();
    }
}

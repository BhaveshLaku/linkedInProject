package com.bhavesh.linkedInProject.userService.service;

import com.bhavesh.linkedInProject.userService.dto.LoginRequestDto;
import com.bhavesh.linkedInProject.userService.dto.SignupRequestDto;
import com.bhavesh.linkedInProject.userService.dto.UserDto;
import com.bhavesh.linkedInProject.userService.entity.User;
import com.bhavesh.linkedInProject.userService.exception.BadRequestException;
import com.bhavesh.linkedInProject.userService.repository.UserRepository;
import com.bhavesh.linkedInProject.userService.util.BCrypt;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public UserDto signUp(SignupRequestDto signupRequestDto) {

        log.info("SignUp a user with email: {} ", signupRequestDto.getEmail());

        boolean exists = userRepository.existsByEmail(signupRequestDto.getEmail());

        if (exists) {
            throw new BadRequestException("User already exists");
        }

        User user = modelMapper.map(signupRequestDto, User.class);
        user.setPassword(BCrypt.hash(signupRequestDto.getPassword()));
        user = userRepository.save(user);
        return modelMapper.map(user, UserDto.class);

    }

//    public String login(LoginRequestDto loginRequestDto) {
//    }
}

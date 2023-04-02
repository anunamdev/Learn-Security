package com.example.security.services;

import com.example.security.payloads.UserDto;
import com.example.security.payloads.UserDto;

import java.util.List;

public interface UserService {

    UserDto createUser(UserDto userDto);

    UserDto updateUser(UserDto userDto, int userid);

    void deleteUser(int userId);

    UserDto getUser(int userId);

    List<UserDto> getAllUser();

}

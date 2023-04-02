package com.example.security.services.impl;

import com.example.security.payloads.UserDto;
import com.example.security.repositories.UserRepo;
import com.example.security.services.UserService;
import com.example.security.entities.User;
import com.example.security.exceptions.ResourceNotFound;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepo;
    @Autowired
    private ModelMapper modelmapper;

    User toEntity(UserDto userDto){return modelmapper.map(userDto,User.class);}
    UserDto toDto(User user){return modelmapper.map(user,UserDto.class);}

    @Override
    public UserDto createUser(UserDto userDto) {
        User user = toEntity(userDto);
        User save = userRepo.save(user);
        return toDto(save);

    }

    @Override
    public UserDto getUser(int userId) {
        User user = userRepo.findById(userId).orElseThrow(() -> new ResourceNotFound("USER", "ID", userId));

        return toDto(user);
    }

    @Override
    public void deleteUser(int userId) {
        User user = userRepo.findById(userId).orElseThrow(() -> new ResourceNotFound("USER", "ID", userId));
        userRepo.delete(user);
    }

    @Override
    public List<UserDto> getAllUser() {
        List<User> all = userRepo.findAll();
        List<UserDto> collect = all.stream().map(x -> toDto(x)).collect(Collectors.toList());
        return collect;
    }

    @Override
    public UserDto updateUser(UserDto userDto, int userId) {
        User user = userRepo.findById(userId).orElseThrow(() -> new ResourceNotFound("USER", "ID", userId));
        User newUser = toEntity(userDto);
        newUser.setId(user.getId());
        User save = userRepo.save(newUser);
        return toDto(save);
    }


}


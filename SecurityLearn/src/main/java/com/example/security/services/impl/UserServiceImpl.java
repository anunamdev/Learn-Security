package com.example.security.services.impl;

import com.example.security.entities.Role;
import com.example.security.entities.User;
import com.example.security.exceptions.ResourceNotFound;
import com.example.security.payloads.UserDto;
import com.example.security.repositories.RoleRepo;
import com.example.security.repositories.UserRepo;
import com.example.security.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepo;
    @Autowired
    private ModelMapper modelmapper;

    @Autowired
    private RoleRepo roleRepo;

    User toEntity(UserDto userDto) {
        return modelmapper.map(userDto, User.class);
    }

    UserDto toDto(User user) {
        return modelmapper.map(user, UserDto.class);
    }

    @Override
    public UserDto createUser(UserDto userDto) {
        User user = toEntity(userDto);
//        Role role = roleRepo.findById(2).orElseThrow(() -> new ResourceNotFound("ROLE", "ID", 2));
//        user.setRole(role);
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
    public UserDto createUser(UserDto userDto, int roleId) {
        User user = toEntity(userDto);
        Role role = roleRepo.findById(roleId).orElseThrow(() -> new ResourceNotFound("ROLE", "ID", roleId));
//        Set<Integer> hset = new HashSet<>();
//        hset.add(1);
        user.setRole((Set<Role>) role);
        User save = userRepo.save(user);
        return toDto(save);
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


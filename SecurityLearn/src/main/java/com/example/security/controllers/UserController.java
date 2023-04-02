package com.example.security.controllers;

import com.example.security.payloads.UserDto;
import com.example.security.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService ser;
    @PostMapping
    ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto) {
        UserDto user = ser.createUser(userDto);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }
    @GetMapping
    ResponseEntity<List<UserDto>> getAllUsers() {
        List<UserDto> allUser = ser.getAllUser();
        return new ResponseEntity<>(allUser, HttpStatus.OK);
    }

    @GetMapping("/{userId}")
    ResponseEntity<UserDto> getUser(@PathVariable int userId) {
        UserDto user = ser.getUser(userId);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
    @PutMapping("/{userId}")
    ResponseEntity<UserDto> updateUser(@RequestBody UserDto userDto, @PathVariable int userId) {
        UserDto updatedUser = ser.updateUser(userDto, userId);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }

    @DeleteMapping("/{userId}")
    ResponseEntity<String> deleteUser(@PathVariable int userId) {
        ser.deleteUser(userId);
        return new ResponseEntity<>("User deleted Sucessfully", HttpStatus.OK);


    }
    @GetMapping("/check")
    ResponseEntity<String> checkSecurity(){
        return new ResponseEntity<>("Authenticated Properly",HttpStatus.ACCEPTED);
    }
}

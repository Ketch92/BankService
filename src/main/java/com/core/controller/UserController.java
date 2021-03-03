package com.core.controller;

import com.core.model.User;
import com.core.model.dto.user.UserRequestDto;
import com.core.model.dto.user.UserResponseDto;
import com.core.service.UserService;
import com.core.service.mapper.user.UserMapper;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {
    private final UserService userService;
    private final UserMapper mapper;
    
    @PostMapping
    public UserResponseDto createNewUser(@RequestBody UserRequestDto requestDto) {
        User user = userService.saveOrUpdate(mapper.mapToEntity(requestDto));
        return mapper.mapToDto(user);
    }
    
    @PostMapping("/{id}")
    public UserResponseDto updateUser(@PathVariable Long id, @RequestBody UserRequestDto requestDto) {
        User user = mapper.mapToEntity(requestDto);
        user.setId(id);
        return mapper.mapToDto(userService.saveOrUpdate(user));
    }
    
    @GetMapping("/{id}")
    public UserResponseDto getUser(@PathVariable Long id) {
        return mapper.mapToDto(userService.get(id));
    }
    
    @GetMapping("/by-phone")
    public UserResponseDto getUserByPhoneNumber(@RequestParam("phone") String phoneNumber) {
        return mapper.mapToDto(userService.getByPhoneNumber(phoneNumber));
    }
    
    @DeleteMapping("/{id}")
    public void removeUserById(@PathVariable Long id) {
        userService.remove(id);
    }
}

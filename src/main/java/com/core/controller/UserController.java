package com.core.controller;

import com.core.lib.DataProcessingException;
import com.core.model.User;
import com.core.model.dto.user.UserRequestDto;
import com.core.model.dto.user.UserResponseDto;
import com.core.service.RoleService;
import com.core.service.UserService;
import com.core.service.mapper.user.UserMapper;
import java.util.Set;
import javax.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {
    private final UserService userService;
    private final RoleService roleService;
    private final UserMapper userMapper;
    
    @PostMapping
    public UserResponseDto createNewUser(@RequestBody @Valid UserRequestDto requestDto) {
        User user = userService.saveOrUpdate(userMapper.mapToEntity(requestDto));
        user.setRoles(Set.of(roleService.getByName("USER")));
        return userMapper.mapToDto(user);
    }
    
    @PutMapping("/{id}")
    public UserResponseDto updateUser(@PathVariable Long id,
                                      @RequestBody @Valid UserRequestDto requestDto) {
        User user = userMapper.mapToEntity(requestDto);
        user.setId(id);
        return userMapper.mapToDto(userService.saveOrUpdate(user));
    }
    
    @GetMapping("/{id}")
    public UserResponseDto getUser(@PathVariable Long id) {
        return userMapper.mapToDto(userService.get(id));
    }
    
    @GetMapping("/by-phone")
    public UserResponseDto getUserByPhoneNumber(@RequestParam("phone") String phoneNumber) {
        return userMapper.mapToDto(userService.getByPhoneNumber(phoneNumber));
    }
    
    @DeleteMapping("/{id}")
    public void removeUserById(@PathVariable Long id) {
        userService.remove(id);
    }
}

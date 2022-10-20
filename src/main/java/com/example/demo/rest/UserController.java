package com.example.demo.rest;

import com.example.demo.exception.NotUniqueUserException;
import com.example.demo.exception.UserNotFoundExeption;
import com.example.demo.model.User;
import com.example.demo.model.dto.UserDto;
import com.example.demo.model.dto.UserLoginDTO;
import com.example.demo.service.UserService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {
    private  final UserService userService;
    @GetMapping("/list")
    public List<UserDto> getAllUsers()
    {
        return  userService.getAll();
    }
    @GetMapping("/user/{id}")
    public User getUserInfo(@PathVariable Long id) throws UserNotFoundExeption {
        return userService.getUser(id);
    }
    @DeleteMapping("/delete/{id}")
    public void deleteUser(@PathVariable Long id) throws UserNotFoundExeption {
        System.out.println(id+"delete id");
        userService.deleteUser(id);
    }
    @PutMapping ("/update/{id}")
    public void updateUser(@PathVariable Long id, @RequestBody User user) throws UserNotFoundExeption {
        userService.updateUser(user,id);
    }
    @RequestMapping("/register")
      public  void signUp(@RequestBody UserLoginDTO userLoginDTO) throws NotUniqueUserException {
        userService.singUpUser(userLoginDTO);
    }
}

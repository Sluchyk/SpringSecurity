package com.example.demo.service;

import com.example.demo.exception.NotUniqueUserException;
import com.example.demo.exception.UserNotFoundExeption;
import com.example.demo.model.User;
import com.example.demo.model.dto.UserDto;
import com.example.demo.model.dto.UserLoginDTO;
import com.example.demo.model.repository.UserRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;

    public User getByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public void singUpUser(@Valid UserLoginDTO newUser) throws NotUniqueUserException {
        User user = userRepository.findByUsername(newUser.getUsername());
        if (user == null) {
            String password = passwordEncoder.encode(newUser.getPassword());
            newUser.setPassword(password);
            userRepository.save(modelMapper.map(newUser, User.class));
        } else throw new NotUniqueUserException("user has been already exist");
    }

    public List<UserDto> getAll() {
        ArrayList<User> usersDto = new ArrayList<>();
        userRepository.findAll().iterator().forEachRemaining(usersDto::add);
        return usersDto.stream().map(x -> modelMapper.map(x, UserDto.class)).collect(Collectors.toList());
    }

    public User getUser(Long id) throws UserNotFoundExeption {
        return isUserExist(id);
    }

    public void deleteUser(Long id) throws UserNotFoundExeption {
        System.out.println(id);
        userRepository.delete(isUserExist(id));
    }

    public void updateUser(User newUser, Long id) throws UserNotFoundExeption {
        User user = isUserExist(id);
        user.setUsername(newUser.getUsername());
        user.setPassword(newUser.getPassword());
        userRepository.save(user);

    }

    private User isUserExist(Long id) throws UserNotFoundExeption {
        return userRepository.findById(id).orElseThrow(() -> new UserNotFoundExeption("not found"));
    }


}

package com.example.demo.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserLoginDTO{
    private Long id;
    private String username;

    public UserLoginDTO(String username, String password) {
        this.username = username;
        this.password = password;
    }

    private String password;
}

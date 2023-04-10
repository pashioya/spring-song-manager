package com.kdg.springprojt5.controllers.api.dto;

import lombok.*;

@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NewUserDto {

    private String username;
    private String password;
    private String role;
}

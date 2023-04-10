package com.kdg.springprojt5.controllers.api.dto;

import lombok.*;

@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateUserDto {
    private String username;
    private String role;
}

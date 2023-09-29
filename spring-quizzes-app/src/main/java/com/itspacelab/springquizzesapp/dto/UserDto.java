package com.itspacelab.springquizzesapp.dto;

import com.itspacelab.springquizzesapp.entity.types.UserType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {
    private int id;
    private String name;
    private UserType type;
    private String surname;
    private String username;
    private String token;
}

package com.itspacelab.springquizzesapp.dto;

import com.itspacelab.springquizzesapp.entity.types.UserType;

public record SignUpDto(
        String name, String surname,
        String username, char[] password,
        UserType type
) {
}

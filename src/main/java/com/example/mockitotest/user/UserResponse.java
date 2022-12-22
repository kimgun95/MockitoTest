package com.example.mockitotest.user;

import lombok.Builder;
import lombok.Getter;

@Getter
public class UserResponse {

    private String email;
    private String pw;
    private UserRole role;

    public UserResponse (Users users) {
        this.email = users.getEmail();
        this.pw = users.getPw();
        this.role = UserRole.ROLE_USER;
    }

    @Builder
    public UserResponse(String email, String pw, UserRole role) {
        this.email = email;
        this.pw = pw;
        this.role = role;

    }
}

package com.example.mockitotest.user;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SignUpRequest {

    private String email;
    private String pw;

    @Builder
    public SignUpRequest(String email, String pw) {
        this.email = email;
        this.pw = pw;

    }

    public Users toEntity() {
        return Users.builder()
                .email(email)
                .pw(pw).
                build();
    }

}

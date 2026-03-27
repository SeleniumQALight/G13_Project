package org.api.dto.requestDto;

public class LoginRequest {
    public String userName;
    public String password;

    public LoginRequest(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }
}

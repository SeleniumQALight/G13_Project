package org.api.dto.responsDto;

import lombok.AllArgsConstructor;
import lombok.Builder;

@AllArgsConstructor
@Builder

public class AuthorDto {
    private String username;
    private String avatar;

    public AuthorDto(String username) {
        this.username = username;
    }

    public AuthorDto(){

    }

    public String getUsername() {
        return username;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    @Override
    public String toString() {
        return "AuthorDto{" +
                "username='" + username + '\'' +
                ", avatar='" + avatar + '\'' +
                '}';
    }
}

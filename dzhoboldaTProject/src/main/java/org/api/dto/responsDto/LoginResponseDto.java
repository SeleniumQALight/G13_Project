package org.api.dto.responsDto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

@JsonInclude(JsonInclude.Include.NON_NULL)
public class LoginResponseDto {
    private String userId;
    @JsonProperty("userName")
    private String userName;
    private String password;
    private String token;
    private String expires;
    private String created_date;
    private Boolean isActive;

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setUsername(String username) {
        this.userName = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setExpires(String expires) {
        this.expires = expires;
    }

    public void setCreated_date(String created_date) {
        this.created_date = created_date;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public String getUserId() {
        return userId;
    }

    public String getUsername() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public String getToken() {
        return token;
    }

    public String getExpires() {
        return expires;
    }

    public String getCreated_date() {
        return created_date;
    }

    public Boolean getActive() {
        return isActive;
    }
}

package org.api.dto.requestDto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class LoginRequestDto {
    private String userName;
    private String password;
}

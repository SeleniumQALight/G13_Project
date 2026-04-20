package org.api.dto.responseDto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DemoqaLoginDto {
    private String userId;
    private String username;
    private String password;
    private String token;
    private String expires;
    private String created_date;
    private Boolean isActive;
    }

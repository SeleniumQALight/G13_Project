package org.api.dto.responseDto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DemoQaLoginDto {
    private String userId;
    private String username;
    private String password;
    private String token;
    private String expires;
    private String created_date;
    private Boolean isActive;
    private List<DemoQaBookDto> books;
}

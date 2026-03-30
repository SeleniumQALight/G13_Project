package org.api.dto.responseDto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class DemoqaBooksListDto {
    private String userId;
    private String username;
    private List<DemoqaBookDto> books;
}

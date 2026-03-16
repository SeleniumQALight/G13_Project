package org.api.dto.requestDto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateNewPostDTO {
    private String title;
    private String body;
    private String token;
    private String uniquePost;
    private String select1;
}

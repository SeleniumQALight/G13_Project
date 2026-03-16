package org.api.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateNewPostDto {
    private String title;
    private  String body;
    private  String select1;
    private  String uniquePost;
    private String  token;

}

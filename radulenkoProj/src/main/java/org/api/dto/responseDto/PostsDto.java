package org.api.dto.responseDto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@AllArgsConstructor
@Builder
@Getter
@Setter
@ToString
@NoArgsConstructor
public class PostsDto {
    @JsonProperty("_id")
    private String id;
    private String title;
    private String body;
    @JsonProperty("select1")
    private String select;
    private String uniquePost;
    private String createdDate;
    private AuthorDto author;
    private Boolean isVisitorOwner;


}

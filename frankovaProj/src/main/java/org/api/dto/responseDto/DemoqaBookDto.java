package org.api.dto.responseDto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class DemoqaBookDto {
    private String isbn;
    private String title;
    @JsonProperty("subTitle")
    private String subTitle;
    private String author;
    @JsonProperty("publish_date")
    private String publishDate;
    private String publisher;
    private int pages;
    private String description;
    private String website;
}

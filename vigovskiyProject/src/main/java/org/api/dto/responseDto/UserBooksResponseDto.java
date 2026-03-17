package org.api.dto.responseDto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserBooksResponseDto {
    private String userId;
    private String username;
    private List<BookDto> books;
}

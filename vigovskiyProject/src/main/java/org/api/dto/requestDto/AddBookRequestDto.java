package org.api.dto.requestDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.api.dto.responseDto.BookDto;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AddBookRequestDto {
    private String userId;
    private List<BookDto> collectionOfIsbns;
}

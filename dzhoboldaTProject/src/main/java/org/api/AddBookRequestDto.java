package org.api;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;
@Data
@Builder
@AllArgsConstructor

public class AddBookRequestDto {
    private String userId;
    private List<IsbnDto> collectionOfIsbns;
}

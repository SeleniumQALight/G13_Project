package org.api.dto.requesteDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddBookRequestDto {

    private String userId;
    private List<org.dto.IsbnDto> collectionOfIsbns;

}
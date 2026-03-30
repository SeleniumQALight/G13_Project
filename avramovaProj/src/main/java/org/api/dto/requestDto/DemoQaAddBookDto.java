package org.api.dto.requestDto;

import lombok.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DemoQaAddBookDto {
    private String userId;
    private List<DemoQaIsbnDto> collectionOfIsbns;
}

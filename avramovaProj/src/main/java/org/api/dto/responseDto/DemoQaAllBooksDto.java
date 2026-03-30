package org.api.dto.responseDto;

import lombok.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DemoQaAllBooksDto {
    private List<DemoQaBookDto> books;
}

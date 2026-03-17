package org.api.dto.requestDto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DemoqaAddBooksList {
    private String userId;
    private List<DemoqaBookIsbn> collectionOfIsbns;
}

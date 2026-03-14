package org.api.dto.requestDto;

import lombok.*;

//lombok створює гетери, сетери, конструктор без аргументів, конструктор з усіма аргументами та builder для цього класу
@Getter
@Setter
@NoArgsConstructor // конструктор без аргументів
@AllArgsConstructor // конструктор з усіма аргументами
@Builder // створює builder для цього класу, щоб можна було створювати об'єкти цього класу за допомогою builder pattern
public class CreateNewPostDto {
    private String title;
    private String body;
    private String select1;
    private String uniquePost;
    private String token;



}

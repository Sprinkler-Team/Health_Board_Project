package com.sprinkler.healthboard.posts;

import lombok.*;

import java.util.Date;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PostsRequestDto {
    private String title;
    private String content;
    private Date created_at;
    private float location;

    //애자일 기법 고려
    //BE에서 넘겨줄 수 있는 정보를 다 적어둔 뒤에(1차) -> 프론트와 연동 후 수정
    public Posts toEntity(String title, String content, Date created_at, float location) {
        return Posts.builder()
                .title(title)
                .content(content)
                .created_at(created_at)
                .location(location)
                .build();
    }
}

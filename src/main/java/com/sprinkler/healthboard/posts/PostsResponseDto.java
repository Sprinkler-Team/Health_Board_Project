package com.sprinkler.healthboard.posts;

import lombok.Builder;
import lombok.Getter;

import java.util.Date;

@Getter
@Builder
public class PostsResponseDto {
    private Long posts_id;
    private String title;
    private String content;
    private float location;
    private Date created_at;
    private int like_cnt;
    private int hate_cnt;

    //댓글조회, 좋아요 조회, 개별 좋아요 조회(각 사용자 기준)

    public static PostsResponseDto fromEntity(String title, String content, float location, Date created_at, int like_cnt, int hate_cnt) {
        return PostsResponseDto.builder()
                .title(title)
                .content(content)
                .location(location)
                .created_at(created_at)
                .like_cnt(like_cnt)
                .hate_cnt(hate_cnt)
                .build();
    }
}

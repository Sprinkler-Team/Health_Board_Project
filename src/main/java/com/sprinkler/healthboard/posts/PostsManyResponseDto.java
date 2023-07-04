package com.sprinkler.healthboard.posts;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class PostsManyResponseDto {
    private Long posts_id;
    private String title;
    private Date created_at;

    @Builder
    public PostsManyResponseDto(Posts entity){
        this.posts_id = entity.getPosts_id();
        this.title = entity.getTitle();
        this.created_at = entity.getCreated_at();
    }
}

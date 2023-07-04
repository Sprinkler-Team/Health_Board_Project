package com.sprinkler.healthboard.posts;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
//@Setter
public class PostsRequestDto {
    private String title;
    private String content;
    private Date created_at;
    private float location;

    //Dto객체를 entity객체로 변환하는 역할을 함 -> 필수 아니지만 Service와 Controller사이에서 데이터를 전달 유용하게함
    //일단 쓰면 좋덴다...
    public Posts toEntity(){
        return Posts.builder()
                .title(title)
                .content(content)
                .created_at(created_at)
                .location(location)
                .build();
    }


    @Builder
    public PostsRequestDto(String title, String content, Date created_at, float location){
        this.title = title;
        this.content = content;
        this.created_at = created_at;
        this.location = location;
    }



}

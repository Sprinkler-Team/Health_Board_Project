package com.sprinkler.healthboard.posts;

import com.sprinkler.healthboard.comment.Comment;
import com.sprinkler.healthboard.memberrecommend.MemberRecommend;
import com.sprinkler.healthboard.recommend.Recommend;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class PostsOneResponseDto {
    private Long posts_id;   //reponsedto에서는 pk값 가져오는게 좋다고 함 -> 해커톤때 깨달음
    private String title;
    private String content;
    private Date created_at;
    private float location;

    private List<Comment> comment;
    private List<MemberRecommend> memberRecommend;
    private Recommend recommend;

    @Builder
    public PostsOneResponseDto(Posts entity){
        this.posts_id = entity.getPosts_id();
        this.title = entity.getTitle();
        this.content = entity.getContent();
        this.created_at = entity.getCreated_at();
        this.location = entity.getLocation();
        this.comment = entity.getComments(); //오 신기 ㅎㅎ
        this.memberRecommend = entity.getMemberRecommends();
        this.recommend = entity.getRecommend();
    }


}

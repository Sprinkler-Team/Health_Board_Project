package com.sprinkler.healthboard.memberrecommend;

import com.sprinkler.healthboard.member.Member;
import com.sprinkler.healthboard.posts.Posts;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "memberrecommend")
public class MemberRecommend {
    //필드
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_recommend_id")
    private Long member_recommend_id;

    @Column(name = "posts_id")
    @ManyToOne
    @JoinColumn(name = "posts_id")
    private Posts posts;

    @Column(name = "member_id")
    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @Column(nullable = false)
    private boolean is_like;

    //빌더
    @Builder
    public MemberRecommend(Posts posts, Member member, boolean is_like) {
        this.posts = posts;
        this.member = member;
        this.is_like = is_like;
    }
}

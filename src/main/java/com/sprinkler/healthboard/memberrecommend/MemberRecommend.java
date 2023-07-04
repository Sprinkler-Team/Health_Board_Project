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


    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @Column(nullable = false)
    private boolean is_like;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "memberRecommend") //카밀 케이스 사용!
    private Posts posts;

    //빌더
    @Builder
    public MemberRecommend(Member member, boolean is_like) {
        this.member = member;
        this.is_like = is_like;
    }
}

package com.sprinkler.healthboard.comment;

import com.sprinkler.healthboard.basetime.BaseTimeEntity;
import com.sprinkler.healthboard.member.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "Comment")
public class Comment {
    //필드
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long comment_id;

    @Column(name = "posts_id")
    @ManyToOne(targetEntity = Posts.class)
    @JoinColumn(name = "posts_id")
    private Posts posts;

    @Column(name = "member_id")
    @ManyToOne(targetEntity = Member.class)
    @JoinColumn(name = "member_id")
    private Member member;

    @Column(length = 200, nullable = false)
    private String content;

    @Column(nullable = false)
    private Date created_at;

    //빌더
    @Builder
    public Comment(Posts posts, Member member, String content, Date created_at) {
        this.posts = posts;
        this.member = member;
        this.content = content;
        this.created_at = created_at;
    }
}

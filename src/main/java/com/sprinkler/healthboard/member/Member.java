package com.sprinkler.healthboard.member;


import com.sprinkler.healthboard.comment.Comment;
import com.sprinkler.healthboard.memberrecommend.MemberRecommend;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "Member")
public class Member {
    //필드
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id", unique = true, nullable = false)
    private Long member_id;
    @Column(length = 25, nullable = false)
    private String nickname;
    @Column(length = 50, nullable = false)
    private String email;
    @Column(nullable = false)
    private Role role;

    //casecade, orphanRemoval 적용 유무?
    @OneToMany(mappedBy = "Member")
    private List<Comment> comments = new ArrayList<>();

    @OneToMany(mappedBy = "Member")
    private List<MemberRecommend> memberRecommends = new ArrayList<>();

    //빌더
    @Builder
    public Member(String nickname, String email, Role role) {
        this.nickname = nickname;
        this.email = email;
        this.role = role;
    }
}

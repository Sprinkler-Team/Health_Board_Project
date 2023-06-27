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
@Table(name = "member")
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

    //casecade, orphanRemoval 적용 유무(서비스 로직 짜면서 수정)
    //영속성??
    @OneToMany(mappedBy = "member")
    private List<Comment> comments = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    private List<MemberRecommend> memberRecommends = new ArrayList<>();

    //빌더
    /*
    빌더란??
     */
    @Builder
    public Member(String nickname, String email, Role role) {
        this.nickname = nickname;
        this.email = email;
        this.role = role;
    }
}

/*
일대다 X 다대일 O 이유:
일대다를 사용하는 경우 주인 테이블의 칼럼이 바뀌었을 때 연속적으로 바뀌는 테이블이 많아서 사용X -> 실무에선 일대다 사용 안함
다대일만 사용하기 때문에 @JoinColum 을 @Manytoone 을 설정한 하위 테이블에 설정해준다.
 */
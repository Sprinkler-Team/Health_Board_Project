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
    /*
    [영속성]
    데이터를 생성한 프로그램이 종료되어도 사라지지 않는 데이터 특성
    영속성이 지켜지지 않으면? -> 프로그램 종료 시 데이터 같이 사라짐
    그래서 파일이나 DB에 데이터를 저장하는 것

    [JPA에서 영속성]
    JPA의 핵심 내용은 엔티티가 영속성 컨텍스트에 포함되어 있냐 아니냐로 갈린다.
    JPA의 엔티티 매니저가 활성화된 상태로 트랜잭션(@Transactional) 안에서 DB에서 데이터를 가져오면 이 데이터는 영속성 컨텍스트가 유지된 상태이다.
        -> DB는 영구 저장하는 환경이니까
    JPA 는 보통 트랜잭션을 커밋하는 순간 영속성 컨텍스트에 새로 저장된 Entity를 데이터베이스에 반영한다.

    [영속성 컨텍스트]
    엔티티를 영구 저장하는 환경
     */
    @OneToMany(mappedBy = "member")
    private List<Comment> comments = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    private List<MemberRecommend> memberRecommends = new ArrayList<>();

    //빌더
    /*
    [빌더패턴]
    객체를 정의하고 그 객체를 생성할 때 보통 생성자를 통해 생성하는 것을 생각한다.
    근데 생성자를 통해 객체를 생성하면 몇 가지 단점이 있어서 객체를 생성하는 별도 builder를 두는데
    이게 우리가 스프링부트에서 쓰고 있는 빌더패턴이다.
     */
    @Builder
    public Member(String nickname, String email, Role role) {
        this.nickname = nickname;
        this.email = email;
        this.role = role;
    }
}

/*
[일대다 단방향보다 다대일 양방향을 선호하는 이유]
일대다 단방향은 매핑한 객체가 관리하는 외래키가 다른 테이블에 있다.
본인 테이블에 외래 키가 있으면 엔티티의 저장과 연관관계 처리를 INSERT 쿼리 한 번으로 끝낼 수 있는데,
다른 테이블에 외래키가 있으면 연관관계 처리를 위한 UPDATE 쿼리를 추가로 실행해야 한다.
또 자신의 테이블이 아닌 다른 테이블의 외래 키를 관리하게 되기 때문에, 관리가 어려워진다.

일대다 양방향은 사실상 존재 X
일대다 관계에선 외래키가 항상 다 쪽에 있음(@ManytoOne에 mappedBy가 없는 이유)
 */
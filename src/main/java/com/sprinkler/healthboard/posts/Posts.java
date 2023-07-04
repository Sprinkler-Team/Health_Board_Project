package com.sprinkler.healthboard.posts;


import com.sprinkler.healthboard.comment.Comment;
import com.sprinkler.healthboard.member.Member;
import com.sprinkler.healthboard.memberrecommend.MemberRecommend;
import com.sprinkler.healthboard.recommend.Recommend;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


//엔터티란? -> DB에 쓰일 필드와 여러 엔터티간의 관계를 정의
//필드라는 것은 엔터티의 각 columnn
@Entity     //이 클래스가 엔터티임을 알려줌
//@Builder  //해당 클래스에 해당하는 엔터티 객체를 만들 때 빌더 패턴을 이용하여 만들수 있도록 하는 어노테이션 -> 나중에 다른곳에서  Board.builder(). {여러가지 필드의 초기값 선언 }. build() 형태로 객체를 만들 수 있음
//@AllArgsConstructor //모든 필드를 파라미터로 포함하는 생성자 자동생성    -> 매우 비권장됨(클래스에 존재하는 모든 필드에 대한 생성자를 자동으로 생성하는데, 인스턴스 멤버의 선언 순서에 영향을 받기 때문에 변수의 순서를 바꾸면 생성자의 입력 값 순서도 바뀌게 되어 검출되지 않는 치명적인 오류를 발생시킬 수 있음)
//따라서 NoArgsConstructor를 사용하면서 옵션으로 (access = AccessLevel.PROTECTED)를 준다
//추가로 이렇게만 하면 Builder에 오류가 생기므로 생성자에 @Builder 적용
@NoArgsConstructor(access = AccessLevel.PROTECTED)  //파라미터가 없는 생성자 자동생성
@Getter     //각 필드값 조회할 수 있는 getter자동생성(ex getId)
@Table(name = "posts")
public class Posts {

    @Id //PK값 명시
    @GeneratedValue(strategy = GenerationType.IDENTITY) //PK가 자동으로 1씩 증가
    @Column(name = "posts_id", unique = true, nullable = false)
    private Long posts_id;

    @Column(nullable = false, length = 50)
    private String title;

    @Column(nullable = false, length = 2000)
    private String content;

    @Column(nullable = false)
    private Date created_at;

    @Column
    private float location;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(mappedBy = "posts")
    List<Comment> comments = new ArrayList<>();

    @OneToMany(mappedBy="posts")
    List<MemberRecommend> memberRecommends = new ArrayList<>();

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "recommend_id")
    private Recommend recommend;

    //생성자에 builder적용
    @Builder
    public Posts(String title, String content, Date created_at, float location){
        this.title=title;
        this.content=content;
        this.created_at=created_at;
        this.location=location;
    }

    //하다가 알았는 nullable는 기본값 true로 되어있다.


      //[일대일(1:1)관계]
    //일대다(1:N), 다대일(N:1) 관계에서는 다(N) 쪽이 항상 외래 키를 가지고 있지만
    //일대일(1:1) 관계에서는 주 테이블이나 대상이 되는 테이블 양쪽 모두 외래 키를 가질 수 있음
    //때문에 일대일 관계를 적용할 때는 주 테이블과 대상이 되는 테이블, 어느 쪽에 외래 키를 둘지 선택해야 함
    //JPA에서는 외래 키를 갖는 쪽이 연관 관계의 주인이 되고,
    //연관 관계의 주인이 데이터베이스 연관 관계와 매핑되어 외래 키를 관리(등록, 수정, 삭제)할 수 있기 때문에 해당 설정이 중요

    //Posts와 Recommend는 1:1관계 -> 주 테이블 Posts / 대상 테이블 Recommend
    //mappedby를 통해 읽기 전용 해줌!

    //case1. 1:1 단방향
    // 주테이블에
//      @OneToOne(fetch = FetchType.LAZY)
//      @JoinColumn(name = "recommend_id")
//      private Recommend recommend;

    //case2. 1:1 양방향
    // 주테이블에
//      @OneToOne(fetch = FetchType.LAZY)
//      @JoinColumn(name = "recommend_id")
//      private Recommend recommend;
    // 대상테이블에
//      @OneToOne(fetch = FetchType.LAZY, mappedBy = "recommend")
//      private Posts posts;

}

package com.sprinkler.healthboard.recommend;

import com.sprinkler.healthboard.posts.Posts;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@NoArgsConstructor
@Getter
@Table(name="recommend")
public class Recommend {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "recommend_id", unique = true, nullable = false)
    private Long recommend_id;

    @Column(nullable = false)
    private int like_cnt;

    @Column(nullable = false)
    private int hate_cnt;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "recommend")
    private Posts posts;

    @Builder
    public Recommend(int like_cnt, int hate_cnt){
        this.like_cnt=like_cnt;
        this.hate_cnt=hate_cnt;
    }

}

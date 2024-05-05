package com.seoultech.sanEseo.post_district.domain;


import com.seoultech.sanEseo.district.domain.District;
import com.seoultech.sanEseo.post.domain.Post;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class PostDistrict {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    //연관된 엔티티 같이 제거
    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "post_id")
    private Post post;

    @ManyToOne
    @JoinColumn(name = "district_id")
    private District district;

    public PostDistrict(Post post, District district) {
        this.post = post;
        this.district = district;
    }

}

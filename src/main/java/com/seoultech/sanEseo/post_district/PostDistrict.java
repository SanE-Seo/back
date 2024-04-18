package com.seoultech.sanEseo.post_district;


import com.seoultech.sanEseo.district.District;
import com.seoultech.sanEseo.post.domain.Post;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class PostDistrict {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
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

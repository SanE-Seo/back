package com.seoultech.sanEseo.post_district;

import com.seoultech.sanEseo.district.District;
import com.seoultech.sanEseo.district.DistrictPort;
import com.seoultech.sanEseo.post.domain.Post;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
public class PostDistrictService {

    private final DistrictPort districtPort;
    private final PostDistrictPort postDistrictPort;

    public PostDistrictService(DistrictPort districtPort, PostDistrictPort postDistrictPort) {
        this.districtPort = districtPort;
        this.postDistrictPort = postDistrictPort;
    }

    public void createPostDistrictRelation(Post post, Long districtId) {
        District district = districtPort.findById(districtId);
        PostDistrict postDistrict = new PostDistrict(post, district);

        postDistrictPort.save(postDistrict);

    }

}

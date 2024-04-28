package com.seoultech.sanEseo.district.adapter;

import com.seoultech.sanEseo.district.domain.District;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;


public interface DistrictRepository extends JpaRepository<District, Long> {
    Optional<District> findByName(String districtName);
}

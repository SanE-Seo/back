package com.seoultech.sanEseo.district.adapter;

import com.seoultech.sanEseo.district.domain.District;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;


public interface DistrictRepository extends JpaRepository<District, Long> {
    Optional<District> findByName(String districtName);

    @Query("SELECT d FROM District d WHERE d.name LIKE :name%")
    List<District> findByNameStartingWith(String name);
}

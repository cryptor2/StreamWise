package com.prince.video_service.repositories;


import com.prince.common.data.entities.Course;
import com.prince.common.data.entities.Video;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VideoRepository extends JpaRepository<Video, Long> {

    List<Video> findAllByCourse(Course course);
    @Query("SELECT v.videoPath FROM Video v WHERE v.videoId = :id")
    Optional<String> findPathById(@Param("id") Long id);
}

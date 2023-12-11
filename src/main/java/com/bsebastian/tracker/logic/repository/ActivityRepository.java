package com.bsebastian.tracker.logic.repository;

import com.bsebastian.tracker.logic.model.Activity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ActivityRepository extends JpaRepository<Activity, Long> {
    @Query("SELECT a FROM Activity a WHERE a.userEntity.id = :userId")
    List<Activity> findAllByUserId(@Param("userId") Long userId);

}
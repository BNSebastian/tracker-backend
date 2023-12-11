package com.bsebastian.tracker.app.repository;

import com.bsebastian.tracker.app.model.Activity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActivityRepository extends JpaRepository<Activity, Long> {  }
package com.bsebastian.tracker.app.entities.Activity.repository;

import com.bsebastian.tracker.app.entities.Activity.model.Activity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActivityRepository extends JpaRepository<Activity, Long> {  }
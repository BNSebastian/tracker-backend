package com.bsebastian.tracker.app.entities.ActivityType.repository;

import com.bsebastian.tracker.app.entities.ActivityType.model.ActivityType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActivityTypeRepository extends JpaRepository<ActivityType, Long> {  }
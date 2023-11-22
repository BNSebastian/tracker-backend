package com.bsebastian.tracker.app.entities.repositories;

import com.bsebastian.tracker.app.entities.models.ActivityType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActivityTypeRepository extends JpaRepository<ActivityType, Long> {  }
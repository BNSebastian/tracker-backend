package com.bsebastian.tracker.app.persistence.repositories;

import com.bsebastian.tracker.app.models.ActivityType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActivityTypeRepository extends JpaRepository<ActivityType, Long> {  }
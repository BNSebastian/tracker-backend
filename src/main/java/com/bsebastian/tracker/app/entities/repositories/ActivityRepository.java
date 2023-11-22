package com.bsebastian.tracker.app.entities.repositories;

import com.bsebastian.tracker.app.entities.models.Activity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActivityRepository extends JpaRepository<Activity, Long> {  }
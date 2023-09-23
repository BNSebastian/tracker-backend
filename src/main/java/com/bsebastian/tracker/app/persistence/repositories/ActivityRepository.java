package com.bsebastian.tracker.app.persistence.repositories;

import com.bsebastian.tracker.app.models.Activity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActivityRepository extends JpaRepository<Activity, Long> {  }
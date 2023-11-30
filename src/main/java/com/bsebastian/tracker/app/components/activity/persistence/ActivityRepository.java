package com.bsebastian.tracker.app.components.activity.persistence;

import com.bsebastian.tracker.app.components.activity.model.Activity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActivityRepository extends JpaRepository<Activity, Long> {  }
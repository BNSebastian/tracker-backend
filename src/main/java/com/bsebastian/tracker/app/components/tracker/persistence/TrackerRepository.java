package com.bsebastian.tracker.app.components.tracker.persistence;

import com.bsebastian.tracker.app.components.tracker.model.Tracker;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrackerRepository extends JpaRepository<Tracker, Long> {  }

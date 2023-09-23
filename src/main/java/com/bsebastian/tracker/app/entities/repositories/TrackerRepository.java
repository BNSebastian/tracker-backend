package com.bsebastian.tracker.app.entities.repositories;

import com.bsebastian.tracker.app.entities.models.Tracker;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrackerRepository extends JpaRepository<Tracker, Long> {  }

package com.bsebastian.tracker.app.repository;

import com.bsebastian.tracker.app.model.Type;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TypeRepository extends JpaRepository<Type, Long> {  }
package com.bsebastian.tracker.logic.repository;

import com.bsebastian.tracker.logic.model.Type;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TypeRepository extends JpaRepository<Type, Long> {  }
package com.bsebastian.tracker.app.components.type.persistence;

import com.bsebastian.tracker.app.components.type.model.Type;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TypeRepository extends JpaRepository<Type, Long> {  }
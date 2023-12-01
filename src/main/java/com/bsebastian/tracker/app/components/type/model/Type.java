package com.bsebastian.tracker.app.components.type.model;

import com.bsebastian.tracker.app.components.activity.model.Activity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "types")
public class Type {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "type",
               cascade = CascadeType.ALL,
               orphanRemoval = true)
    private List<Activity> activities;
}
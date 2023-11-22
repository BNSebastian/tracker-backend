package com.bsebastian.tracker.app.entities.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "activity_types")
public class ActivityType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

//    @JsonManagedReference
//    @OneToMany(mappedBy = "activityType",
//               cascade = CascadeType.ALL,
//               orphanRemoval = true)
//    private List<Activity> usedIn = new ArrayList<>();
}
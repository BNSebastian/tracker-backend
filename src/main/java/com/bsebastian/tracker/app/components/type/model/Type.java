package com.bsebastian.tracker.app.components.type.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "activity_types")
public class Type {
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
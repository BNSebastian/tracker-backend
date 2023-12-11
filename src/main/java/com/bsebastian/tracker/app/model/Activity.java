package com.bsebastian.tracker.app.model;

import com.bsebastian.tracker.core.model.UserEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "activities")
public class Activity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Name cannot be null")
    @Size(min = 5, max = 255, message = "Name must be between 5 and 255 characters")
    private String name;

    @ManyToOne
    @JoinColumn(name="users_id", nullable=false)
    public UserEntity userEntity;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "types_id")
    private Type type;

    private String description;

    private LocalDateTime startedOn;

    private LocalDateTime endedOn;

    private Long timeElapsedInMinutes;
}
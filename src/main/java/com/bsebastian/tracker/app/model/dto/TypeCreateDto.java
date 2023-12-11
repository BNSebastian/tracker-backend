package com.bsebastian.tracker.app.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // lombok
@AllArgsConstructor // lombok
@NoArgsConstructor // lombok
@Builder // needed for tests
public class TypeCreateDto {
    private String name;
}

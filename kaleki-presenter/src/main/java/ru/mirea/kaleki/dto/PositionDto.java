package ru.mirea.kaleki.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The type Position dto.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PositionDto {
    /**
     * The Id.
     */
    protected long id;
    /**
     * The Name.
     */
    protected String name;
}

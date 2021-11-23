package ru.mirea.kaleki.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The type Change project status dto payload.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChangeProjectStatusDtoPayload {
    /**
     * The Project id.
     */
    protected long project_id;
    /**
     * The Status.
     */
    protected String status;
}

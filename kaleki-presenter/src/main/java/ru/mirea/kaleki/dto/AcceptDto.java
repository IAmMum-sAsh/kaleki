package ru.mirea.kaleki.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The type Accept dto.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AcceptDto {
    /**
     * The User id.
     */
    protected long user_id;
    /**
     * The Company id.
     */
    protected long company_id;
}

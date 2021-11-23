package ru.mirea.kaleki.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The type Company dto payload.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CompanyDtoPayload {
    /**
     * The Name.
     */
    protected String name;
    /**
     * The Address.
     */
    protected String address;
}

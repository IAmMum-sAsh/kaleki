package ru.mirea.kaleki.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AcceptDto {
    protected long user_id;
    protected long company_id;
}

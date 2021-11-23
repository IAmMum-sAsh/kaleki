package ru.mirea.kaleki.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.mirea.kaleki.security.dto.UserDto;

import java.util.List;

/**
 * The type Company dto extended.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CompanyDtoExtended {
    /**
     * The Id.
     */
    protected long id;
    /**
     * The Name.
     */
    protected String name;
    /**
     * The Address.
     */
    protected String address;
    /**
     * The Ceo username.
     */
    protected String CEO_username;
    /**
     * The Ceo email.
     */
    protected String CEO_email;

    /**
     * The Projects.
     */
    protected List<ProjectDto> projects;
}

package ru.mirea.kaleki.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.mirea.kaleki.security.dto.UserDto;

import java.sql.Date;
import java.util.List;

/**
 * The type Project dto extended.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProjectDtoExtended {
    /**
     * The Id.
     */
    protected long id;
    /**
     * The Name.
     */
    protected String name;
    /**
     * The Company id.
     */
    protected long company_id;
    /**
     * The Company name.
     */
    protected String company_name;
    /**
     * The Ceo username.
     */
    protected String CEO_username;
    /**
     * The Ceo email.
     */
    protected String CEO_email;
    /**
     * The Start date.
     */
    protected Date start_date;
    /**
     * The Status.
     */
    protected String status;

    /**
     * The Users.
     */
    protected List<UserDto> users;
}

package ru.mirea.kaleki.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.mirea.kaleki.security.dto.UserDto;

import java.sql.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProjectDtoExtended {
    protected long id;
    protected String name;
    protected long company_id;
    protected String company_name;
    protected String CEO_username;
    protected String CEO_email;
    protected Date start_date;
    protected String status;

    protected List<UserDto> users;
}

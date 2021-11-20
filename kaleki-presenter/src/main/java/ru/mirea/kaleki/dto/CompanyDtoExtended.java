package ru.mirea.kaleki.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.mirea.kaleki.security.dto.UserDto;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CompanyDtoExtended {
    protected long id;
    protected String name;
    protected String address;
    protected String CEO_username;
    protected String CEO_email;

    protected List<ProjectDto> projects;
}

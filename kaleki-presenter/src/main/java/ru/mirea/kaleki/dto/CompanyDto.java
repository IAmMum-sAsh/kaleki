package ru.mirea.kaleki.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.mirea.kaleki.entitys.Company;
import ru.mirea.kaleki.security.dto.UserDto;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CompanyDto {
    protected long id;
    protected String name;
    protected String address;
    protected UserDto CEO;

    public CompanyDto(Company company) {
        this.id = company.getId();
        this.name = company.getName();
        this.address = company.getAddress();
        this.CEO = new UserDto(company.getCEO());
    }
}

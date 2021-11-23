package ru.mirea.kaleki.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.mirea.kaleki.entitys.Company;
import ru.mirea.kaleki.security.dto.UserDto;

/**
 * The type Company dto.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CompanyDto {
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
     * The Ceo.
     */
    protected UserDto CEO;

    /**
     * Instantiates a new Company dto.
     *
     * @param company the company
     */
    public CompanyDto(Company company) {
        this.id = company.getId();
        this.name = company.getName();
        this.address = company.getAddress();
        this.CEO = new UserDto(company.getCEO());
    }
}

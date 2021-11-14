package ru.mirea.kaleki.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.mirea.kaleki.dto.CompanyDtoPayload;
import ru.mirea.kaleki.entitys.Company;
import ru.mirea.kaleki.entitys.Project;
import ru.mirea.kaleki.entitys.User;
import ru.mirea.kaleki.repositories.CompanyRepository;
import ru.mirea.kaleki.repositories.ProjectRepository;
import ru.mirea.kaleki.repositories.UsersOnProjectsRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CompanyService {
    @Autowired
    protected CompanyRepository companyRepository;

    @Autowired
    protected ProjectRepository projectRepository;

    @Autowired
    protected UsersOnProjectsRepository usersOnProjectsRepository;

    public List<Company> getCompanies(){
        return companyRepository.findAll();
    }

    public Company getCompanyById(long id) {return companyRepository.getById(id);}

    public Company createCompany(CompanyDtoPayload companyDtoPayload, User ceo){
        Company company = new Company();

        company.setAddress(companyDtoPayload.getAddress());
        company.setName(companyDtoPayload.getName());
        company.setCEO(ceo);

        return companyRepository.save(company);
    }
}

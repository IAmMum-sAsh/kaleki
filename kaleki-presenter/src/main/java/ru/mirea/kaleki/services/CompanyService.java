package ru.mirea.kaleki.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.mirea.kaleki.dto.CompanyDtoExtended;
import ru.mirea.kaleki.dto.CompanyDtoPayload;
import ru.mirea.kaleki.dto.ProjectDto;
import ru.mirea.kaleki.entitys.Company;
import ru.mirea.kaleki.entitys.Project;
import ru.mirea.kaleki.entitys.User;
import ru.mirea.kaleki.repositories.CompanyRepository;
import ru.mirea.kaleki.repositories.ProjectRepository;
import ru.mirea.kaleki.repositories.UsersOnProjectsRepository;
import ru.mirea.kaleki.security.dto.UserDto;

import java.util.ArrayList;
import java.util.Arrays;
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

    @Autowired
    protected ProjectService projectService;

    @Autowired
    protected UserService userService;

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

    public CompanyDtoExtended companyInfo(long id){
        CompanyDtoExtended companyDtoExtended = new CompanyDtoExtended();
        Company company = this.getCompanyById(id);

        companyDtoExtended.setId(id);
        companyDtoExtended.setName(company.getName());
        companyDtoExtended.setAddress(company.getAddress());
        companyDtoExtended.setCEO_email(company.getCEO().getEmail());
        companyDtoExtended.setCEO_username(company.getCEO().getUsername());

        List<ProjectDto> currentProjects = new ArrayList<>();

        List<Project> projects = projectService.getProjects();

        for (Project project : projects){
            ProjectDto projectDto = new ProjectDto(project);
            if (project.getCompany().getId() == id && !currentProjects.contains(projectDto)){
                currentProjects.add(projectDto);
            }
        }

        companyDtoExtended.setProjects(currentProjects);

        return companyDtoExtended;
    }
}

package ru.mirea.kaleki.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.mirea.kaleki.dto.CompanyDtoPayload;
import ru.mirea.kaleki.dto.ProjectDtoPayload;
import ru.mirea.kaleki.entitys.Company;
import ru.mirea.kaleki.entitys.Project;
import ru.mirea.kaleki.entitys.User;
import ru.mirea.kaleki.repositories.ProjectRepository;
import ru.mirea.kaleki.repositories.UsersOnProjectsRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ProjectService {
    @Autowired
    protected ProjectRepository projectRepository;

    @Autowired
    protected CompanyService companyService;

    @Autowired
    protected UsersOnProjectsRepository usersOnProjectsRepository;

    public List<Project> getProjects(){
        return projectRepository.findAll();
    }

    public Optional<Project> findByName(String name) {
        return projectRepository.findByName(name);
    }

    public Optional<Project> findById(long id) {
        return projectRepository.findById(id);
    }

    public Project createProject(ProjectDtoPayload projectDtoPayload){
        Project project = new Project();

        project.setCompany(companyService.getCompanyById(projectDtoPayload.getCompany()));
        project.setName(projectDtoPayload.getName());
        project.setStart_date(projectDtoPayload.getStart_date());
        project.setStatus(projectDtoPayload.getStatus());

        return projectRepository.save(project);
    }
}

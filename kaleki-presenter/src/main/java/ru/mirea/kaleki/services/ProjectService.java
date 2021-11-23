package ru.mirea.kaleki.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.mirea.kaleki.dto.*;
import ru.mirea.kaleki.entitys.Company;
import ru.mirea.kaleki.entitys.Project;
import ru.mirea.kaleki.entitys.User;
import ru.mirea.kaleki.repositories.ProjectRepository;
import ru.mirea.kaleki.repositories.UsersOnProjectsRepository;
import ru.mirea.kaleki.security.dto.UserDto;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * The type Project service.
 */
@Service
public class ProjectService {
    /**
     * The Project repository.
     */
    @Autowired
    protected ProjectRepository projectRepository;

    /**
     * The Company service.
     */
    @Autowired
    protected CompanyService companyService;

    /**
     * The Users on projects repository.
     */
    @Autowired
    protected UsersOnProjectsRepository usersOnProjectsRepository;

    /**
     * The User service.
     */
    @Autowired
    protected UserService userService;

    /**
     * Get projects list.
     *
     * @return the list
     */
    public List<Project> getProjects(){
        return projectRepository.findAll();
    }

    /**
     * Find by name optional.
     *
     * @param name the name
     * @return the optional
     */
    public Optional<Project> findByName(String name) {
        return projectRepository.findByName(name);
    }

    /**
     * Find by id optional.
     *
     * @param id the id
     * @return the optional
     */
    public Optional<Project> findById(long id) {
        return projectRepository.findById(id);
    }

    /**
     * Create project project.
     *
     * @param projectDtoPayload the project dto payload
     * @return the project
     */
    public Project createProject(ProjectDtoPayload projectDtoPayload){
        Project project = new Project();

        project.setCompany(companyService.getCompanyById(projectDtoPayload.getCompany()));
        project.setName(projectDtoPayload.getName());
        project.setStart_date(projectDtoPayload.getStart_date());
        project.setStatus(projectDtoPayload.getStatus());

        return projectRepository.save(project);
    }

    /**
     * Update project status project.
     *
     * @param payload the payload
     * @return the project
     */
    public Project updateProjectStatus(ChangeProjectStatusDtoPayload payload){
        Project project = projectRepository.findById(payload.getProject_id()).get();

        project.setStatus(payload.getStatus());

        return projectRepository.save(project);
    }

    /**
     * Project info project dto extended.
     *
     * @param id the id
     * @return the project dto extended
     */
    public ProjectDtoExtended projectInfo(long id){
        ProjectDtoExtended projectDtoExtended = new ProjectDtoExtended();
        Project project = this.findById(id).get();

        projectDtoExtended.setId(id);
        projectDtoExtended.setName(project.getName());
        projectDtoExtended.setCompany_name(project.getCompany().getName());
        projectDtoExtended.setCompany_id(project.getCompany().getId());
        projectDtoExtended.setCEO_email(project.getCompany().getCEO().getEmail());
        projectDtoExtended.setCEO_username(project.getCompany().getCEO().getUsername());
        projectDtoExtended.setStart_date(project.getStart_date());
        projectDtoExtended.setStatus(project.getStatus());

        List<User> users = userService.findAll();

        List<UserDto> currentUsers = new ArrayList<>();

        for (User user : users){
            UserDto userDto = new UserDto(user);
            List<String> userProjects = Arrays.asList(user.getProjects().split("#"));
            if (userProjects.contains(project.getName())){
                currentUsers.add(userDto);
            }
        }

        projectDtoExtended.setUsers(currentUsers);

        return projectDtoExtended;
    }
}

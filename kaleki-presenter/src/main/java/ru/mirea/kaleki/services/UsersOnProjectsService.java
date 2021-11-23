package ru.mirea.kaleki.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.mirea.kaleki.dto.UsersOnProjectsDto;
import ru.mirea.kaleki.dto.UsersOnProjectsDtoPayload;
import ru.mirea.kaleki.entitys.PKs.UsersOnProjectsPK;
import ru.mirea.kaleki.entitys.User;
import ru.mirea.kaleki.entitys.UsersOnProjects;
import ru.mirea.kaleki.repositories.UsersOnProjectsRepository;

import java.util.Arrays;
import java.util.List;

/**
 * The type Users on projects service.
 */
@Service
@Slf4j
public class UsersOnProjectsService {
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
     * The Project service.
     */
    @Autowired
    protected ProjectService projectService;

    /**
     * The Position service.
     */
    @Autowired
    protected PositionService positionService;

    /**
     * Sets worker on project.
     *
     * @param usersOnProjectsDtoPayload the users on projects dto payload
     * @return the worker on project
     */
    public UsersOnProjects setWorkerOnProject(UsersOnProjectsDtoPayload usersOnProjectsDtoPayload) {
        UsersOnProjects usersOnProjects = new UsersOnProjects();
        User user = userService.findById(usersOnProjectsDtoPayload.getUser_id()).get();

        usersOnProjects.setUsersOnProjectsPK(new UsersOnProjectsPK(user, projectService.findById(usersOnProjectsDtoPayload.getProject_id()).get()));
        usersOnProjects.setPosition(positionService.findById(usersOnProjectsDtoPayload.getPosition_id()));
        usersOnProjects.setRate(usersOnProjectsDtoPayload.getRate());
        usersOnProjects.setBase_salary(usersOnProjectsDtoPayload.getBase_salary());
        usersOnProjects.setWeek_work_time(usersOnProjectsDtoPayload.getWeek_work_time());

        List<String> companies = Arrays.asList(user.getCompany().split("#"));
        List<String> projects = Arrays.asList(user.getProjects().split("#"));
        if (!companies.contains(usersOnProjects.getUsersOnProjectsPK().getProject().getCompany().getName())) {
            userService.updateUserCompany(user, usersOnProjects.getUsersOnProjectsPK().getProject().getCompany().getName());
        }

        return usersOnProjectsRepository.save(usersOnProjects);
    }

    /**
     * Find by users on projects pk users on projects.
     *
     * @param usersOnProjectsPK the users on projects pk
     * @return the users on projects
     */
    public UsersOnProjects findByUsersOnProjectsPK(UsersOnProjectsPK usersOnProjectsPK){
        UsersOnProjects usersOnProjects = usersOnProjectsRepository.findByUsersOnProjectsPK(usersOnProjectsPK).get();
        return usersOnProjects;
    }

    /**
     * Update info users on projects.
     *
     * @param usersOnProjects the users on projects
     * @return the users on projects
     */
    public UsersOnProjects updateInfo(UsersOnProjects usersOnProjects){
        return usersOnProjectsRepository.save(usersOnProjects);

    }
}

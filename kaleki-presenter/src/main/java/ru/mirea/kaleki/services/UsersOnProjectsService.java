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

@Service
@Slf4j
public class UsersOnProjectsService {
    @Autowired
    protected UsersOnProjectsRepository usersOnProjectsRepository;

    @Autowired
    protected UserService userService;

    @Autowired
    protected ProjectService projectService;

    @Autowired
    protected PositionService positionService;

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
        if (!projects.contains(usersOnProjects.getUsersOnProjectsPK().getProject().getName())) {
            userService.updateUserProject(user, usersOnProjects.getUsersOnProjectsPK().getProject().getName());
        }

        return usersOnProjectsRepository.save(usersOnProjects);
    }

    public UsersOnProjects findByUsersOnProjectsPK(UsersOnProjectsPK usersOnProjectsPK){
        UsersOnProjects usersOnProjects = usersOnProjectsRepository.findByUsersOnProjectsPK(usersOnProjectsPK).get();
        return usersOnProjects;
    }

    public UsersOnProjects updateInfo(UsersOnProjects usersOnProjects){
        return usersOnProjectsRepository.save(usersOnProjects);

    }
}

package ru.mirea.kaleki.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.mirea.kaleki.dto.*;
import ru.mirea.kaleki.entitys.User;
import ru.mirea.kaleki.entitys.UsersOnProjects;
import ru.mirea.kaleki.exceptions.MyNotFoundException;
import ru.mirea.kaleki.security.dto.UserDto;
import ru.mirea.kaleki.services.CompanyService;
import ru.mirea.kaleki.services.ProjectService;
import ru.mirea.kaleki.services.UserService;
import ru.mirea.kaleki.services.UsersOnProjectsService;

import java.sql.Date;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/api")
public class ManagerController {
    @Autowired
    protected UserService userService;

    @Autowired
    protected UsersOnProjectsService usersOnProjectsService;

    @PostMapping ("/set_worker_on_project")
    public ResponseEntity<UsersOnProjectsDto> setWorkerOnProject(@RequestBody UsersOnProjectsDtoPayload usersOnProjectsDtoPayload){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserName = authentication.getName();
        User currentUser = userService.findByEmail(currentUserName).orElseThrow(
                () -> {throw new MyNotFoundException("User not found");}
        );

        UsersOnProjects usersOnProjects;
        UsersOnProjectsDto usersOnProjectsDto = new UsersOnProjectsDto();

        User user = userService.findById(usersOnProjectsDtoPayload.getUser_id()).get();

        List<String> workerCompanies = Arrays.asList(user.getCompany().split("#"));
        List<String> managerCompanies = Arrays.asList(currentUser.getCompany().split("#"));
        boolean flag = false;

        if (workerCompanies.get(0).equals("Base company")){
            flag = true;
            user.setCompany("");
        }
        for (String workComp : workerCompanies){
            for (String managComp : managerCompanies){
                if (workComp.equals(managComp)){
                    flag = true;
                }
            }
        }

        if (flag) {
            usersOnProjects = usersOnProjectsService.setWorkerOnProject(usersOnProjectsDtoPayload);
            usersOnProjectsDto.setProject(new ProjectDto(usersOnProjects.getUsersOnProjectsPK().getProject()));
            usersOnProjectsDto.setUser(new UserDto(usersOnProjects.getUsersOnProjectsPK().getUser()));
            usersOnProjectsDto.setPosition(usersOnProjects.getPosition().getName());
            usersOnProjectsDto.setRate(usersOnProjects.getRate());
            usersOnProjectsDto.setBase_salary(usersOnProjects.getBase_salary());

            userService.updateUserProject(user, usersOnProjects.getUsersOnProjectsPK().getProject().getName());
            userService.updateUserCompany(user, usersOnProjects.getUsersOnProjectsPK().getProject().getCompany().getName());

        } else{
            usersOnProjectsDto.setProject(new ProjectDto());
            usersOnProjectsDto.setUser(new UserDto());
            usersOnProjectsDto.setPosition("Нельзя поставить на проект работника чужой организации");
            usersOnProjectsDto.setRate(-1);
            usersOnProjectsDto.setBase_salary(0);
        }

        return ResponseEntity.ok(usersOnProjectsDto);
    }
}

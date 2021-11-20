package ru.mirea.kaleki.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.mirea.kaleki.dto.*;
import ru.mirea.kaleki.entitys.PKs.UsersOnProjectsPK;
import ru.mirea.kaleki.entitys.Project;
import ru.mirea.kaleki.entitys.User;
import ru.mirea.kaleki.entitys.UsersOnProjects;
import ru.mirea.kaleki.exceptions.MyNotFoundException;
import ru.mirea.kaleki.services.ProjectService;
import ru.mirea.kaleki.services.UserService;
import ru.mirea.kaleki.services.UsersOnProjectsService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/api/")
public class MyController {

    @Autowired
    protected ProjectService projectService;

    @Autowired
    protected UserService userService;

    @Autowired
    protected UsersOnProjectsService usersOnProjectsService;

    @GetMapping("/my_projects")
    public ResponseEntity<List<ProjectDto>> getMyProjects(){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserName = authentication.getName();
        User currentUser = userService.findByEmail(currentUserName).orElseThrow(
                () -> {throw new MyNotFoundException("User not found");}
        );

        List<String> projects = Arrays.asList(currentUser.getProjects().split("#"));

        List<ProjectDto> projectDtos = new ArrayList<>();
        for(String project : projects){
            if (projectService.findByName(project).isPresent()){
                Project temp = projectService.findByName(project).get();
                projectDtos.add(new ProjectDto(temp.getId(), temp.getName(), new CompanyDto(temp.getCompany()), temp.getStart_date(), temp.getStatus()));
            }
        }
        return ResponseEntity.ok(projectDtos);
    }

    @GetMapping("/my_projects/{id}")
    public ResponseEntity<ProjectDtoExtendedForUser> getMyProjectById(@PathVariable long id){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserName = authentication.getName();
        User currentUser = userService.findByEmail(currentUserName).orElseThrow(
                () -> {throw new MyNotFoundException("User not found");}
        );

        ProjectDtoExtendedForUser projectDtoExtendedForUser = new ProjectDtoExtendedForUser();

        Project project = projectService.findById(id).get();

        UsersOnProjects usersOnProjects = usersOnProjectsService.findByUsersOnProjectsPK(new UsersOnProjectsPK(currentUser, project));

        projectDtoExtendedForUser.setId(project.getId());
        projectDtoExtendedForUser.setName(project.getName());
        projectDtoExtendedForUser.setCompany_name(project.getCompany().getName());
        projectDtoExtendedForUser.setCEO_username(project.getCompany().getCEO().getUsername());
        projectDtoExtendedForUser.setCEO_email(project.getCompany().getCEO().getEmail());
        projectDtoExtendedForUser.setStart_date(project.getStart_date());
        projectDtoExtendedForUser.setStatus(project.getStatus());
        projectDtoExtendedForUser.setPosition(usersOnProjects.getPosition().getName());
        projectDtoExtendedForUser.setBase_salary(usersOnProjects.getBase_salary());
        projectDtoExtendedForUser.setRate(usersOnProjects.getRate());
        projectDtoExtendedForUser.setWeek_work_time(usersOnProjects.getWeek_work_time());

        return ResponseEntity.ok(projectDtoExtendedForUser);
    }

    @PutMapping("/my_projects/{id}/write_off")
    public ResponseEntity<ProjectDtoExtendedForUser> writeOff(@PathVariable long id, @RequestBody WriteOffDto writeOffDto){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserName = authentication.getName();
        User currentUser = userService.findByEmail(currentUserName).orElseThrow(
                () -> {throw new MyNotFoundException("User not found");}
        );

        ProjectDtoExtendedForUser projectDtoExtendedForUser = new ProjectDtoExtendedForUser();

        Project project = projectService.findById(id).get();

        if (!project.getStatus().equals("ACTIVE")){
            projectDtoExtendedForUser.setName("Проект неактивен, часы списать нельзя");
            projectDtoExtendedForUser.setId(-1);
            return ResponseEntity.ok(projectDtoExtendedForUser);
        }

        UsersOnProjects usersOnProjects = usersOnProjectsService.findByUsersOnProjectsPK(new UsersOnProjectsPK(currentUser, project));

        usersOnProjects.setWeek_work_time(usersOnProjects.getWeek_work_time() + writeOffDto.getHours());

        usersOnProjects = usersOnProjectsService.updateInfo(usersOnProjects);

        projectDtoExtendedForUser.setId(project.getId());
        projectDtoExtendedForUser.setName(project.getName());
        projectDtoExtendedForUser.setCompany_name(project.getCompany().getName());
        projectDtoExtendedForUser.setCEO_username(project.getCompany().getCEO().getUsername());
        projectDtoExtendedForUser.setCEO_email(project.getCompany().getCEO().getEmail());
        projectDtoExtendedForUser.setStart_date(project.getStart_date());
        projectDtoExtendedForUser.setStatus(project.getStatus());
        projectDtoExtendedForUser.setPosition(usersOnProjects.getPosition().getName());
        projectDtoExtendedForUser.setBase_salary(usersOnProjects.getBase_salary());
        projectDtoExtendedForUser.setRate(usersOnProjects.getRate());
        projectDtoExtendedForUser.setWeek_work_time(usersOnProjects.getWeek_work_time());

        return ResponseEntity.ok(projectDtoExtendedForUser);
    }
}

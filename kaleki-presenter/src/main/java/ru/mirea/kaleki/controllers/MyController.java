package ru.mirea.kaleki.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.mirea.kaleki.dto.*;
import ru.mirea.kaleki.entitys.Company;
import ru.mirea.kaleki.entitys.PKs.UsersOnProjectsPK;
import ru.mirea.kaleki.entitys.Project;
import ru.mirea.kaleki.entitys.User;
import ru.mirea.kaleki.entitys.UsersOnProjects;
import ru.mirea.kaleki.exceptions.MyNotFoundException;
import ru.mirea.kaleki.repositories.ProjectRepository;
import ru.mirea.kaleki.repositories.UsersOnProjectsRepository;
import ru.mirea.kaleki.security.dto.AuthenticationRequestDto;
import ru.mirea.kaleki.security.dto.UserDto;
import ru.mirea.kaleki.services.ProjectService;
import ru.mirea.kaleki.services.UserService;
import ru.mirea.kaleki.services.UsersOnProjectsService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

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
    public ResponseEntity<ProjectDtoExtended> getMyProjectById(@PathVariable long id){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserName = authentication.getName();
        User currentUser = userService.findByEmail(currentUserName).orElseThrow(
                () -> {throw new MyNotFoundException("User not found");}
        );

        ProjectDtoExtended projectDtoExtended = new ProjectDtoExtended();

        Project project = projectService.findById(id).get();

        UsersOnProjects usersOnProjects = usersOnProjectsService.findByUsersOnProjectsPK(new UsersOnProjectsPK(currentUser, project));

        projectDtoExtended.setId(project.getId());
        projectDtoExtended.setName(project.getName());
        projectDtoExtended.setCompany_name(project.getCompany().getName());
        projectDtoExtended.setCEO_username(project.getCompany().getCEO().getUsername());
        projectDtoExtended.setCEO_email(project.getCompany().getCEO().getEmail());
        projectDtoExtended.setStart_date(project.getStart_date());
        projectDtoExtended.setStatus(project.getStatus());
        projectDtoExtended.setPosition(usersOnProjects.getPosition().getName());
        projectDtoExtended.setBase_salary(usersOnProjects.getBase_salary());
        projectDtoExtended.setRate(usersOnProjects.getRate());
        projectDtoExtended.setWeek_work_time(usersOnProjects.getWeek_work_time());

        return ResponseEntity.ok(projectDtoExtended);
    }

    @PutMapping("/my_projects/{id}/write_off")
    public ResponseEntity<ProjectDtoExtended> writeOff(@PathVariable long id, @RequestBody WriteOffDto writeOffDto){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserName = authentication.getName();
        User currentUser = userService.findByEmail(currentUserName).orElseThrow(
                () -> {throw new MyNotFoundException("User not found");}
        );

        ProjectDtoExtended projectDtoExtended = new ProjectDtoExtended();

        Project project = projectService.findById(id).get();

        if (!project.getStatus().equals("ACTIVE")){
            projectDtoExtended.setName("Проект неактивен, часы списать нельзя");
            projectDtoExtended.setId(-1);
            return ResponseEntity.ok(projectDtoExtended);
        }

        UsersOnProjects usersOnProjects = usersOnProjectsService.findByUsersOnProjectsPK(new UsersOnProjectsPK(currentUser, project));

        usersOnProjects.setWeek_work_time(usersOnProjects.getWeek_work_time() + writeOffDto.getHours());

        usersOnProjects = usersOnProjectsService.updateInfo(usersOnProjects);

        projectDtoExtended.setId(project.getId());
        projectDtoExtended.setName(project.getName());
        projectDtoExtended.setCompany_name(project.getCompany().getName());
        projectDtoExtended.setCEO_username(project.getCompany().getCEO().getUsername());
        projectDtoExtended.setCEO_email(project.getCompany().getCEO().getEmail());
        projectDtoExtended.setStart_date(project.getStart_date());
        projectDtoExtended.setStatus(project.getStatus());
        projectDtoExtended.setPosition(usersOnProjects.getPosition().getName());
        projectDtoExtended.setBase_salary(usersOnProjects.getBase_salary());
        projectDtoExtended.setRate(usersOnProjects.getRate());
        projectDtoExtended.setWeek_work_time(usersOnProjects.getWeek_work_time());

        return ResponseEntity.ok(projectDtoExtended);
    }
}

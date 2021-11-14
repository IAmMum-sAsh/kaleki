package ru.mirea.kaleki.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.mirea.kaleki.dto.CompanyDto;
import ru.mirea.kaleki.dto.ProjectDto;
import ru.mirea.kaleki.entitys.Company;
import ru.mirea.kaleki.entitys.Project;
import ru.mirea.kaleki.entitys.User;
import ru.mirea.kaleki.exceptions.MyNotFoundException;
import ru.mirea.kaleki.repositories.ProjectRepository;
import ru.mirea.kaleki.repositories.UsersOnProjectsRepository;
import ru.mirea.kaleki.security.dto.UserDto;
import ru.mirea.kaleki.services.ProjectService;
import ru.mirea.kaleki.services.UserService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/api/")
public class MyController {

    @Autowired
    ProjectService projectService;

    @Autowired
    UserService userService;

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
            Project temp = projectService.findByName(project).get();
            projectDtos.add(new ProjectDto(temp.getId(), temp.getName(), new CompanyDto(temp.getCompany()), temp.getStart_date(), temp.getStatus()));
        }
        return ResponseEntity.ok(projectDtos);
    }
}

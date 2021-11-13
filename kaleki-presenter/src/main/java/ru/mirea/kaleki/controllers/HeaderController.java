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
import ru.mirea.kaleki.security.dto.UserDto;
import ru.mirea.kaleki.services.CompanyService;
import ru.mirea.kaleki.services.ProjectService;
import ru.mirea.kaleki.services.UserService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/api/")
public class HeaderController {
    @Autowired
    protected CompanyService companyService;

    @Autowired
    protected ProjectService projectService;

    @Autowired
    protected UserService userService;

    @GetMapping("/companies")
    public ResponseEntity<List<CompanyDto>> getCompanies(){
        List<CompanyDto> companyDtos = new ArrayList<>();
        List<Company> companies = companyService.getCompanies();

        for(Company company : companies){
            companyDtos.add(new CompanyDto(company.getId(), company.getName(), company.getAddress(), new UserDto(company.getCEO())));
        }
        return ResponseEntity.ok(companyDtos);
    }

    @GetMapping("/projects")
    public ResponseEntity<List<ProjectDto>> getProjects(){
        List<ProjectDto> projectDtos = new ArrayList<>();
        List<Project> projects = projectService.getProjects();

        for(Project project : projects){
            projectDtos.add(new ProjectDto(project.getId(), project.getName(), new CompanyDto(project.getCompany()), project.getStart_date(), project.getStatus()));
        }
        return ResponseEntity.ok(projectDtos);
    }

    @GetMapping("/workers")
    public ResponseEntity<List<UserDto>> getWorkers(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserName = authentication.getName();
        User currentUser = userService.findByEmail(currentUserName).orElseThrow(
                () -> {throw new MyNotFoundException("User not found");}
        );

        String company = currentUser.getCompany();

        List<Optional<User>> list = userService.findByCompanies(company);

        List<UserDto> userDtos = new ArrayList<>();

        for(Optional<User> user : list){
            userDtos.add(new UserDto(user.get()));
        }
        return ResponseEntity.ok(userDtos);
    }
}

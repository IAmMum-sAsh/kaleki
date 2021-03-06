package ru.mirea.kaleki.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.mirea.kaleki.dto.CompanyDto;
import ru.mirea.kaleki.dto.CompanyDtoExtended;
import ru.mirea.kaleki.dto.ProjectDto;
import ru.mirea.kaleki.dto.ProjectDtoExtended;
import ru.mirea.kaleki.entitys.Company;
import ru.mirea.kaleki.entitys.Project;
import ru.mirea.kaleki.entitys.User;
import ru.mirea.kaleki.exceptions.MyNotFoundException;
import ru.mirea.kaleki.security.dto.UserDto;
import ru.mirea.kaleki.services.CompanyService;
import ru.mirea.kaleki.services.ProjectService;
import ru.mirea.kaleki.services.UserService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * The type Header controller.
 */
@Controller
@RequestMapping("/api/")
public class HeaderController {
    /**
     * The Company service.
     */
    @Autowired
    protected CompanyService companyService;

    /**
     * The Project service.
     */
    @Autowired
    protected ProjectService projectService;

    /**
     * The User service.
     */
    @Autowired
    protected UserService userService;

    /**
     * Get companies response entity.
     *
     * @return the response entity
     */
    @GetMapping("/companies")
    public ResponseEntity<List<CompanyDto>> getCompanies(){
        List<CompanyDto> companyDtos = new ArrayList<>();
        List<Company> companies = companyService.getCompanies();

        for(Company company : companies){
            companyDtos.add(new CompanyDto(company.getId(), company.getName(), company.getAddress(), new UserDto(company.getCEO())));
        }
        return ResponseEntity.ok(companyDtos);
    }

    /**
     * Get company by id response entity.
     *
     * @param id the id
     * @return the response entity
     */
    @GetMapping("/companies/{id}")
    public ResponseEntity<CompanyDtoExtended> getCompanyById(@PathVariable long id){
        return ResponseEntity.ok(companyService.companyInfo(id));
    }

    /**
     * Get projects response entity.
     *
     * @return the response entity
     */
    @GetMapping("/projects")
    public ResponseEntity<List<ProjectDto>> getProjects(){
        List<ProjectDto> projectDtos = new ArrayList<>();
        List<Project> projects = projectService.getProjects();

        for(Project project : projects){
            projectDtos.add(new ProjectDto(project.getId(), project.getName(), new CompanyDto(project.getCompany()), project.getStart_date(), project.getStatus()));
        }
        return ResponseEntity.ok(projectDtos);
    }

    /**
     * Get project by id response entity.
     *
     * @param id the id
     * @return the response entity
     */
    @GetMapping("/projects/{id}")
    public ResponseEntity<ProjectDtoExtended> getProjectById(@PathVariable long id){
        return ResponseEntity.ok(projectService.projectInfo(id));
    }

    /**
     * Get workers response entity.
     *
     * @return the response entity
     */
    @GetMapping("/workers")
    public ResponseEntity<List<UserDto>> getWorkers(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserName = authentication.getName();
        User currentUser = userService.findByEmail(currentUserName).orElseThrow(
                () -> {throw new MyNotFoundException("User not found");}
        );

        List<String> companies = Arrays.asList(currentUser.getCompany().split("#"));
//        for(String company : companies){
//            List<Optional<User>> temp = userService.findByCompanies(company);
//            for(Optional<User> user : temp){
//                list.add(user.get());
//            }
//        }

        List<User> allUsers = userService.findAll();

        List<UserDto> userDtos = new ArrayList<>();
        for(User user : allUsers){
            List<String> temp = Arrays.asList(user.getCompany().split("#"));
            for (String tempComp : temp){
                if(companies.contains(tempComp)&&(!userDtos.contains(new UserDto(user)))){
                    userDtos.add(new UserDto(user));
                }
            }
        }
        return ResponseEntity.ok(userDtos);
    }
}

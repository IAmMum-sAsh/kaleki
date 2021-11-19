package ru.mirea.kaleki.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.mirea.kaleki.dto.*;
import ru.mirea.kaleki.entitys.Company;
import ru.mirea.kaleki.entitys.User;
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
public class AdminController {
    @Autowired
    protected UserService userService;

    @Autowired
    protected CompanyService companyService;

    @Autowired
    protected ProjectService projectService;

    @Autowired
    protected UsersOnProjectsService usersOnProjectsService;

    @PutMapping("/give_manage")
    public ResponseEntity<UserDto> getGiveManage(@RequestBody GiveManageDto giveManageDto){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserName = authentication.getName();
        User currentUser = userService.findByEmail(currentUserName).orElseThrow(
                () -> {throw new MyNotFoundException("User not found");}
        );

        User user = userService.findById(giveManageDto.getId()).get();

        if (user.getCompany().equals(currentUser.getCompany())) {
            userService.giveManage(user);
            return ResponseEntity.ok(new UserDto(user));
        } else{
            return ResponseEntity.ok(new UserDto(-1, "Невозможно присвоить статус сотруднику сторонней компании", ""));
        }
    }

    @PutMapping("/give_admin")
    public ResponseEntity<UserDto> getGiveAdmin(@RequestBody GiveManageDto giveManageDto){
        User user = userService.findById(giveManageDto.getId()).get();

        userService.giveAdmin(user);
        return ResponseEntity.ok(new UserDto(user));
    }

    @PostMapping("/create_company")
    public ResponseEntity<CompanyDto> createCompany(@RequestBody CompanyDtoPayload companyDtoPayload){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserName = authentication.getName();
        User currentUser = userService.findByEmail(currentUserName).orElseThrow(
                () -> {throw new MyNotFoundException("User not found");}
        );

        userService.updateUserCompany(currentUser, companyDtoPayload.getName());

        return ResponseEntity.ok(new CompanyDto(companyService.createCompany(companyDtoPayload, currentUser)));
    }

    @PostMapping ("/create_project")
    public ResponseEntity<ProjectDto> createProject(@RequestBody ProjectDtoPayload projectDtoPayload){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserName = authentication.getName();
        User currentUser = userService.findByEmail(currentUserName).orElseThrow(
                () -> {throw new MyNotFoundException("User not found");}
        );

        List<String> companies = Arrays.asList(currentUser.getCompany().split("#"));

        ProjectDto projectDto;

        if (companies.contains(companyService.getCompanyById(projectDtoPayload.getCompany()).getName())) {
            projectDto = new ProjectDto(projectService.createProject(projectDtoPayload));
        } else{
            projectDto = new ProjectDto(-1, "Невозможно создать проект в подразделении другой компании", new CompanyDto(), new Date(0), "");
        }

        UsersOnProjectsDtoPayload usersOnProjectsDtoPayload = new UsersOnProjectsDtoPayload();
        usersOnProjectsDtoPayload.setUser_id(currentUser.getId());
        usersOnProjectsDtoPayload.setProject_id(projectService.findByName(projectDtoPayload.getName()).get().getId());
        usersOnProjectsDtoPayload.setPosition_id(1);
        usersOnProjectsDtoPayload.setRate(1.0);
        usersOnProjectsDtoPayload.setBase_salary(projectDtoPayload.getSalary());
        usersOnProjectsDtoPayload.setWeek_work_time(0);

        userService.updateUserProject(currentUser, projectDtoPayload.getName());
        usersOnProjectsService.setWorkerOnProject(usersOnProjectsDtoPayload);

        return ResponseEntity.ok(projectDto);
    }

    @PutMapping("/change_project_status")
    public ResponseEntity<ProjectDto> changeProjectStatus(@RequestBody ChangeProjectStatusDtoPayload payload){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserName = authentication.getName();
        User currentUser = userService.findByEmail(currentUserName).orElseThrow(
                () -> {throw new MyNotFoundException("User not found");}
        );

        List<String> companies = Arrays.asList(currentUser.getCompany().split("#"));

        if (companies.contains(projectService.findById(payload.getProject_id()).get().getCompany().getName())) {
            return ResponseEntity.ok(new ProjectDto(projectService.updateProjectStatus(payload)));
        } else{
            return ResponseEntity.ok(new ProjectDto(-1, "Невозможно изменить проект в подразделении другой компании", new CompanyDto(), new Date(0), ""));
        }
    }

    @PutMapping("/accept_into_company")
    public ResponseEntity<UserDto> acceptIntoCompany(@RequestBody AcceptDto acceptDto){
        User user = userService.findById(acceptDto.getUser_id()).get();
        Company company = companyService.getCompanyById(acceptDto.getCompany_id());

        if (user.getCompany().equals("Base company")){
            user.setCompany("");
        }

        userService.updateUserCompany(user, company.getName());

        return ResponseEntity.ok(new UserDto(user));
    }
}

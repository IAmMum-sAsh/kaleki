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
    protected CompanyService companyService;

    @Autowired
    protected ProjectService projectService;

    @Autowired
    protected UsersOnProjectsService usersOnProjectsService;

    @PutMapping("/give_manage")
    public ResponseEntity<UserDto> getMyProjects(@RequestBody GiveManageDto giveManageDto){
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

    @PostMapping ("/create_company")
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

        if (companies.contains(companyService.getCompanyById(projectDtoPayload.getCompany()).getName())) {
            return ResponseEntity.ok(new ProjectDto(projectService.createProject(projectDtoPayload)));
        } else{
            return ResponseEntity.ok(new ProjectDto(-1, "Невозможно создать проект в подразделении другой компании", new CompanyDto(), new Date(0), ""));
        }
    }

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
}

package ru.mirea.kaleki.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.mirea.kaleki.dto.CompanyDto;
import ru.mirea.kaleki.dto.RoleDto;
import ru.mirea.kaleki.entitys.Company;
import ru.mirea.kaleki.entitys.User;
import ru.mirea.kaleki.exceptions.MyNotFoundException;
import ru.mirea.kaleki.security.dto.UserDto;
import ru.mirea.kaleki.services.UserService;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/api/")
public class HelpComtroller {
    @Autowired
    protected UserService userService;

    @GetMapping("/get_my_role")
    public ResponseEntity<RoleDto> getCompanies(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserName = authentication.getName();
        User currentUser = userService.findByEmail(currentUserName).orElseThrow(
                () -> {throw new MyNotFoundException("User not found");}
        );

        return ResponseEntity.ok(new RoleDto(userService.findById(currentUser.getId()).get().getRole()));
    }
}

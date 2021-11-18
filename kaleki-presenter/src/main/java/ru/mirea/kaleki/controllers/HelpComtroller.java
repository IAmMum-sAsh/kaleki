package ru.mirea.kaleki.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.mirea.kaleki.dto.NameDto;
import ru.mirea.kaleki.dto.PositionDto;
import ru.mirea.kaleki.dto.RoleDto;
import ru.mirea.kaleki.entitys.Position;
import ru.mirea.kaleki.entitys.User;
import ru.mirea.kaleki.exceptions.MyNotFoundException;
import ru.mirea.kaleki.services.PositionService;
import ru.mirea.kaleki.services.UserService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/api/")
public class HelpComtroller {
    @Autowired
    protected UserService userService;

    @Autowired
    protected PositionService positionService;

    @GetMapping("/get_my_role")
    public ResponseEntity<RoleDto> getCompanies(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserName = authentication.getName();
        User currentUser = userService.findByEmail(currentUserName).orElseThrow(
                () -> {throw new MyNotFoundException("User not found");}
        );

        return ResponseEntity.ok(new RoleDto(userService.findById(currentUser.getId()).get().getRole()));
    }

    @GetMapping("/get_positions")
    public ResponseEntity<List<PositionDto>> getPositions(){
        List<PositionDto> positionDtos = new ArrayList<>();

        List<Position> positions = positionService.findAll();

        for (Position position : positions){
            positionDtos.add(new PositionDto(position.getId(), position.getName()));
        }

        return ResponseEntity.ok(positionDtos);
    }

    @GetMapping("/get_name")
    public ResponseEntity<NameDto> getName(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserName = authentication.getName();
        User currentUser = userService.findByEmail(currentUserName).orElseThrow(
                () -> {throw new MyNotFoundException("User not found");}
        );

        return ResponseEntity.ok(new NameDto(userService.findById(currentUser.getId()).get().getUsername()));
    }
}

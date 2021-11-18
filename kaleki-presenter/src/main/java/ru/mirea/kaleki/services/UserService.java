package ru.mirea.kaleki.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.mirea.kaleki.entitys.Company;
import ru.mirea.kaleki.entitys.User;
import ru.mirea.kaleki.repositories.CompanyRepository;
import ru.mirea.kaleki.repositories.UserRepository;
import ru.mirea.kaleki.security.payload.UserDtoPayload;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    public BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    protected CompanyRepository companyRepository;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder;
    }

    public Optional<User> findById(Long userId) {
//        if (optionalUser.isPresent())
//            log.info("User " + optionalUser.get().toString() + "found by id " + userId);
//        else log.info("User with id '" + userId + "' not found.");
        return userRepository.findById(userId);
    }
    public Optional<User> findByEmail(String email) {
//        if (optionalUser.isPresent())
//            log.info("User " + optionalUser.get().toString() + "found by email " + email);
//        else log.info("User with email '" + email + "' not found.");
        return userRepository.findByEmail(email);
    }
    public List<Optional<User>> findByCompanies(String company){
//        if (optionalUser.size() > 0)
//            log.info("Users " + optionalUser.toString() + "found by company " + company);
//        else log.info("Users with company '" + company + "' not found.");
        return userRepository.findByCompany(company);
    }

    public List<User> findAll() {return userRepository.findAll();}

    public User registerNewUser(UserDtoPayload userDtoPayload, String role) {
        User user = new User();

        user.setEmail(userDtoPayload.getEmail());
        user.setUsername(userDtoPayload.getUsername());
        user.setRole(role);
        user.setCompany("Base company");
        user.setProjects("");

        String encodedPassword = bCryptPasswordEncoder.encode(userDtoPayload.getPassword());
        user.setPassword(encodedPassword);

        userRepository.save(user);

        return user;
    }

    public User giveManage(User user){
        user.setRole("ROLE_MANAGER");

        return userRepository.save(user);
    }

    public User updateUserCompany(User user, String newCompany){
        boolean flag = false;
        if (user.getCompany().equals("")) { flag=true; }
        List<String> companies = Arrays.asList(user.getCompany().split("#"));
        if (!companies.contains(newCompany)){
            user.setCompany(user.getCompany()+"#"+newCompany);
        }
        if (flag) { user.setCompany(user.getCompany().substring(1)); }
        return userRepository.save(user);
    }

    public User updateUserProject(User user, String newProject){
        boolean flag = false;
        if (user.getProjects().equals("")) { flag=true; }
        List<String> companies = Arrays.asList(user.getCompany().split("#"));
        if (!companies.contains(newProject)){
            user.setProjects(user.getProjects()+"#"+newProject);
        }
        if (flag) { user.setProjects(user.getProjects().substring(1)); }
        return userRepository.save(user);
    }

}
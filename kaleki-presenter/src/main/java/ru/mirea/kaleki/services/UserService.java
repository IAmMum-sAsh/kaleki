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

/**
 * The type User service.
 */
@Service
@Slf4j
public class UserService {
    @Autowired
    private UserRepository userRepository;

    /**
     * The B crypt password encoder.
     */
    @Autowired
    public BCryptPasswordEncoder bCryptPasswordEncoder;

    /**
     * The Company repository.
     */
    @Autowired
    protected CompanyRepository companyRepository;

    /**
     * Password encoder b crypt password encoder.
     *
     * @return the b crypt password encoder
     */
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder;
    }

    /**
     * Find by id optional.
     *
     * @param userId the user id
     * @return the optional
     */
    public Optional<User> findById(Long userId) {
        return userRepository.findById(userId);
    }

    /**
     * Find by email optional.
     *
     * @param email the email
     * @return the optional
     */
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    /**
     * Find all list.
     *
     * @return the list
     */
    public List<User> findAll() {return userRepository.findAll();}

    /**
     * Register new user user.
     *
     * @param userDtoPayload the user dto payload
     * @param role           the role
     * @return the user
     */
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

    /**
     * Give manage user.
     *
     * @param user the user
     * @return the user
     */
    public User giveManage(User user){
        user.setRole("ROLE_MANAGER");

        return userRepository.save(user);
    }

    /**
     * Give admin user.
     *
     * @param user the user
     * @return the user
     */
    public User giveAdmin(User user){
        user.setRole("ROLE_ADMIN");

        return userRepository.save(user);
    }

    /**
     * Update user company user.
     *
     * @param user       the user
     * @param newCompany the new company
     * @return the user
     */
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

    /**
     * Update user project user.
     *
     * @param user       the user
     * @param newProject the new project
     * @return the user
     */
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
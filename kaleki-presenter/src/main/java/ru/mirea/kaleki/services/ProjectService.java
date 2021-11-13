package ru.mirea.kaleki.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.mirea.kaleki.entitys.Project;
import ru.mirea.kaleki.entitys.User;
import ru.mirea.kaleki.repositories.ProjectRepository;
import ru.mirea.kaleki.repositories.UsersOnProjectsRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ProjectService {
    @Autowired
    protected ProjectRepository projectRepository;

    @Autowired
    protected UsersOnProjectsRepository usersOnProjectsRepository;

    public List<Project> getProjects(){
        return projectRepository.findAll();
    }

//    public List<Optional<Project>> getByUser(User user){
//        return usersOnProjectsRepository.findByUser(user);
//    }
}

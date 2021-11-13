package ru.mirea.kaleki.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.mirea.kaleki.entitys.Company;
import ru.mirea.kaleki.entitys.Project;
import ru.mirea.kaleki.entitys.User;
import ru.mirea.kaleki.repositories.CompanyRepository;
import ru.mirea.kaleki.repositories.ProjectRepository;
import ru.mirea.kaleki.repositories.UsersOnProjectsRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CompanyService {
    @Autowired
    protected CompanyRepository companyRepository;

    @Autowired
    protected ProjectRepository projectRepository;

    @Autowired
    protected UsersOnProjectsRepository usersOnProjectsRepository;

    public List<Company> getCompanies(){
        return companyRepository.findAll();
    }

//    public List<Optional<User>> findUserByCompany(String companyName) {
//        List<Optional<User>> listU = new ArrayList<>();
//
//        Optional<Company> companyOptional = companyRepository.findByName(companyName);
//        Company company = companyOptional.get();
//
//        List<Optional<Project>> listP = projectRepository.findAllByCompany(company);
//
//        for(Optional<Project> project : listP){
//            List<Optional<User>> lou = (usersOnProjectsRepository.findByProject(project.get()));
//            for (Optional<User> optionalUser : lou){
//                listU.add(optionalUser);
//            }
//        }
//        return listU;
//    }
}

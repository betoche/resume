package org.als.resume.services;

import org.als.resume.entities.Users;
import org.als.resume.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository repository;

    public Users createUser(Users user ) {
        repository.save(user);repository.findAll();

        return user;
    }

    public Users findUserById(long id ){
        return repository.findById(id);
    }

}

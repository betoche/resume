package org.als.resume.repositories;

import org.als.resume.entities.IdText;
import org.als.resume.entities.Phone;
import org.als.resume.entities.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;

public class RepositoryFactory {
    @Autowired
    private UserRepository userRepo;
    @Autowired
    private PhoneRepository phoneRepo;

    private Object entity;

    public RepositoryFactory(Object entity) {
        this.entity = entity;
    }

    public Iterable<IdText> findAll() {
        return getRepository().findAll();
    }

    public CrudRepository getRepository() {
        CrudRepository repo = null;
        if( this.entity instanceof Users){
            repo = userRepo;
        }
        if( this.entity instanceof Phone) {
            repo = phoneRepo;
        }

        return repo;
    }

}

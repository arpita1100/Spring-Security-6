package com.app.repository;

import com.app.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends CrudRepository<User,String> {
    User findByUsername(String username);
}

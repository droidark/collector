package com.comixtorm.collector.domain.repository;

import com.comixtorm.collector.domain.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
    //User findByUsername(String username);
    Optional<User> findByUsername(String username);
}

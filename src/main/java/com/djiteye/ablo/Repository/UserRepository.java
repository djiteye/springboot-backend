package com.djiteye.ablo.Repository;

import java.util.Optional;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.stereotype.Repository;

import com.djiteye.ablo.Entity.User;

@Repository
@Configuration
@EnableMongoRepositories(basePackages = "com.djiteye.ablo.Repository.UserRepository")
public interface UserRepository extends MongoRepository<User, String> {

	Optional<User> findByUsername(String username);

	User findById(Long id);

	boolean existsByUsername(String username);

	boolean existsByEmail(String email);
	

}

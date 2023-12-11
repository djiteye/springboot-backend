package com.djiteye.ablo.Repository;

import java.util.Optional;

import org.springframework.context.annotation.Configuration;
//import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.stereotype.Repository;

import com.djiteye.ablo.Entity.ERole;
import com.djiteye.ablo.Entity.Role;

@Repository
@Configuration
@EnableMongoRepositories(basePackages = "com.devback.uc.Repository.RoleRepos")
public interface RoleRepos extends MongoRepository<Role, String> {
	Optional<Role> findByName(ERole name);


	//Optional<Role> add(ERole roleUser);

	//void save(ERole roleUser);

}

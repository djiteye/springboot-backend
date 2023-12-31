package com.djiteye.ablo.Repository;

import java.util.Optional;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.stereotype.Repository;

import com.djiteye.ablo.Entity.Formation;

@Repository
@Configuration
@EnableMongoRepositories(basePackages = "com.djiteye.ablo.Repository.FormationRepository")
public interface FormationRepository extends MongoRepository<Formation, String> {

	Optional<Formation> findById(String id);

}

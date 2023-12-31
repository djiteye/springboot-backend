package com.djiteye.ablo.Repository;

 import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.stereotype.Repository;

import com.djiteye.ablo.Entity.ProjetRealiser;

@Repository
@Configuration
@EnableMongoRepositories(basePackages = "com.djiteye.ablo.Repository.ProjetRealiserRepository")
public interface ProjetRealiserRepository extends MongoRepository<ProjetRealiser, String>{

}

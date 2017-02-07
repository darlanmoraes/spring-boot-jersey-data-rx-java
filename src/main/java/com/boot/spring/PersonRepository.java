package com.boot.spring;

import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by darlan on 25/01/17.
 */
public interface PersonRepository extends MongoRepository<Person, String> {}

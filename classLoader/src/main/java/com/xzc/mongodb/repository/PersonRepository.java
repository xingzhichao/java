package com.xzc.mongodb.repository;

import com.xzc.mongodb.entity.Person;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface PersonRepository extends MongoRepository<Person,String> {
    List<Person> findByLocationNear(Point location, Distance distance);
}

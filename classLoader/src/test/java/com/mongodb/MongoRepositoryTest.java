package com.mongodb;

import com.xzc.MongodbStarterApp;
import com.xzc.mongodb.entity.Person;
import com.xzc.mongodb.repository.PersonRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Metrics;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = MongodbStarterApp.class)
public class MongoRepositoryTest {
    @Autowired
    MongoTemplate mongoTemplate;
    @Autowired
    PersonRepository repository;

    @Test
    public void test() {
        //初始化打车人的经纬度点
        Point point = new Point(43.7, 48.8);
        //初始化老王牛肉面馆所在的经纬度点
        Point point1 = new Point(50.2, 60.8);
        //初始化老汉卤肉铺,假设老汉卤肉铺的位置跟下单人同一经纬度.
        Person person = new Person("老汉", 25, point);
        //初始化老王牛肉面
        Person person1 = new Person("老王", 18, point1);
        //将老汉卤肉铺当前的位置存入数据库里
        mongoTemplate.insert(person);
        //将老王牛肉面当前的位置存入数据库里
        mongoTemplate.insert(person1);
        //设置距离,查找3公里以内的所有商铺
        Distance distance = new Distance(3, Metrics.KILOMETERS);
        List<Person> personList = repository.findByLocationNear(point, distance);
        System.out.println(">>>" + personList);
    }
}

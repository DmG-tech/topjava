package ru.javawebinar.topjava.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.bridge.SLF4JBridgeHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;
import ru.javawebinar.topjava.MealTestData;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.util.List;

import static org.junit.Assert.*;
import static ru.javawebinar.topjava.MealTestData.*;

@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringRunner.class)
@Sql(scripts = "classpath:db/populateDBForTest.sql", config = @SqlConfig(encoding = "UTF-8"))
public class MealServiceTest {

    static {
        // Only for postgres driver logging
        // It uses java.util.logging and logged via jul-to-slf4j bridge
        SLF4JBridgeHandler.install();
    }

    @Autowired
    private MealService service;

    @Autowired
    private MealRepository repository;

    @Test
    public void get() {
        Meal meal = service.get(USER_FIRST_MEAL.getId(), USER_ID);
        assertMatch(meal, USER_FIRST_MEAL);
    }

    @Test
    public void getNotFound() {
        assertThrows(NotFoundException.class, () -> service.get(NOT_FOUND_ID, USER_ID));
    }

    @Test
    public void getAlienMeal() {
        assertThrows(NotFoundException.class, () -> service.get(USER_FIRST_MEAL.getId(), ADMIN_ID));
    }

    @Test
    public void delete() {
        service.delete(USER_FIRST_MEAL.getId(), USER_ID);
        assertNull(repository.get(USER_FIRST_MEAL.getId(), USER_ID));
    }

    @Test
    public void deleteNotFound() {
        assertThrows(NotFoundException.class, () -> service.delete(NOT_FOUND_ID, USER_ID));
    }

    @Test
    public void getBetweenInclusive() {
        List<Meal> meals = service.getBetweenInclusive(START_DATE, END_DATE, USER_ID);
        assertMatch(meals, USER_SECOND_MEAL);
    }

    @Test
    public void getAll() {
        List<Meal> meals = service.getAll(USER_ID);
        assertMatch(meals, USER_SECOND_MEAL, USER_FIRST_MEAL);
    }

    @Test
    public void update() {
        Meal updated = getUpdated();
        service.update(updated, USER_ID);
        assertMatch(service.get(updated.getId(), USER_ID), updated);
    }

    @Test
    public void create() {
        Meal meal = getNew();
        Meal created = service.create(meal, USER_ID);
        assertMatch(created, meal);
        assertMatch(service.get(meal.getId(), USER_ID), meal);
    }
}
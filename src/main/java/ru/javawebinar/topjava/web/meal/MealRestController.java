package ru.javawebinar.topjava.web.meal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealTo;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.web.SecurityUtil;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collection;

@Controller
public class MealRestController {
    protected final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private MealService service;

    public Meal save(Meal meal) {
        log.info("save {}", meal);
        return service.save(meal, getUserId());
    }

    public boolean delete(int id) {
        log.info("delete {}", id);
        return service.delete(id, getUserId());
    }

    public Meal get(int id) {
        log.info("get {}", id);
        return service.get(id, getUserId());
    }

    public Collection<MealTo> getAll(LocalDate startDate, LocalDate endDate, LocalTime startTime, LocalTime endTime) {
        log.info("getAll");
        return service.getAll(startDate, endDate, startTime, endTime, getUserId());
    }

    public Collection<MealTo> getAll() {
        log.info("getAll");
        return service.getAll(getUserId());
    }

    private Integer getUserId() {
        return SecurityUtil.authUserId();
    }
}
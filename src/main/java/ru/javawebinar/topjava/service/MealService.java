package ru.javawebinar.topjava.service;

import org.springframework.stereotype.Service;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealTo;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.MealsUtil;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;


@Service
public class MealService {

    private MealRepository repository;

    public MealService(MealRepository repository) {
        this.repository = repository;
    }

    public Meal save(Meal meal, Integer userId) {
        return repository.save(meal, userId);
    }

    public boolean delete(int id, Integer userId) {
        return  repository.delete(id, userId);
    }

    public Meal get(int id, Integer userId) {
        return repository.get(id, userId);
    }

    public Collection<MealTo> getAll(LocalDate startDate, LocalDate endDate, LocalTime startTime, LocalTime endTime, Integer userId) {
        Collection<MealTo> meals = MealsUtil.getTos(repository.getAll(userId), MealsUtil.DEFAULT_CALORIES_PER_DAY);

        if (startTime != null | endTime != null) {
            LocalTime st = startTime == null ? LocalTime.MIN : startTime;
            LocalTime et = endTime == null ? LocalTime.MAX : endTime;
            meals = MealsUtil.getFilteredTos(meals, st, et);
        }
        else if (startDate != null | endDate != null) {
            LocalDate sd = startDate == null ? LocalDate.MIN : startDate;
            LocalDate ed = endDate == null ? LocalDate.MAX : endDate;
            meals = MealsUtil.getFilteredTos(meals, sd, ed);
        }

        return meals;
    }

    public Collection<MealTo> getAll(Integer userId) {
        return MealsUtil.getTos(repository.getAll(userId), MealsUtil.DEFAULT_CALORIES_PER_DAY);
    }
}
package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.repository.MemoryMealRepository;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDateTime;

import static org.slf4j.LoggerFactory.getLogger;

public class MealServlet extends HttpServlet {
    private static final Logger log = getLogger(MealServlet.class);
    private MealRepository repository;

    public MealServlet() {
        super();
        this.repository = new MemoryMealRepository();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.debug("GET request to meals");

        String action = request.getParameter("action");

        if (action == null) {
            log.debug("get all");
            request.setAttribute("mealsList", MealsUtil.getAllWithExcess(repository.getAll(), 2000));
            request.getRequestDispatcher("meals.jsp").forward(request, response);
        } else if (action.equals("delete")) {
            int id = getId(request);
            log.debug("delete: " + id);
            repository.delete(id);
            request.setAttribute("mealsList", MealsUtil.getAllWithExcess(repository.getAll(), 2000));
            request.getRequestDispatcher("meals.jsp").forward(request, response);
        } else if (action.equals("update")) {
            int id = getId(request);
            log.debug("update: " + id);
            Meal meal = repository.get(id);
            request.setAttribute("meal", meal);
            request.getRequestDispatcher("editMeal.jsp").forward(request, response);
        } else if (action.equals("add")) {
            log.debug("create meal");
            request.setAttribute("meal", new Meal(LocalDateTime.now(), "New meal", 0));
            request.getRequestDispatcher("editMeal.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String date = req.getParameter("dateTime");
        String description = req.getParameter("description");
        String calories = req.getParameter("calories");
        String id = req.getParameter("id");

        Meal meal = new Meal(LocalDateTime.parse(date), description, Integer.parseInt(calories));
        if (id != null && !id.isEmpty()) {
            meal.setId(Integer.parseInt(id));
        }
        repository.save(meal);
        req.setAttribute("mealsList", MealsUtil.getAllWithExcess(repository.getAll(), 2000));
        req.getRequestDispatcher("meals.jsp").forward(req, resp);
    }

    private int getId(HttpServletRequest request) {
        return Integer.parseInt(request.getParameter("id"));
    }
}

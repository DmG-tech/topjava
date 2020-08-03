package ru.javawebinar.topjava.web.meal;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.to.MealTo;

import java.net.URI;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@RestController
@RequestMapping(value = MealRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class MealRestController extends AbstractMealController {

    static final String REST_URL = "/rest/meals";

    @GetMapping
    public List<MealTo> getAll() {
        return super.getAll();
    }

    @GetMapping("/{id}")
    public Meal get(@PathVariable int id) {
        return super.get(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        super.delete(id);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@RequestBody Meal meal, @PathVariable int id) {
        super.update(meal, id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Meal> createWithLocation(@RequestBody Meal meal) {
        Meal created = new Meal(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS), "", 1000);
        URI uriOfNewResponse = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResponse).body(created);
    }

    @PostMapping(value = "/filter", params = {"startDate", "endDate", "startTime", "endTime"})
    public List<MealTo> getBetween(@DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @RequestParam("startDate") LocalDate startDate,
                                   @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @RequestParam("endDate") LocalDate endDate,
                                   @DateTimeFormat(iso = DateTimeFormat.ISO.TIME) @RequestParam("startTime") LocalTime startTime,
                                   @DateTimeFormat(iso = DateTimeFormat.ISO.TIME) @RequestParam("endTime") LocalTime endTime) {
        return super.getBetween(startDate, startTime, endDate, endTime);
    }
}
package ru.javawebinar.topjava;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import static org.assertj.core.api.Assertions.assertThat;
import static ru.javawebinar.topjava.model.AbstractBaseEntity.START_SEQ;

public class MealTestData {

    public static final int NOT_FOUND_ID = 111;

    public static final int USER_ID = START_SEQ;
    public static final int ADMIN_ID = START_SEQ + 1;

    public static final LocalDate START_DATE = LocalDate.of(2020, 2, 21);
    public static final LocalDate END_DATE = LocalDate.of(2020,2,25);

    public static final Meal USER_FIRST_MEAL = new Meal(1, LocalDateTime.of(2020, 2, 20, 9, 05, 30), "завтрак", 500);
    public static final Meal USER_SECOND_MEAL = new Meal(2, LocalDateTime.of(2020, 2, 22, 13, 10, 25), "обед", 1000);
    public static final Meal ADMIN_FIRST_MEAL = new Meal(3, LocalDateTime.of(2020, 5, 20, 9, 20, 30), "завтрак", 800);

    public static Meal getNew() {
        return new Meal(null, LocalDateTime.of(2000, 2, 20, 2, 22, 22), "food", 222);
    }

    public static Meal getUpdated() {
        Meal updated = new Meal(USER_FIRST_MEAL);
        updated.setDescription("UpdatedDescription");
        updated.setCalories(5000);
        return updated;
    }

    public static void assertMatch(Meal actual, Meal expected) {
        assertThat(actual).isEqualTo(expected);
    }

    public static void assertMatch(Iterable<Meal> actual, Meal... expected) {
        assertMatch(actual, Arrays.asList(expected));
    }

    public static void assertMatch(Iterable<Meal> actual, Iterable<Meal> expected) {
        assertThat(actual).usingElementComparatorIgnoringFields("registered", "roles").isEqualTo(expected);
    }

}

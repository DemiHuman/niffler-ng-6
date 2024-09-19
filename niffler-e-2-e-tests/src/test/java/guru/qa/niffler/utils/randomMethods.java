package guru.qa.niffler.utils;

import com.github.javafaker.Faker;

import java.util.Date;

public class randomMethods {

    private static final Faker faker = new Faker();

    public static String getRandomUsername() {
        return faker.name().username();
    }

    public static String getRandomCategoryName() {
        return faker.commerce().productName() + faker.random().hex(3);
    }

    public static String getRandomPassword() {
        Long date = new Date().getTime();
        return faker.cat().name() + date.toString().substring(8);
    }
}

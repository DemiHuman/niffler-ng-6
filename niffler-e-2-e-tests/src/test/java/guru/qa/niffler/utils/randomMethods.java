package guru.qa.niffler.utils;

import com.github.javafaker.Faker;

import java.util.Date;

public class randomMethods {

    public static String getRandomUsername() {
        return new Faker().name().username();
    }

    public static String getRandomCategoryName() {
        return new Faker().funnyName().name();
    }

    public static String getRandomPassword() {
        Long date = new Date().getTime();
        return new Faker().cat().name() + date.toString().substring(8);
    }
}

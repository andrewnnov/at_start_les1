package ru.bellintegrator;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class FirstTest {

    @BeforeAll
    public static void beforeAll() {
        System.out.println("Before All");
    }

    @Test
    public void firstTest() {
        System.out.println("firstTest");
    }

    @Test
    public void secondTest() {
        System.out.println("secondTest");
    }

    @AfterAll
    public static void afterAll() {
        System.out.println("After All");
    }
}

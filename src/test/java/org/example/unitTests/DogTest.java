package org.example.unitTests;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DogTest {
    Dog dog = new Dog("Jack", 7);

    @Test
    void testGetName() {
        assertEquals("Jack", dog.getName());
    }

    @Test
    void testGetAge() {
        assertEquals(7, dog.getAge());
    }
}
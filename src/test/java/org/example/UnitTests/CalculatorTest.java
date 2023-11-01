package org.example.UnitTests;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {
    @Test
    void getSum() {
        assertEquals(7,3+4);
    }

    @Test
    void getDivide() {
        assertEquals(1, 3/3);
    }

    @Test
    void getMultiple() {assertEquals(30, 3*10);}

    @Test
    void getSubstraction(){assertEquals(7, 10-3);}
}

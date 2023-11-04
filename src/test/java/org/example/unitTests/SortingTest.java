package org.example.unitTests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class SortingTest {
    @Test
    @DisplayName("Array sorted")
    public void sortArray() {
        Sorting sortingTests = new Sorting();
        int[] array = {7, 10, 5, 11, 1, 8};
        sortingTests.sortingArray(array);
        Assertions.assertArrayEquals(new int[]{1, 5, 7, 8, 10, 11}, array);
    }
}
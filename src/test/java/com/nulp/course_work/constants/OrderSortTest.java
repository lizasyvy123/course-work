// File: src/test/java/com/nulp/course_work/constants/OrderSortTest.java

package com.nulp.course_work.constants;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class OrderSortTest {

    @Test
    public void testGetOrderSortTypeByIndexValid() {
        assertEquals(ordersort.DESC, ordersort.getordersortype(1));
        assertEquals(ordersort.ASC, ordersort.getordersortype(2));
    }

    @Test
    public void testGetOrderSortTypeByIndexInvalid() {
        assertNull(ordersort.getordersortype(3));
    }

    @Test
    public void testGetOrderSortTypeByNameValid() {
        assertEquals(ordersort.DESC, ordersort.getordersortype("In descending order"));
        assertEquals(ordersort.ASC, ordersort.getordersortype("In ascending order"));
    }

    @Test
    public void testGetOrderSortTypeByNameInvalid() {
        assertNull(ordersort.getordersortype("NonExistingType"));
    }
}

// File: src/test/java/com/nulp/course_work/constants/AccessoryTypeTest.java

package com.nulp.course_work.constants;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AccessoryTypeTest {

    @Test
    public void testGetAccessoryTypeByIndexValid() {
        assertEquals(accessorytype.JEWELRY, accessorytype.getaccessorytype(1));
        assertEquals(accessorytype.RIBBON, accessorytype.getaccessorytype(2));
        assertEquals(accessorytype.BEAR, accessorytype.getaccessorytype(3));
        assertEquals(accessorytype.GELBALL, accessorytype.getaccessorytype(4));
        assertEquals(accessorytype.POSTCARD, accessorytype.getaccessorytype(5));
        assertEquals(accessorytype.HEART, accessorytype.getaccessorytype(6));
    }

    @Test
    public void testGetAccessoryTypeByIndexInvalid() {
        assertNull(accessorytype.getaccessorytype(7));
    }

    @Test
    public void testGetAccessoryTypeByNameValid() {
        assertEquals(accessorytype.JEWELRY, accessorytype.getaccessorytype("Jewelry"));
        assertEquals(accessorytype.RIBBON, accessorytype.getaccessorytype("Ribbon"));
        assertEquals(accessorytype.BEAR, accessorytype.getaccessorytype("Bear"));
        assertEquals(accessorytype.GELBALL, accessorytype.getaccessorytype("Gel ball"));
        assertEquals(accessorytype.POSTCARD, accessorytype.getaccessorytype("Postcard"));
        assertEquals(accessorytype.HEART, accessorytype.getaccessorytype("Heart"));
    }

    @Test
    public void testGetAccessoryTypeByNameInvalid() {
        assertNull(accessorytype.getaccessorytype("NonExistingType"));
    }

    @Test
    public void testGetNumType() {
        assertEquals(1, accessorytype.JEWELRY.getNumType());
        assertEquals(2, accessorytype.RIBBON.getNumType());
        assertEquals(3, accessorytype.BEAR.getNumType());
        assertEquals(4, accessorytype.GELBALL.getNumType());
        assertEquals(5, accessorytype.POSTCARD.getNumType());
        assertEquals(6, accessorytype.HEART.getNumType());
    }
}

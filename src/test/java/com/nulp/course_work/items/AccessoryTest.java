// File: src/test/java/com/nulp/course_work/items/AccessoryTest.java

package com.nulp.course_work.items;

import com.nulp.course_work.constants.accessorytype;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AccessoryTest {

    private accessory accessory;
    private accessorytype accessoryType;

    @BeforeEach
    public void setUp() {
        accessoryType = accessorytype.JEWELRY;
        accessory = new accessory(accessoryType, "Red", 19.99, "Beautiful red accessory");
    }

    @Test
    public void testAccessoryBuilder() {
        accessory builtAccessory = accessory.builder()
                .setId(1)
                .setaccessorytype(accessoryType)
                .setColour("Blue")
                .setPrice(29.99)
                .setInfo("Beautiful blue accessory")
                .built();

        assertEquals(1, builtAccessory.getId());
        assertEquals(accessoryType, builtAccessory.getaccessorytype());
        assertEquals("Blue", builtAccessory.getColour());
        assertEquals(29.99, builtAccessory.getPrice());
        assertEquals("Beautiful blue accessory", builtAccessory.getInfo());
    }

    @Test
    public void testGetId() {
        accessory builtAccessory = accessory.builder().setId(1).built();
        assertEquals(1, builtAccessory.getId());
    }

    @Test
    public void testGetAccessoryType() {
        assertEquals(accessoryType, accessory.getaccessorytype());
    }

    @Test
    public void testGetColour() {
        assertEquals("Red", accessory.getColour());
    }

    @Test
    public void testGetPrice() {
        assertEquals(19.99, accessory.getPrice());
    }

    @Test
    public void testGetInfo() {
        assertEquals("Beautiful red accessory", accessory.getInfo());
    }

    @Test
    public void testToString() {
        String expected = "accessory{id=0, accessorytype=JEWELRY, colour='Red', price=19.99, info='Beautiful red accessory'}\n";
        assertEquals(expected, accessory.toString());
    }

    @Test
    public void testEquals() {
        accessory accessory1 = accessory.builder().setId(1).built();
        accessory accessory2 = accessory.builder().setId(1).built();
        accessory accessory3 = accessory.builder().setId(2).built();

        assertEquals(accessory1, accessory2);
        assertNotEquals(accessory1, accessory3);
    }

    @Test
    public void testHashCode() {
        accessory accessory1 = accessory.builder().setId(1).built();
        accessory accessory2 = accessory.builder().setId(1).built();
        accessory accessory3 = accessory.builder().setId(2).built();

        assertEquals(accessory1.hashCode(), accessory2.hashCode());
        assertNotEquals(accessory1.hashCode(), accessory3.hashCode());
    }
}

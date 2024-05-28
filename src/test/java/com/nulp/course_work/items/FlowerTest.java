// File: src/test/java/com/nulp/course_work/items/FlowerTest.java

package com.nulp.course_work.items;

import com.nulp.course_work.constants.flowertype;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Date;

import static org.junit.jupiter.api.Assertions.*;

public class FlowerTest {

    private flower flower;
    private flowertype flowerType;
    private Date currentDate;

    @BeforeEach
    public void setUp() {
        flowerType = flowertype.ROSE;
        currentDate = new Date(System.currentTimeMillis());
        flower = new flower(flowerType, "Red", 30.5, 15.99);
        flower.builder().setDate(currentDate);
    }

    @Test
    public void testFlowerBuilder() {
        flower builtFlower = flower.builder()
                .setId(1)
                .setflowertype(flowerType)
                .setColour("Blue")
                .setLength(25.0)
                .setPrice(20.99)
                .setDate(currentDate)
                .built();

        assertEquals(1, builtFlower.getId());
        assertEquals(flowerType, builtFlower.getflowertype());
        assertEquals("Blue", builtFlower.getColour());
        assertEquals(25.0, builtFlower.getLength());
        assertEquals(20.99, builtFlower.getPrice());
        assertEquals(currentDate, builtFlower.getDate());
    }

    @Test
    public void testGetId() {
        flower builtFlower = flower.builder().setId(1).built();
        assertEquals(1, builtFlower.getId());
    }

    @Test
    public void testGetFlowerType() {
        assertEquals(flowerType, flower.getflowertype());
    }

    @Test
    public void testGetColour() {
        assertEquals("Red", flower.getColour());
    }

    @Test
    public void testGetLength() {
        assertEquals(30.5, flower.getLength());
    }

    @Test
    public void testGetPrice() {
        assertEquals(15.99, flower.getPrice());
    }

    @Test
    public void testGetDate() {
        flower builtFlower = flower.builder().setDate(currentDate).built();
        assertEquals(currentDate, builtFlower.getDate());
    }

    @Test
    public void testToString() {
        flower builtFlower = flower.builder()
                .setId(1)
                .setflowertype(flowerType)
                .setColour("Red")
                .setLength(30.5)
                .setPrice(15.99)
                .setDate(currentDate)
                .built();
        String expected = "flower{id=1, flowertype=ROSE, colour='Red', length=30.5, price=15.99, date=" + currentDate + "}\n";
        assertEquals(expected, builtFlower.toString());
    }

    @Test
    public void testEquals() {
        flower flower1 = flower.builder().setId(1).built();
        flower flower2 = flower.builder().setId(1).built();
        flower flower3 = flower.builder().setId(2).built();

        assertEquals(flower1, flower2);
        assertNotEquals(flower1, flower3);
    }

    @Test
    public void testHashCode() {
        flower flower1 = flower.builder().setId(1).built();
        flower flower2 = flower.builder().setId(1).built();
        flower flower3 = flower.builder().setId(2).built();

        assertEquals(flower1.hashCode(), flower2.hashCode());
        assertNotEquals(flower1.hashCode(), flower3.hashCode());
    }
}

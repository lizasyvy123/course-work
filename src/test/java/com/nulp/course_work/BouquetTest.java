// File: src/test/java/com/nulp/course_work/BouquetTest.java

package com.nulp.course_work;

import com.nulp.course_work.items.accessory;
import com.nulp.course_work.items.flower;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.util.ArrayList;

import static com.nulp.course_work.sortfilter.FilterAccessoriesWithEntering.filteredAccessories;
import static com.nulp.course_work.sortfilter.FilterflowersWithEntering.filteredflowers;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class BouquetTest {

    private accessory mockAccessory1;
    private accessory mockAccessory2;
    private flower mockFlower1;
    private flower mockFlower2;

    @BeforeEach
    public void setUp() {
        mockAccessory1 = mock(accessory.class);
        mockAccessory2 = mock(accessory.class);
        mockFlower1 = mock(flower.class);
        mockFlower2 = mock(flower.class);

        Bouquet.bouquetflowers.clear();
        Bouquet.bouquetAccessories.clear();
        filteredAccessories.clear();
        filteredflowers.clear();
    }

    @Test
    public void testPriceOfBouquet() {
        when(mockFlower1.getPrice()).thenReturn(10.0);
        when(mockFlower2.getPrice()).thenReturn(15.0);
        when(mockAccessory1.getPrice()).thenReturn(5.0);
        when(mockAccessory2.getPrice()).thenReturn(7.0);

        Bouquet.bouquetflowers.add(mockFlower1);
        Bouquet.bouquetflowers.add(mockFlower2);
        Bouquet.bouquetAccessories.add(mockAccessory1);
        Bouquet.bouquetAccessories.add(mockAccessory2);

        double expectedPrice = 10.0 + 15.0 + 5.0 + 7.0;
        double actualPrice = Bouquet.priceOfBouquet();

        assertEquals(expectedPrice, actualPrice);
    }

    @Test
    public void testAverageLengthOfflowers() {
        when(mockFlower1.getLength()).thenReturn(10.0);
        when(mockFlower2.getLength()).thenReturn(20.0);

        Bouquet.bouquetflowers.add(mockFlower1);
        Bouquet.bouquetflowers.add(mockFlower2);

        double expectedAverageLength = (10.0 + 20.0) / 2;
        double actualAverageLength = Bouquet.averageLengthOfflowers();
        assertEquals(expectedAverageLength, actualAverageLength);
    }

    @Test
    public void testAddAccessoryFromFilteredListFX() {
        filteredAccessories.add(mockAccessory1);

        Bouquet.addAccessoryFromFilteredListFX(0);

        assertTrue(Bouquet.bouquetAccessories.contains(mockAccessory1));
        assertTrue(filteredAccessories.isEmpty());
    }

    @Test
    public void testAddflowerFromFilteredListFX() {
        filteredflowers.add(mockFlower1);

        Bouquet.addflowerFromFilteredListFX(0);

        assertTrue(Bouquet.bouquetflowers.contains(mockFlower1));
        assertTrue(filteredflowers.isEmpty());
    }

    @Test
    public void testDeleteflowerFromBouqFX() {
        Bouquet.bouquetflowers.add(mockFlower1);
        Bouquet.bouquetflowers.add(mockFlower2);

        Bouquet.deleteflowerFromBouqFX(0);

        assertFalse(Bouquet.bouquetflowers.contains(mockFlower1));
        assertTrue(Bouquet.bouquetflowers.contains(mockFlower2));
    }

    @Test
    public void testDeleteAccessoryFromBouqFX() {
        Bouquet.bouquetAccessories.add(mockAccessory1);
        Bouquet.bouquetAccessories.add(mockAccessory2);

        Bouquet.deleteAccessoryFromBouqFX(0);

        assertFalse(Bouquet.bouquetAccessories.contains(mockAccessory1));
        assertTrue(Bouquet.bouquetAccessories.contains(mockAccessory2));
    }

    @Test
    public void testRefreshBouquet() {
        Bouquet.bouquetflowers.add(mockFlower1);
        Bouquet.bouquetAccessories.add(mockAccessory1);

        Bouquet.refreshBouquet();

        assertTrue(Bouquet.bouquetflowers.isEmpty());
        assertTrue(Bouquet.bouquetAccessories.isEmpty());
    }
}

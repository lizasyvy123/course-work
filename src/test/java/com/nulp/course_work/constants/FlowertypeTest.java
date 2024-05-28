// File: src/test/java/com/nulp/course_work/constants/FlowertypeTest.java

package com.nulp.course_work.constants;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class FlowertypeTest {

    @Test
    public void testGetFlowertypeByIndexValid() {
        assertEquals(flowertype.ROSE, flowertype.getflowertype(1));
        assertEquals(flowertype.LAVANDULA, flowertype.getflowertype(2));
        assertEquals(flowertype.LILY, flowertype.getflowertype(3));
        assertEquals(flowertype.LUPINE, flowertype.getflowertype(4));
        assertEquals(flowertype.TULIP, flowertype.getflowertype(5));
        assertEquals(flowertype.DAISY, flowertype.getflowertype(6));
        assertEquals(flowertype.GARDENIA, flowertype.getflowertype(7));
        assertEquals(flowertype.ORCHID, flowertype.getflowertype(8));
        assertEquals(flowertype.GYPSOPHILA, flowertype.getflowertype(9));
        assertEquals(flowertype.CHAMOMILE, flowertype.getflowertype(10));
        assertEquals(flowertype.CHRYSANTHEMUM, flowertype.getflowertype(11));
    }

    @Test
    public void testGetFlowertypeByIndexInvalid() {
        assertNull(flowertype.getflowertype(12));
    }

    @Test
    public void testGetFlowertypeByNameValid() {
        assertEquals(flowertype.ROSE, flowertype.getflowertype("Rose"));
        assertEquals(flowertype.LAVANDULA, flowertype.getflowertype("Lavandula"));
        assertEquals(flowertype.LILY, flowertype.getflowertype("Lily"));
        assertEquals(flowertype.LUPINE, flowertype.getflowertype("Lupine"));
        assertEquals(flowertype.TULIP, flowertype.getflowertype("Tulip"));
        assertEquals(flowertype.DAISY, flowertype.getflowertype("Daisy"));
        assertEquals(flowertype.GARDENIA, flowertype.getflowertype("Gardenia"));
        assertEquals(flowertype.ORCHID, flowertype.getflowertype("Orchid"));
        assertEquals(flowertype.GYPSOPHILA, flowertype.getflowertype("Gypsophila"));
        assertEquals(flowertype.CHAMOMILE, flowertype.getflowertype("Chamomile"));
        assertEquals(flowertype.CHRYSANTHEMUM, flowertype.getflowertype("Chrysanthemum"));
    }

    @Test
    public void testGetFlowertypeByNameInvalid() {
        assertNull(flowertype.getflowertype("NonExistingType"));
    }

    @Test
    public void testGetNumType() {
        assertEquals(1, flowertype.ROSE.getNumType());
        assertEquals(2, flowertype.LAVANDULA.getNumType());
        assertEquals(3, flowertype.LILY.getNumType());
        assertEquals(4, flowertype.LUPINE.getNumType());
        assertEquals(5, flowertype.TULIP.getNumType());
        assertEquals(6, flowertype.DAISY.getNumType());
        assertEquals(7, flowertype.GARDENIA.getNumType());
        assertEquals(8, flowertype.ORCHID.getNumType());
        assertEquals(9, flowertype.GYPSOPHILA.getNumType());
        assertEquals(10, flowertype.CHAMOMILE.getNumType());
        assertEquals(11, flowertype.CHRYSANTHEMUM.getNumType());
    }
}

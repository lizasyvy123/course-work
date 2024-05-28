// File: src/test/java/com/nulp/course_work/itemwithDB/FlowerDBTest.java

package com.nulp.course_work.itemwithDB;

import com.nulp.course_work.Database;
import com.nulp.course_work.constants.flowertype;
import com.nulp.course_work.items.flower;
import org.apache.logging.log4j.core.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class FlowerDBTest {

    private flowerDB flowerDB;
    private Connection mockConnection;
    private PreparedStatement mockPreparedStatement;
    private ResultSet mockResultSet;
    private Date currentDate;

    @BeforeEach
    public void setUp() {
        flowerDB = new flowerDB();
        mockConnection = mock(Connection.class);
        mockPreparedStatement = mock(PreparedStatement.class);
        mockResultSet = mock(ResultSet.class);

        // Mock the static Database connection
        Database.connection = mockConnection;

        currentDate = new Date(System.currentTimeMillis());
    }

    @Test
    public void testGetById() throws SQLException {
        when(mockConnection.prepareStatement(flowerDB.GET_flower_BY_ID)).thenReturn(mockPreparedStatement);
        when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);

        when(mockResultSet.next()).thenReturn(true);
        when(mockResultSet.getInt("flowerID")).thenReturn(1);
        when(mockResultSet.getInt("flowertypeID")).thenReturn(1);
        when(mockResultSet.getString("flowerColour")).thenReturn("Red");
        when(mockResultSet.getDouble("flowerLength")).thenReturn(30.5);
        when(mockResultSet.getDouble("flowerPrice")).thenReturn(15.99);
        when(mockResultSet.getDate("fDateDelivery")).thenReturn(currentDate);

        flower result = flowerDB.getById(1);

        assertNotNull(result);
        assertEquals(1, result.getId());
        assertEquals(flowertype.ROSE, result.getflowertype());
        assertEquals("Red", result.getColour());
        assertEquals(30.5, result.getLength());
        assertEquals(15.99, result.getPrice());
        assertEquals(currentDate.getTime(), result.getDate().getTime());

        verify(mockPreparedStatement).setInt(1, 1);
        verify(mockPreparedStatement).executeQuery();
    }

    @Test
    public void testDelete() throws SQLException {
        flower testFlower = flower.builder().setId(1).built();

        when(mockConnection.prepareStatement(flowerDB.DELETE_flower_BY_ID)).thenReturn(mockPreparedStatement);
        when(mockPreparedStatement.executeUpdate()).thenReturn(1);

        int result = flowerDB.delete(testFlower);

        assertEquals(1, result);

        verify(mockPreparedStatement).setInt(1, 1);
        verify(mockPreparedStatement).executeUpdate();
    }

    @Test
    public void testInsert() throws SQLException {
        flower testFlower = flower.builder()
                .setflowertype(flowertype.ROSE)
                .setColour("Red")
                .setLength(30.5)
                .setPrice(15.99)
                .built();

        when(mockConnection.prepareStatement(flowerDB.INSERT_flower)).thenReturn(mockPreparedStatement);
        when(mockPreparedStatement.executeUpdate()).thenReturn(1);

        int result = flowerDB.insert(testFlower);

        assertEquals(1, result);

        verify(mockPreparedStatement).setInt(1, flowertype.ROSE.getNumType());
        verify(mockPreparedStatement).setString(2, "Red");
        verify(mockPreparedStatement).setDouble(3, 30.5);
        verify(mockPreparedStatement).setDouble(4, 15.99);
        verify(mockPreparedStatement).executeUpdate();
    }

    @Test
    public void testInsertWithEnteringFX() throws SQLException {
        flower testFlower = flower.builder()
                .setflowertype(flowertype.ROSE)
                .setColour("Red")
                .setLength(30.5)
                .setPrice(15.99)
                .built();

        when(mockConnection.prepareStatement(flowerDB.INSERT_flower)).thenReturn(mockPreparedStatement);
        when(mockPreparedStatement.executeUpdate()).thenReturn(1);

        flowerDB.insertWithEnteringFX(5, testFlower);

        verify(mockPreparedStatement, times(5)).executeUpdate();
    }


}

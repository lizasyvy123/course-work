// File: src/test/java/com/nulp/course_work/itemwithDB/AccessoryDBTest.java

package com.nulp.course_work.itemwithDB;

import com.nulp.course_work.Database;
import com.nulp.course_work.constants.accessorytype;
import com.nulp.course_work.items.accessory;
import org.apache.logging.log4j.core.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class AccessoryDBTest {

    private AccessoryDB accessoryDB;
    private Connection mockConnection;
    private PreparedStatement mockPreparedStatement;
    private ResultSet mockResultSet;

    @BeforeEach
    public void setUp() {
        accessoryDB = new AccessoryDB();
        mockConnection = mock(Connection.class);
        mockPreparedStatement = mock(PreparedStatement.class);
        mockResultSet = mock(ResultSet.class);

        // Mock the static Database connection
        Database.connection = mockConnection;
    }

    @Test
    public void testGetById() throws SQLException {
        when(mockConnection.prepareStatement(AccessoryDB.GET_ACCESSORY_BY_ID)).thenReturn(mockPreparedStatement);
        when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);

        when(mockResultSet.next()).thenReturn(true);
        when(mockResultSet.getInt("accessoryID")).thenReturn(1);
        when(mockResultSet.getInt("accessoryTypeID")).thenReturn(1);
        when(mockResultSet.getString("accessoryColour")).thenReturn("Red");
        when(mockResultSet.getDouble("accessoryPrice")).thenReturn(19.99);
        when(mockResultSet.getString("accessoryInfo")).thenReturn("Beautiful red accessory");

        accessory result = accessoryDB.getById(1);

        assertNotNull(result);
        assertEquals(1, result.getId());
        assertEquals(accessorytype.JEWELRY, result.getaccessorytype());
        assertEquals("Red", result.getColour());
        assertEquals(19.99, result.getPrice());
        assertEquals("Beautiful red accessory", result.getInfo());

        verify(mockPreparedStatement).setInt(1, 1);
        verify(mockPreparedStatement).executeQuery();
    }

    @Test
    public void testDelete() throws SQLException {
        accessory testAccessory = accessory.builder().setId(1).built();

        when(mockConnection.prepareStatement(AccessoryDB.DELETE_ACCESSORY_BY_ID)).thenReturn(mockPreparedStatement);
        when(mockPreparedStatement.executeUpdate()).thenReturn(1);

        int result = accessoryDB.delete(testAccessory);

        assertEquals(1, result);

        verify(mockPreparedStatement).setInt(1, 1);
        verify(mockPreparedStatement).executeUpdate();
    }

    @Test
    public void testInsert() throws SQLException {
        accessory testAccessory = accessory.builder()
                .setaccessorytype(accessorytype.JEWELRY)
                .setColour("Red")
                .setPrice(19.99)
                .setInfo("Beautiful red accessory")
                .built();

        when(mockConnection.prepareStatement(AccessoryDB.INSERT_ACCESSORY)).thenReturn(mockPreparedStatement);
        when(mockPreparedStatement.executeUpdate()).thenReturn(1);

        int result = accessoryDB.insert(testAccessory);

        assertEquals(1, result);

        verify(mockPreparedStatement).setInt(1, accessorytype.JEWELRY.getNumType());
        verify(mockPreparedStatement).setString(2, "Red");
        verify(mockPreparedStatement).setDouble(3, 19.99);
        verify(mockPreparedStatement).setString(4, "Beautiful red accessory");
        verify(mockPreparedStatement).executeUpdate();
    }

    @Test
    public void testInsertWithEnteringFX() throws SQLException {
        accessory testAccessory = accessory.builder()
                .setaccessorytype(accessorytype.JEWELRY)
                .setColour("Red")
                .setPrice(19.99)
                .setInfo("Beautiful red accessory")
                .built();

        when(mockConnection.prepareStatement(AccessoryDB.INSERT_ACCESSORY)).thenReturn(mockPreparedStatement);
        when(mockPreparedStatement.executeUpdate()).thenReturn(1);

        accessoryDB.insertWithEnteringFX(5, testAccessory);

        verify(mockPreparedStatement, times(5)).executeUpdate();
    }


}

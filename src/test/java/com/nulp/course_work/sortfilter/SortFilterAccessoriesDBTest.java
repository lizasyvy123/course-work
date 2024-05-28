// File: src/test/java/com/nulp/course_work/sortfilter/SortFilterAccessoriesDBTest.java

package com.nulp.course_work.sortfilter;

import com.nulp.course_work.Database;
import com.nulp.course_work.constants.accessorytype;
import com.nulp.course_work.items.accessory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class SortFilterAccessoriesDBTest {

    private Connection mockConnection;
    private PreparedStatement mockPreparedStatement;
    private ResultSet mockResultSet;
    private SortFilterAccessoriesDB sortFilterAccessoriesDB;

    @BeforeEach
    public void setUp() throws SQLException {
        mockConnection = mock(Connection.class);
        mockPreparedStatement = mock(PreparedStatement.class);
        mockResultSet = mock(ResultSet.class);
        sortFilterAccessoriesDB = new SortFilterAccessoriesDB();

        // Ensure the mockPreparedStatement is returned when any prepareStatement method is called
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);
    }

    @Test
    public void testGetAll() throws SQLException {
        try (MockedStatic<Database> mockedDatabase = mockStatic(Database.class)) {
            mockedDatabase.when(Database::getConnection).thenReturn(mockConnection);

            when(mockConnection.prepareStatement(SortFilterAccessoriesDB.GET_ALL_accessory)).thenReturn(mockPreparedStatement);
            when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);

            when(mockResultSet.next()).thenReturn(true).thenReturn(true).thenReturn(false);
            when(mockResultSet.getInt("accessoryID")).thenReturn(1).thenReturn(2);
            when(mockResultSet.getInt("accessoryTypeID")).thenReturn(accessorytype.JEWELRY.getNumType());
            when(mockResultSet.getString("accessoryColour")).thenReturn("Red").thenReturn("Blue");
            when(mockResultSet.getDouble("accessoryPrice")).thenReturn(10.0).thenReturn(15.0);
            when(mockResultSet.getString("accessoryInfo")).thenReturn("Info1").thenReturn("Info2");

            ArrayList<accessory> accessories = sortFilterAccessoriesDB.getAll();

            assertEquals(2, accessories.size());
            assertEquals(1, accessories.get(0).getId());
            assertEquals(2, accessories.get(1).getId());
        }
    }

    @Test
    public void testFilterInPriceRange() throws SQLException {
        try (MockedStatic<Database> mockedDatabase = mockStatic(Database.class)) {
            mockedDatabase.when(Database::getConnection).thenReturn(mockConnection);

            when(mockConnection.prepareStatement(SortFilterAccessoriesDB.FILTER_ACCESSORIES_IN_PRICE_RANGE)).thenReturn(mockPreparedStatement);
            when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);

            when(mockResultSet.next()).thenReturn(true).thenReturn(false);
            when(mockResultSet.getInt("accessoryID")).thenReturn(1);
            when(mockResultSet.getInt("accessoryTypeID")).thenReturn(accessorytype.JEWELRY.getNumType());
            when(mockResultSet.getString("accessoryColour")).thenReturn("Red");
            when(mockResultSet.getDouble("accessoryPrice")).thenReturn(10.0);
            when(mockResultSet.getString("accessoryInfo")).thenReturn("Info1");

            ArrayList<accessory> accessories = sortFilterAccessoriesDB.filterInPriceRange(1.0, 20.0);

            assertEquals(1, accessories.size());
            assertEquals(1, accessories.get(0).getId());
        }
    }

    @Test
    public void testSortByType() throws SQLException {
        try (MockedStatic<Database> mockedDatabase = mockStatic(Database.class)) {
            mockedDatabase.when(Database::getConnection).thenReturn(mockConnection);

            when(mockConnection.prepareStatement(SortFilterAccessoriesDB.SORT_BY_TYPE_accessory)).thenReturn(mockPreparedStatement);
            when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);

            when(mockResultSet.next()).thenReturn(true).thenReturn(false);
            when(mockResultSet.getInt("accessoryID")).thenReturn(1);
            when(mockResultSet.getInt("accessoryTypeID")).thenReturn(accessorytype.JEWELRY.getNumType());
            when(mockResultSet.getString("accessoryColour")).thenReturn("Red");
            when(mockResultSet.getDouble("accessoryPrice")).thenReturn(10.0);
            when(mockResultSet.getString("accessoryInfo")).thenReturn("Info1");

            ArrayList<accessory> accessories = sortFilterAccessoriesDB.sortByType(accessorytype.JEWELRY);

            assertEquals(1, accessories.size());
            assertEquals(1, accessories.get(0).getId());
        }
    }

    @Test
    public void testMinOrMaxStatement() throws SQLException {
        try (MockedStatic<Database> mockedDatabase = mockStatic(Database.class)) {
            mockedDatabase.when(Database::getConnection).thenReturn(mockConnection);

            when(mockConnection.prepareStatement(SortFilterAccessoriesDB.MAX_PRICE_accessory)).thenReturn(mockPreparedStatement);
            when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);

            when(mockResultSet.next()).thenReturn(true);
            when(mockResultSet.getDouble(1)).thenReturn(20.0);

            Double maxPrice = sortFilterAccessoriesDB.maxPrice();

            assertEquals(20.0, maxPrice);
        }
    }
}

// File: src/test/java/com/nulp/course_work/sortfilter/SortFilterflowersDBTest.java

package com.nulp.course_work.sortfilter;

import com.nulp.course_work.Database;
import com.nulp.course_work.constants.flowertype;
import com.nulp.course_work.constants.ordersort;
import com.nulp.course_work.items.flower;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class SortFilterflowersDBTest {

    private Connection mockConnection;
    private PreparedStatement mockPreparedStatement;
    private ResultSet mockResultSet;
    private SortFilterflowersDB sortFilterflowersDB;
    private Date currentDate;

    @BeforeEach
    public void setUp() throws SQLException {
        mockConnection = mock(Connection.class);
        mockPreparedStatement = mock(PreparedStatement.class);
        mockResultSet = mock(ResultSet.class);
        sortFilterflowersDB = new SortFilterflowersDB();
        currentDate = new Date(System.currentTimeMillis());

        // Ensure the mockPreparedStatement is returned when any prepareStatement method is called
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);
    }

    @Test
    public void testGetAll() throws SQLException {
        try (MockedStatic<Database> mockedDatabase = mockStatic(Database.class)) {
            mockedDatabase.when(Database::getConnection).thenReturn(mockConnection);

            when(mockConnection.prepareStatement(SortFilterflowersDB.GET_ALL_flower)).thenReturn(mockPreparedStatement);
            when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);

            when(mockResultSet.next()).thenReturn(true).thenReturn(true).thenReturn(false);
            when(mockResultSet.getInt("flowerID")).thenReturn(1).thenReturn(2);
            when(mockResultSet.getInt("flowertypeID")).thenReturn(flowertype.ROSE.getNumType());
            when(mockResultSet.getString("flowerColour")).thenReturn("Red").thenReturn("White");
            when(mockResultSet.getDouble("flowerLength")).thenReturn(10.0).thenReturn(15.0);
            when(mockResultSet.getDouble("flowerPrice")).thenReturn(5.0).thenReturn(7.0);
            when(mockResultSet.getDate("fDateDelivery")).thenReturn(currentDate);

            ArrayList<flower> flowers = sortFilterflowersDB.getAll();

            assertEquals(2, flowers.size());
            assertEquals(1, flowers.get(0).getId());
            assertEquals(2, flowers.get(1).getId());
        }
    }

    @Test
    public void testFilterInPriceRange() throws SQLException {
        try (MockedStatic<Database> mockedDatabase = mockStatic(Database.class)) {
            mockedDatabase.when(Database::getConnection).thenReturn(mockConnection);

            when(mockConnection.prepareStatement(SortFilterflowersDB.FILTER_flowerS_IN_PRICE_RANGE)).thenReturn(mockPreparedStatement);
            when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);

            when(mockResultSet.next()).thenReturn(true).thenReturn(false);
            when(mockResultSet.getInt("flowerID")).thenReturn(1);
            when(mockResultSet.getInt("flowertypeID")).thenReturn(flowertype.ROSE.getNumType());
            when(mockResultSet.getString("flowerColour")).thenReturn("Red");
            when(mockResultSet.getDouble("flowerLength")).thenReturn(10.0);
            when(mockResultSet.getDouble("flowerPrice")).thenReturn(5.0);
            when(mockResultSet.getDate("fDateDelivery")).thenReturn(currentDate);

            ArrayList<flower> flowers = sortFilterflowersDB.filterInPriceRange(1.0, 10.0);

            assertEquals(1, flowers.size());
            assertEquals(1, flowers.get(0).getId());
        }
    }

    @Test
    public void testFilterInLengthRange() throws SQLException {
        try (MockedStatic<Database> mockedDatabase = mockStatic(Database.class)) {
            mockedDatabase.when(Database::getConnection).thenReturn(mockConnection);

            when(mockConnection.prepareStatement(SortFilterflowersDB.FILTER_flowerS_IN_LENGTH_RANGE)).thenReturn(mockPreparedStatement);
            when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);

            when(mockResultSet.next()).thenReturn(true).thenReturn(false);
            when(mockResultSet.getInt("flowerID")).thenReturn(1);
            when(mockResultSet.getInt("flowertypeID")).thenReturn(flowertype.ROSE.getNumType());
            when(mockResultSet.getString("flowerColour")).thenReturn("Red");
            when(mockResultSet.getDouble("flowerLength")).thenReturn(10.0);
            when(mockResultSet.getDouble("flowerPrice")).thenReturn(5.0);
            when(mockResultSet.getDate("fDateDelivery")).thenReturn(currentDate);

            ArrayList<flower> flowers = sortFilterflowersDB.filterInLengthRange(5.0, 15.0);

            assertEquals(1, flowers.size());
            assertEquals(1, flowers.get(0).getId());
        }
    }

    @Test
    public void testSortByType() throws SQLException {
        try (MockedStatic<Database> mockedDatabase = mockStatic(Database.class)) {
            mockedDatabase.when(Database::getConnection).thenReturn(mockConnection);

            when(mockConnection.prepareStatement(SortFilterflowersDB.SORT_BY_TYPE_flower)).thenReturn(mockPreparedStatement);
            when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);

            when(mockResultSet.next()).thenReturn(true).thenReturn(false);
            when(mockResultSet.getInt("flowerID")).thenReturn(1);
            when(mockResultSet.getInt("flowertypeID")).thenReturn(flowertype.ROSE.getNumType());
            when(mockResultSet.getString("flowerColour")).thenReturn("Red");
            when(mockResultSet.getDouble("flowerLength")).thenReturn(10.0);
            when(mockResultSet.getDouble("flowerPrice")).thenReturn(5.0);
            when(mockResultSet.getDate("fDateDelivery")).thenReturn(currentDate);

            ArrayList<flower> flowers = sortFilterflowersDB.sortByType(flowertype.ROSE);

            assertEquals(1, flowers.size());
            assertEquals(1, flowers.get(0).getId());
        }
    }

    @Test
    public void testSortByFresh() throws SQLException {
        try (MockedStatic<Database> mockedDatabase = mockStatic(Database.class)) {
            mockedDatabase.when(Database::getConnection).thenReturn(mockConnection);

            when(mockConnection.prepareStatement(SortFilterflowersDB.SORT_BY_FRESH_flower + ordersort.ASC)).thenReturn(mockPreparedStatement);
            when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);

            when(mockResultSet.next()).thenReturn(true).thenReturn(false);
            when(mockResultSet.getInt("flowerID")).thenReturn(1);
            when(mockResultSet.getInt("flowertypeID")).thenReturn(flowertype.ROSE.getNumType());
            when(mockResultSet.getString("flowerColour")).thenReturn("Red");
            when(mockResultSet.getDouble("flowerLength")).thenReturn(10.0);
            when(mockResultSet.getDouble("flowerPrice")).thenReturn(5.0);
            when(mockResultSet.getDate("fDateDelivery")).thenReturn(currentDate);

            ArrayList<flower> flowers = sortFilterflowersDB.sortByFresh(ordersort.ASC);

            assertEquals(1, flowers.size());
            assertEquals(1, flowers.get(0).getId());
        }
    }

    @Test
    public void testMinOrMaxStatement() throws SQLException {
        try (MockedStatic<Database> mockedDatabase = mockStatic(Database.class)) {
            mockedDatabase.when(Database::getConnection).thenReturn(mockConnection);

            when(mockConnection.prepareStatement(SortFilterflowersDB.MAX_PRICE_flower)).thenReturn(mockPreparedStatement);
            when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);

            when(mockResultSet.next()).thenReturn(true);
            when(mockResultSet.getDouble(1)).thenReturn(10.0);

            Double maxPrice = sortFilterflowersDB.maxPrice();

            assertEquals(10.0, maxPrice);
        }
    }
}

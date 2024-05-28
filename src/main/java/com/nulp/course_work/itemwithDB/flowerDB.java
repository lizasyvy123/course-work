package com.nulp.course_work.itemwithDB;

import com.nulp.course_work.Database;
import com.nulp.course_work.constants.flowertype;
import com.nulp.course_work.items.flower;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static com.nulp.course_work.Bouquet.bouquetflowers;
import static com.nulp.course_work.sortfilter.FilterflowersWithEntering.filteredflowers;

public class flowerDB implements  ItemDB<flower>{
    public static final Logger LOGI = (Logger) LogManager.getLogger(flowerDB.class);

    static final String GET_flower_BY_ID = "SELECT * FROM dbo.flowers WHERE flowerID = ?";
    static final String DELETE_flower_BY_ID = "DELETE FROM dbo.flowers WHERE flowerID = ?";
    static final String INSERT_flower = "INSERT INTO dbo.flowers ( flowertypeID, flowerColour, flowerLength," +
            " flowerPrice) VALUES( ?, ?, ?, ?);";

    @Override
    public flower getById(int id) {
        Database.connection = Database.getConnection();
        flower flower = null;

        try {
            PreparedStatement ps = Database.connection.prepareStatement(GET_flower_BY_ID);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                flower = flower.builder()
                        .setId(rs.getInt("flowerID"))
                        .setflowertype(flowertype.getflowertype(rs.getInt("flowertypeID")))
                        .setColour(rs.getString("flowerColour"))
                        .setLength(rs.getDouble("flowerLength"))
                        .setPrice(rs.getDouble("flowerPrice"))
                        .setDate(rs.getDate("fDateDelivery"))
                        .built();
            }
            LOGI.info("Get by ID flower.");
            return flower;

        } catch (SQLException e) {
            LOGI.error("Detect error while searching flower! Try again!");
        }
        return null;
    }

    @Override
    public int delete(flower flower) {
        Database.connection = Database.getConnection();

        try {
            PreparedStatement ps = Database.connection.prepareStatement(DELETE_flower_BY_ID);
            ps.setInt(1, flower.getId());
            LOGI.info("Delete flower.");

            return ps.executeUpdate();

        } catch (SQLException e) {
            LOGI.error("Detect error while deleting flower!");
            return 0;
        }
    }

    @Override
    public int insert(flower flower) {
        Database.connection = Database.getConnection();

        try {
            PreparedStatement ps = Database.connection.prepareStatement(INSERT_flower);
            ps.setInt(1, flower.getflowertype().getNumType());
            ps.setString(2, flower.getColour());
            ps.setDouble(3,  flower.getLength());
            ps.setDouble(4,  flower.getPrice());
            LOGI.info("Insert flower.");

            return ps.executeUpdate();

        } catch (SQLException e) {
            LOGI.error(e);
            LOGI.error("Detect error during insertion flower!");
            return 0;
        }
    }



    public void insertWithEnteringFX(int count, flower flower){

        int added = 0;
        for(int i = 0; i < count; i++) if(insert(flower) == 1) added++;
        LOGI.info("Insert flower. " + added + "elems.");

    }


    public void deleteFromFilteredFX(int index) {
        flower flower = filteredflowers.get(index);
        if(flower != null){
            delete(flower);
            filteredflowers.set(filteredflowers.indexOf(flower), null);
        }
        LOGI.info("Delete for filtered flower.");

    }

    public void deleteAllFromBouquet(){
        for(flower flower : bouquetflowers){
            delete(flower);
        }
        bouquetflowers.clear();
        LOGI.info("Delete flower from bouquet.");

    }
}

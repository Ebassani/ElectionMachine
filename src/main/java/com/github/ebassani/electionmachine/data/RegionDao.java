package com.github.ebassani.electionmachine.data;

import com.github.ebassani.electionmachine.data.model.Region;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RegionDao {

    static Database db;

    static {
        try {
            db = Database.getInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Function that goes through the database, saves all the regions in an array and return this array
     */
    public static List<Region> getRegions() throws SQLException {
        ResultSet rs = db.conn.createStatement().executeQuery("SELECT * FROM regions");
        ArrayList<Region> regions = new ArrayList<>();
        while (rs.next()) {
            Region region = new Region();
            region.setRegion(rs.getString("region"));
            regions.add(region);
        }
        return regions;
    }
}

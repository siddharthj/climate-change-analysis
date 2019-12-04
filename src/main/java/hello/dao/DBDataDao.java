package hello.dao;

import hello.model.DBData;
import hello.model.GreenhouseGasBySector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

//CISE Oracle Server	sj3@//oracle.cise.ufl.edu:1521/orcl
@Repository
public class DBDataDao {
    @Autowired
    private DataSource dataSource;
 //   private JdbcTemplate jdbcTemplate

    public List<DBData> getSectorData()
            throws SQLException {
        List<DBData> data = new ArrayList<>();
        Connection con = dataSource.getConnection();
        String query = "SELECT  (\n" +
                "        SELECT COUNT(*)\n" +
                "        FROM   CONTINENT\n" +
                "        ) AS CONTINENT,\n" +
                "        (\n" +
                "        SELECT COUNT(*)\n" +
                "        FROM   COUNTRY\n" +
                "        ) AS COUNTRY,\n" +
                "        (\n" +
                "        SELECT COUNT(*)\n" +
                "        FROM   COUNTRY_TYPE\n" +
                "        ) AS COUNTRY_TYPE,\n" +
                "        (\n" +
                "        SELECT COUNT(*)\n" +
                "        FROM   FOSSIL_FUEL_TYPE\n" +
                "        ) AS FOSSIL_FUEL_TYPE,\n" +
                "        (\n" +
                "        SELECT COUNT(*)\n" +
                "        FROM   FOSSIL_FUEL\n" +
                "        ) AS FOSSIL_FUEL,\n" +
                "        (\n" +
                "        SELECT COUNT(*)\n" +
                "        FROM   GREENHOUSE_GAS_EMISSION\n" +
                "        ) AS GREENHOUSE_GAS_EMISSION,\n" +
                "        (\n" +
                "        SELECT COUNT(*)\n" +
                "        FROM   GREENHOUSE_GAS_TYPE\n" +
                "        ) AS GREENHOUSE_GAS_TYPE,\n" +
                "        (\n" +
                "        SELECT COUNT(*)\n" +
                "        FROM   POPULATION\n" +
                "        ) AS POPULATION,\n" +
                "        (\n" +
                "         SELECT COUNT(*)\n" +
                "        FROM   SECTOR\n" +
                "        ) AS SECTOR,\n" +
                "        (\n" +
                "        SELECT COUNT(*)\n" +
                "        FROM   SECTOR_TYPE\n" +
                "        ) AS SECTOR_TYPE,\n" +
                "        (\n" +
                "        SELECT COUNT(*)\n" +
                "        FROM   TEMPERATURE\n" +
                "        ) AS TEMPERATURE\n" +
                "FROM    dual";
        try (Statement stmt = con.createStatement()) {
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                data.add(new DBData("CONTINENT", rs.getInt("CONTINENT")));
                data.add(new DBData("COUNTRY", rs.getInt("COUNTRY")));
                data.add(new DBData("COUNTRY_TYPE", rs.getInt("COUNTRY_TYPE")));
                data.add(new DBData("FOSSIL_FUEL_TYPE", rs.getInt("FOSSIL_FUEL_TYPE")));
                data.add(new DBData("FOSSIL_FUEL", rs.getInt("FOSSIL_FUEL")));
                data.add(new DBData("GREENHOUSE_GAS_TYPE", rs.getInt("GREENHOUSE_GAS_TYPE")));
                data.add(new DBData("POPULATION", rs.getInt("POPULATION")));
                data.add(new DBData("SECTOR", rs.getInt("SECTOR")));
                data.add(new DBData("SECTOR_TYPE", rs.getInt("SECTOR_TYPE")));
                data.add(new DBData("TEMPERATURE", rs.getInt("TEMPERATURE")));
            }
        }
        con.close();
        //data.add(new DBData("TOTAL", data.stream().map(DBData::getRowCount).reduce(0, Integer::sum)));
        return data;
    }
}

package hello.dao;

import hello.model.FuelEfficiencyData;
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
public class FuelDao {
    @Autowired
    private DataSource dataSource;
 //   private JdbcTemplate jdbcTemplate

    public List<FuelEfficiencyData> getFuelData()
            throws SQLException {
        List<FuelEfficiencyData> data = new ArrayList<>();
        Connection con = dataSource.getConnection();
        String query = "select " +
                "       con.continent_name, " +
                "       f.emission_year,  " +
                "       sum(f.emission_value)/sum(g.emission_amount) as \"RATIO\"\n" +
                "from country c, fossil_fuel f,  greenhouse_gas_emission g, continent con\n" +
                "where f.COUNTRY_CODE=c.COUNTRY_CODE " +
                "       and g.COUNTRY_CODE=c.COUNTRY_CODE " +
                "       and g.emission_year=f.emission_year " +
                "        and f.emission_value != 0 and c.continent_id = con.continent_id\n" +
                "group by f.emission_year, con.continent_name\n" +
                "order by f.emission_year";
        try (Statement stmt = con.createStatement()) {
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                String continent = rs.getString("continent_name");
                Integer year = rs.getInt("emission_year");
                Double value = rs.getDouble("RATIO");
                FuelEfficiencyData islandCount = new FuelEfficiencyData(year, continent, value);
                data.add(islandCount);
            }
        }
        con.close();
        return data;
    }
}

package hello.dao;

import hello.model.GreenhouseGasBySector;
import hello.model.IslandCount;
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
public class SectorDao {
    @Autowired
    private DataSource dataSource;
 //   private JdbcTemplate jdbcTemplate

    public List<GreenhouseGasBySector> getSectorData()
            throws SQLException {
        List<GreenhouseGasBySector> data = new ArrayList<>();
        Connection con = dataSource.getConnection();
        String query = "select " +
                "   sector_type.SECTOR_NAME as typename," +
                "   year," +
                "   sum(value) as totalvalue " +
                "   from( select sector.country_code as code," +
                "           sector.record_year as year, " +
                "               sector.sector_type_id as type," +
                "               greenhouse_gas_emission.emission_amount*sector.emission*0.01 as value " +
                "       from sector,greenhouse_gas_emission\n" +
                "where sector.country_code=greenhouse_gas_emission.country_code\n" +
                "and sector.record_year=greenhouse_gas_emission.emission_year\n" +
                "and greenhouse_gas_emission.gas_type='1'\n" +
                "and sector.record_year between '1970' and '2010'),sector_type\n" +
                "where type=sector_type.SECTOR_TYPE_ID\n" +
                "group by sector_type.SECTOR_NAME,year\n" +
                "order by year";
        try (Statement stmt = con.createStatement()) {
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                String country = rs.getString("typename");
                Integer year = rs.getInt("year");
                Double value = rs.getDouble("totalvalue");
                GreenhouseGasBySector islandCount = new GreenhouseGasBySector(year, country, value);
                data.add(islandCount);
            }
        }
        con.close();
        return data;
    }
}

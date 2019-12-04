package hello.dao;

import hello.model.IslandCount;
import hello.model.TemperatureTrend;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

//CISE Oracle Server	sj3@//oracle.cise.ufl.edu:1521/orcl
@Repository
public class TemperatureTrendDao {
    @Autowired
    private DataSource dataSource;


    public List<TemperatureTrend> getTemperatureTrend()
            throws SQLException {
        List<TemperatureTrend> data = new ArrayList<>();
        Connection con = dataSource.getConnection();
        String query = "select years as everyyear,avg(Tvalue) as avgvalue, continent.continent_name\n" +
                "from( " +
                "   select " +
                "       country_code as ccode," +
                "       extract(year from temperature_date) as years," +
                "       temperature_value as Tvalue from temperature), country,  continent\n" +
                "where country.country_code=ccode and years > 1900 \n" +
                "and country.continent_id=continent.continent_id\n" +
                "group by years ,continent.continent_name\n" +
                "order by years";
        try (Statement stmt = con.createStatement()) {
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                Integer year = rs.getInt("everyyear");
                Double temperature = rs.getDouble("avgvalue");
                String continent = rs.getString("continent_name");
                TemperatureTrend temperatureTrend = new TemperatureTrend(year, temperature,continent);
                data.add(temperatureTrend);
            }
        }
        con.close();
        return data;
    }

}
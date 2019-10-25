package hello.dao;

import hello.model.IslandCount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

//CISE Oracle Server	sj3@//oracle.cise.ufl.edu:1521/orcl
@Repository
public class IslandDao {
    @Autowired
    private DataSource dataSource;
 //   private JdbcTemplate jdbcTemplate

    public List<IslandCount> getIslandCount()
            throws SQLException {
        List<IslandCount> data = new ArrayList<>();
        Connection con = dataSource.getConnection();
        String query = "    select \n" +
                "        c.name as country, \n" +
                "        count(i.island) as islandCount \n" +
                "            from geo_island i\n" +
                "                inner join country c on i.country = c.code\n" +
                "    group by c.name  \n" +
                "    order by count(i.island) desc \n" +
                "    OFFSET 0 ROWS FETCH NEXT 15 ROWS ONLY";
        try (Statement stmt = con.createStatement()) {
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                String country = rs.getString("country");
                Integer count = rs.getInt("islandCount");
                IslandCount islandCount = new IslandCount(country, count);
                data.add(islandCount);
            }
        }
        return data;
    }
}

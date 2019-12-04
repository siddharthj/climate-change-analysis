package hello.dao;

import hello.model.EmissionContribution;
import hello.model.TemperatureTrend;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

//CISE Oracle Server	sj3@//oracle.cise.ufl.edu:1521/orcl
@Repository
public class GreenHouseEmissionComparisonTrend {
    @Autowired
    private DataSource dataSource;
    public List<EmissionContribution> getEmissionTrend()
            throws SQLException {
        List<EmissionContribution> data = new ArrayList<>();
        Connection con = dataSource.getConnection();
        String query = "select GH1.EYEAR,\n" +
                "\t(gh1.emission_data *100)/gh2.emission_data as percentageContribuition\n" +
                "\tfrom\n" +
                "    \t(select\n" +
                "        \tdistinct gh.emission_year as EYEAR,\n" +
                "        \t(select sum(eamount) from " +
                "                   (select emission_amount AS eamount " +
                "                   from greenhouse_gas_emission where emission_year = gh.emission_year " +
                "                   ORDER BY emission_amount DESC FETCH NEXT 3 ROWS ONLY) )as emission_data\n" +
                "    \tfrom greenhouse_gas_emission gh order by eyear desc)\n" +
                "        \tgh1,\n" +
                "    \t(select\n" +
                "        \tdistinct gh.emission_year as EYEAR,\n" +
                "        \t (select sum(emission_amount) AS Addition from greenhouse_gas_emission " +
                "               where emission_year = gh.emission_year )as emission_data\n" +
                "    \tfrom greenhouse_gas_emission gh order by eyear desc)\n" +
                "         \tgh2\n" +
                "\twhere gh1.EYEAR = gh2.EYEAR";
        try (Statement stmt = con.createStatement()) {
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                Integer year = rs.getInt("EYEAR");
                Double contribuition = rs.getDouble("percentageContribuition");
                EmissionContribution temperatureTrend = new EmissionContribution(year, contribuition);
                data.add(temperatureTrend);
            }
        }
        con.close();
        return data;
    }

}
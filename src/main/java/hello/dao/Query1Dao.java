package hello.dao;

public class Query1Dao {

    "select avg(temperature_value) AS \"AVERAGE TEMPERATURE\", (CAST(EXTRACT(YEAR FROM temperature_date)/10 as INTEGER)*10) AS \"DECADE\"\n"+
            "FROM temperature\n"+
            "group by CAST(EXTRACT(YEAR FROM temperature_date)/10 as INTEGER)*10\n"+
            "order by CAST(EXTRACT(YEAR FROM temperature_date)/10 as INTEGER)*10;"
}

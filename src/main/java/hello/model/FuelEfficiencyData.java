package hello.model;

public class FuelEfficiencyData {
    Integer year;
    String continent;

    Double value;


    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getContinent() {
        return continent;
    }

    public void setContinent(String continent) {
        this.continent = continent;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public FuelEfficiencyData(Integer year, String sector, Double value) {
        this.year = year;
        this.continent = sector;
        this.value = value;
    }
}

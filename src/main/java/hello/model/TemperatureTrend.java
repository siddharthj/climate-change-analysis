package hello.model;

public class TemperatureTrend {
    Integer year;
    Double temperatureValue;
    String continent;
    public String getContinent() {
        return continent;
    }

    public void setContinent(String continent) {
        this.continent = continent;
    }


    public TemperatureTrend(Integer year, Double temperatureValue, String continent) {
        this.year = year;
        this.temperatureValue = temperatureValue;
        this.continent = continent;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Double getTemperatureValue() {
        return temperatureValue;
    }

    public void setTemperatureValue(Double temperatureValue) {
        this.temperatureValue = temperatureValue;
    }
}

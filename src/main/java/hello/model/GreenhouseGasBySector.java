package hello.model;

public class GreenhouseGasBySector {
    Integer year;
    String sector;

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getSector() {
        return sector;
    }

    public void setSector(String sector) {
        this.sector = sector;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    Double value;

    public GreenhouseGasBySector(Integer year, String sector, Double value) {
        this.year = year;
        this.sector = sector;
        this.value = value;
    }
}

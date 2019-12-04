package hello.model;

public class EmissionContribution {
    Integer year;

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Double getPercentageContribution() {
        return percentageContribution;
    }

    public void setPercentageContribution(Double percentageContribution) {
        this.percentageContribution = percentageContribution;
    }

    Double percentageContribution;

    public EmissionContribution(Integer year, Double percentageContribution) {
        this.year = year;
        this.percentageContribution = percentageContribution;
    }
}

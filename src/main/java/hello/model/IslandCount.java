package hello.model;

public class IslandCount {
    private String countryCode;
    private Integer islandCount;

    public IslandCount(String countryCode, Integer islandCount) {
        this.countryCode = countryCode;
        this.islandCount = islandCount;
    }


    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public Integer getIslandCount() {
        return islandCount;
    }

    public void setIslandCount(Integer islandCount) {
        this.islandCount = islandCount;
    }
}

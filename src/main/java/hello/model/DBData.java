package hello.model;

public class DBData {
    public DBData(String tableName, Integer rowCount) {
        this.rowCount = rowCount;
        this.tableName = tableName;
    }

    public Integer getRowCount() {
        return rowCount;
    }

    public void setRowCount(Integer rowCount) {
        this.rowCount = rowCount;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    Integer rowCount;
    String tableName;
}

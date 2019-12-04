package hello.controller;

import hello.dao.DBDataDao;
import hello.model.DBData;
import hello.model.TemperatureTrend;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.List;

@RestController
public class DBDataDataController {
    @Autowired
    private DBDataDao dbDataDao;

    @GetMapping("/dbdata")
    public List<DBData> about(@RequestParam(name = "name", required = false, defaultValue = "World") String name, Model model)
            throws SQLException {
        return dbDataDao.getSectorData();
    }
}
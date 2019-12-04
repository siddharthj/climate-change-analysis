package hello.controller;

import hello.dao.TemperatureTrendDao;
import hello.model.IslandCount;
import hello.model.TemperatureTrend;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.List;

@RestController
public class TemperatureTrendDataController {
    @Autowired
    private TemperatureTrendDao temperatureTrendDao;

    @GetMapping("/tempTrenddata")
    public List<TemperatureTrend> about(@RequestParam(name = "name", required = false, defaultValue = "World") String name, Model model)
            throws SQLException {
        return temperatureTrendDao.getTemperatureTrend();
    }
}
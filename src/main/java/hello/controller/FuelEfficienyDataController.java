package hello.controller;

import hello.dao.FuelDao;
import hello.dao.TemperatureTrendDao;
import hello.model.FuelEfficiencyData;
import hello.model.TemperatureTrend;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class FuelEfficienyDataController {
    @Autowired
    private FuelDao temperatureTrendDao;

    @GetMapping("/fuelefficiency")
    public List<FuelEfficiencyData> about(@RequestParam(name = "name", required = false, defaultValue = "World") String name, Model model)
            throws SQLException {
        return temperatureTrendDao.getFuelData().stream().filter(x -> x.getYear() <=2010 && x.getYear() >= 1975).collect(Collectors.toList());
    }
}
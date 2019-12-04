package hello.controller;

import hello.dao.GreenHouseEmissionComparisonTrend;
import hello.model.EmissionContribution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class EmissionTrendDataController {
    @Autowired
    private GreenHouseEmissionComparisonTrend gr;

    @GetMapping("/emissionTrendData")
    public List<EmissionContribution> about(@RequestParam(name = "name", required = false, defaultValue = "World") String name, Model model)
            throws SQLException {
        return gr.getEmissionTrend().stream().filter(x -> x.getYear() <=2012 && x.getYear() >= 1975).collect(Collectors.toList());
    }
}
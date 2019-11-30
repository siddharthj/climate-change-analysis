package hello.controller;

import hello.dao.IslandDao;
import hello.model.IslandCount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.List;

@RestController
public class D3TestDataController {
    @Autowired
    private IslandDao islandDao;

    @GetMapping("/d3testdata")
    public List<IslandCount> about(@RequestParam(name = "name", required = false, defaultValue = "World") String name, Model model)
            throws SQLException {
        return islandDao.getIslandCount();
    }
}
package hello.controller;

import hello.dao.IslandDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.SQLException;
import java.util.ArrayList;

@Controller
public class WelcomeController {
    @Autowired
    private IslandDao islandDao;
    @GetMapping("/welcome")
    public String about(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) throws SQLException {
        //List<IslandCount> islandCounts = islandDao.getIslandCount();
        model.addAttribute("islandCounts", new ArrayList<>());
        return "welcome";
    }

}
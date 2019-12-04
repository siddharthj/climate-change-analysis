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
public class Query1Controller {
    @Autowired
    private IslandDao islandDao;
    @GetMapping("/query1")
    public String about(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) throws SQLException {

        return "query1";
    }

}
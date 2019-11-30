package hello.controller;

import hello.dao.IslandDao;
import hello.model.IslandCount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.SQLException;
import java.util.List;

@Controller
public class D3TestController {

    @GetMapping("/d3test")
    public String about(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) throws SQLException {
        return "d3test";
    }

}
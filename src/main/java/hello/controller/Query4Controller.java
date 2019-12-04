package hello.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.SQLException;

@Controller
public class Query4Controller {
    @GetMapping("/query4")
    public String about(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) throws SQLException {
        return "query4";
    }
}
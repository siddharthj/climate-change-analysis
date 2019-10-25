package hello;

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
public class AboutController {
    @Autowired
    private IslandDao islandDao;
    @GetMapping("/about")
    public String about(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) throws SQLException {
        List<IslandCount> islandCounts = islandDao.getIslandCount();
        model.addAttribute("islandCounts", islandCounts);
        return "about";
    }

}
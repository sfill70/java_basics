package main;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;

/*@RestController
public class DefaultController {

    @RequestMapping("/")
    public String index(){
        return  LocalDate.now().toString();
    }
}*/

@Controller
public class DefaultController {
    private static Clock clock = new Clock(LocalDate.now().toString(), "Московское время");;

    @RequestMapping("/")
    public String index(Model model) {
        model.addAttribute("date", clock.date);
        model.addAttribute("clock", clock);

        return "index" /*LocalDate.now().toString()*/;
    }
}
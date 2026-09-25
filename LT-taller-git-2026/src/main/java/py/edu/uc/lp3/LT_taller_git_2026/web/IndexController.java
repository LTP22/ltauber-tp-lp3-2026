package py.edu.uc.lp3.LT_taller_git_2026.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {

    @GetMapping("/")
    public String index() {
        return "API REST de Armas de Counter-Strike 2";
    }
}

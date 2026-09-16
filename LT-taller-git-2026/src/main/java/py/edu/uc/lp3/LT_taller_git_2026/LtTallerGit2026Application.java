package py.edu.uc.lp3.lt_taller_git_2026;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class LtTallerGit2026Application {

	public static void main(String[] args) {
		SpringApplication.run(LtTallerGit2026Application.class, args);
	}

	@GetMapping("/")
	public String index() {
		return "Hola mundo";
	}

}

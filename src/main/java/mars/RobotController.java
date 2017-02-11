package mars;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RobotController {

    @PostMapping("/rest/mars")
    public @ResponseBody Robot robotPostEmpty() {
        return new Robot("");
    }

    @GetMapping("/rest/mars")
    public @ResponseBody Robot robotGetEmpty() {
        return new Robot("");
    }

    @PostMapping("/rest/mars/{command}")
    public @ResponseBody ResponseEntity<Robot> robotPostCommand(@PathVariable(value="command") final String command) {
		if (command.equals("X"))
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		else
			return ResponseEntity.ok(new Robot(command));
    }

}

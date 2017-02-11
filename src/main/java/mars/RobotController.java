package mars;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RobotController {

    @PostMapping("/rest/mars/{command}")
    public @ResponseBody Robot robotPostCommand(@PathVariable(value="command") final String command) {
        return new Robot(command);
    }

    @PostMapping("/rest/mars")
    public @ResponseBody Robot robotPostEmpty() {
        return new Robot("");
    }

    @GetMapping("/rest/mars")
    public @ResponseBody Robot robotGetEmpty() {
        return new Robot("");
    }

}

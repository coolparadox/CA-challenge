package mars;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RobotController {

    @RequestMapping("/rest/mars/{command}")
    public @ResponseBody Robot robot(@PathVariable(value="command") final String command) {
        return new Robot(command);
    }

    @RequestMapping("/rest/mars")
    public @ResponseBody Robot robot() {
        return new Robot("");
    }

}

package mars;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RobotController {

    @RequestMapping(path="/rest/mars", method=RequestMethod.GET, produces="text/plain")
    public @ResponseBody String robotPostNoCommand() {
        return new Robot().getPosition();
    }

    @RequestMapping(path="/rest/mars", method=RequestMethod.POST, produces="text/plain")
    public @ResponseBody String robotGetNoCommand() {
        return new Robot().getPosition();
    }

    @RequestMapping(path="/rest/mars/{command}", method=RequestMethod.POST, produces="text/plain")
    public @ResponseBody ResponseEntity<String> robotPostCommand(@PathVariable(value="command") final String command) {
		Robot robot = new Robot();
		robot.execute(command);
		if (robot.isLost())
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		else
			return ResponseEntity.ok(robot.getPosition());
    }

}

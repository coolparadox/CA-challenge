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

	static final String helpMessage = "Interface REST - Robô Marte ContaAzul\n"
		+ "\n"
		+ "Método: POST\n"
		+ "\n"
		+ "Comandos reconhecidos:\n"
		+ "- 'M' (move uma posição para frente)\n"
		+ "- 'L' (gira 90 graus para a esquerda)\n"
		+ "- 'R' (gira 90 graus para a direita)\n"
		+ "\n"
		+ "Os comandos podem ser concatenados em sequência, ex: MMRM\n"
		+ "\n"
		+ "A cada acesso um novo robô é inicializado na posição (0, 0) (sudoeste) do mapa,\n"
		+ "voltado para o norte. O tamanho do mapa é 5 x 5 posições.\n"
		+ "\n"
		+ "Após a execução dos comandos, é retornada a posição e orientação atuais do robô.\n"
		+ "\n"
		+ "Comandos desconhecidos não são permitidos.\n"
		+ "Sequências de comandos que levem o robô para fora do mapa não são permitidas.\n"
		+ "\n"
		+ "Rafael Lorandi\n"
		+ "http://github.com/coolparadox\n";

    @RequestMapping(path="/rest/mars", method=RequestMethod.GET, produces="text/plain")
    public @ResponseBody String robotGetNoCommand() {
        return helpMessage;
    }

    @RequestMapping(path="/rest/mars", method=RequestMethod.POST, produces="text/plain")
    public @ResponseBody String robotPostNoCommand() {
        return new Robot().getPosition();
    }

    @RequestMapping(path="/rest/mars/{commands}", method=RequestMethod.POST, produces="text/plain")
    public @ResponseBody ResponseEntity<String> robotPostCommand(@PathVariable(value="commands") final String commands) {
		Robot robot = new Robot();
		robot.execute(commands);
		if (robot.isLost())
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Bad Request\n");
		else
			return ResponseEntity.ok(robot.getPosition());
    }

}

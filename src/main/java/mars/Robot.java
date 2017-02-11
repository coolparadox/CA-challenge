package mars;

public class Robot {

	private final String command;

    public Robot(String command) {
        this.command = command;
    }

	public String getCommand() {
		return command;
	}

	public String getDestination() {
		return "(0, 0, N)";
	}
}

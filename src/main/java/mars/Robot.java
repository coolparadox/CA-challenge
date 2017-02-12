package mars;

public class Robot {

	private int posX;
	private int posY;
	private int orientation;
	private boolean isLost;

    public Robot() {
		this.posX = 0;
		this.posY = 0;
		this.orientation = 0;
		this.isLost = false;
    }

	public boolean isLost() {
		return this.isLost;
	}

	public String getPosition() {
		return "(0, 0, N)";
	}

	void execute(String command) {
		isLost = true;
	}

}

package mars;

public class Robot {

	// Hardcoded 5x5 grid limits
	static final int minX = 0;
	static final int minY = 0;
	static final int maxX = 4;
	static final int maxY = 4;

	// State variables
	private boolean mIsLost;
	private int mPosX;
	private int mPosY;
	private Orientation mOrientation;

	// Orientation values.
	// These perform robot's movement and turning.
	private enum Orientation {

		NORTH {

			// Walk North
			@Override
			public void stepForward(Robot robot) {
				if (robot.mPosY < maxY)
					robot.mPosY++;
				else
					// Uncharted territory
					robot.mIsLost = true;
			}

			// Face West
			@Override
			public void turnLeft(Robot robot) {
				robot.mOrientation = WEST;
			}

			// Face East
			@Override
			public void turnRight(Robot robot) {
				robot.mOrientation = EAST;
			}

			// Answer "N"
			@Override
			public String toString() {
				return "N";
			}

		},

		EAST {

			// Walk East
			@Override
			public void stepForward(Robot robot) {
				if (robot.mPosX < maxX)
					robot.mPosX++;
				else
					// Uncharted territory
					robot.mIsLost = true;
			}

			// Face North
			@Override
			public void turnLeft(Robot robot) {
				robot.mOrientation = NORTH;
			}

			// Face South
			@Override
			public void turnRight(Robot robot) {
				robot.mOrientation = SOUTH;
			}

			// Answer "E"
			@Override
			public String toString() {
				return "E";
			}

		},

		SOUTH {

			// Walk South
			@Override
			public void stepForward(Robot robot) {
				if (robot.mPosY > minY)
					robot.mPosY--;
				else
					// Uncharted territory
					robot.mIsLost = true;
			}

			// Face East
			@Override
			public void turnLeft(Robot robot) {
				robot.mOrientation = EAST;
			}

			// Face West
			@Override
			public void turnRight(Robot robot) {
				robot.mOrientation = WEST;
			}

			// Answer "S"
			@Override
			public String toString() {
				return "S";
			}

		},

		WEST {

			// Walk West
			@Override
			public void stepForward(Robot robot) {
				if (robot.mPosX > minX)
					robot.mPosX--;
				else
					// Uncharted territory
					robot.mIsLost = true;
			}

			// Face South
			@Override
			public void turnLeft(Robot robot) {
				robot.mOrientation = SOUTH;
			}

			// Face North
			@Override
			public void turnRight(Robot robot) {
				robot.mOrientation = NORTH;
			}

			// Answer "W"
			@Override
			public String toString() {
				return "W";
			}

		};

		// Make robot take a step forward.
		abstract public void stepForward(Robot robot);

		// Make robot turn left by 90 degrees.
		abstract public void turnLeft(Robot robot);

		// Make robot turn right by 90 degrees.
		abstract public void turnRight(Robot robot);

		// Answer a string identification about the orientation.
		abstract public String toString();

	}

	// Constructor.
	// Robot is initialized at position (0, 0) (left bottom) facing North.
    public Robot() {
		mPosX = 0;
		mPosY = 0;
		mOrientation = Orientation.NORTH;
		mIsLost = false;
    }

	// Answer if robot is lost.
	public boolean isLost() {
		return mIsLost;
	}

	// Answer a string representation of robot's position and orientation.
	public String getPosition() {
		return "(" + mPosX + ", " + mPosY + ", " + mOrientation.toString() + ")\n";
	}

	// Execute a sequence of move and turn commands.
	//
	// Parameter commands is a string comprised of letters:
	// - 'M' (move)
	// - 'L' (turn left)
	// - 'R' (turn right)
	// 
	// Robot is declared lost if taken outside map limits.
	// Robot is declared lost on receiving unknown commands.
	void execute(String commands) {
		for(char command : commands.toCharArray()) {
			if (mIsLost) {
				// Robot is lost.
				// Cease execution of further commands.
				return;
			}
			switch (command) {
				case 'M':
					// Move
					mOrientation.stepForward(this);
					break;
				case 'L':
					// Turn left
					mOrientation.turnLeft(this);
					break;
				case 'R':
					// Turn right
					mOrientation.turnRight(this);
					break;
				default:
					// Unknown command.
					// Declare robot is lost.
					mIsLost = true;
			}
		}
	}

}

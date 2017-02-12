package mars;

public class Robot {

	// Hardcoded grid limits
	static final int minX = 0;
	static final int minY = 0;
	static final int maxX = 5;
	static final int maxY = 5;

	// State variables
	private boolean mIsLost;
	private int mPosX;
	private int mPosY;
	private Orientation mOrientation;

	// Orientation objects.
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

		};

		// Make robot take a step forward
		abstract public void stepForward(Robot robot);

		// Make robot turn left by 90 degrees
		abstract public void turnLeft(Robot robot);

		// Make robot turn right by 90 degrees
		abstract public void turnRight(Robot robot);

	}

    public Robot() {
		mPosX = 0;
		mPosY = 0;
		mOrientation = Orientation.NORTH;
		mIsLost = false;
    }

	public boolean isLost() {
		return mIsLost;
	}

	public String getPosition() {
		return "(0, 0, N)";
	}

	void execute(String command) {
		mIsLost = true;
	}

}

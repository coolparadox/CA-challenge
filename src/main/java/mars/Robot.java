package mars;

public class Robot {

	private boolean mIsLost;
	private int mPosX;
	private int mPosY;
	private Orientation mOrientation;

	private enum Orientation {

		NORTH {
		},

		SOUTH {
		},

		EAST {
		},

		WEST {
		};

		abstract public void move(Robot parent);
		abstract public void left(Robot parent);
		abstract public void right(Robot parent);

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

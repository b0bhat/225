package rushhour.utility;

/**
 * This is the Vehicle class, in which following the read-in of the file from BoardReader
 * we then proceed to store the information about each car in a Vehicle object.
 * This object saves contains information for the name length, x-y position,
 * and orientation of the car. This id int is arbitrary as it is simply used to distinguish the target
 * car from the other traffic.
 */
public class Vehicle {
	
	public enum Direction {
		VERTICAL, HORIZONTAL
	}

	private int id, length;
	private String name;
	private Position position;
	public Direction direction;

	public Vehicle(int id, String name, int length, int row, int column, char direction) {
		this.id = id;
		this.name = name;
		this.length = length;
		setPosition(row, column);
		setDirection(direction);
	}

	public void setID(int id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public int getID() {
		return this.id;
	}

	public String getName() {
		return this.name;
	}

	public int getLength() {
		return this.length;
	}

	public Position getPosition() {
		return position;
	}

	public void setPosition(int row, int column) {
		position = new Position(row, column);
	}

	public void setDirection(char direction) {
		if (direction == 'h')
			this.direction = Direction.HORIZONTAL;
		else
			this.direction = Direction.VERTICAL;
	}

	public Direction getDirection() {
		return direction;
	}

	public String getInfo() {
		return (getID() + " " + getName() + " " + getLength() + " " + position.getRow() + " " + position.getColumn() + " " + getDirection());
	}

	public Vehicle createCopy() {
		char dir = this.direction == Direction.HORIZONTAL ? 'h' : 'v';
		return (new Vehicle(id, name, length, position.getRow(), position.getColumn(), dir));
	}

}

package rushhour.utility;

/**
 * Position.java simply stores the x-y coordinates of a
 * car, and is used in the Vehicle class. We opted to
 * create a separate class to reduce the clutter of
 * the vehicle class, but this class could easily be
 * merged into the vehicle class.
 */
public class Position {
	
	private int row, column;
	
	public Position(int row, int column) {
		this.row = row;
		this.column = column;
	}
	
	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getColumn() {
		return column;
	}

	public void setColumn(int column) {
		this.column = column;
	}	
	
}

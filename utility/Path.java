package rushhour.utility;

/**
 * Path.java saves the solution of the puzzle into a StringBuilder, which
 * is then later written into the output files using Path.getpath().
 * backTracePath() is the method in which traverses the LinkedList Nodes
 * of each board state and returns the move set in the correct order
 */
public class Path {
	
	private StringBuilder path;
	private int totalMoves;
	
	public Path(StringBuilder path, int moves) {
		this.path = path;
		this.totalMoves = moves;
	}
	
	public StringBuilder getPath() {
		return path;
	}

	public int getTotalMoves() {
		return totalMoves;
	}
	
	public static Path savePath(State finalState) {
		StringBuilder path = new StringBuilder();
		State temp = finalState.createCopy();
		int op = 0;
		while (temp != null) {
			if (temp.getMoveMade() != null) {
				op++;
				path.insert(0, temp.getMoveMade() + "\n");
			} temp = temp.getParent();
		} return new Path(path, op);
	}
	

}

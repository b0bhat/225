package rushhour;

import rushhour.utility.*;
import java.util.*;
import java.io.*;


/**
 * The solver class for the Rush Hour boardgame. This class is simply a driver class
 * piecing together the utility classes from rushhour/utility to solve the given
 * board using a Breadth-First Search algorithm. The algorithm returns the shortest
 * path found and the number of moves associated.
 */
public class Solver {
	
	public static Map<String, Boolean> visitedStates;

	public String inputPath;
	public String outputPath;

	public Solver(String inputPath, String outputPath) {
		this.inputPath = inputPath;
		this.outputPath = outputPath;
	}
	
	public static void solveFromFile(String inputPath, String outputPath) throws FileNotFoundException {

		BoardReader board = new BoardReader(inputPath);

		List<Vehicle> cars = board.getCarList();
		State startingState = new State(cars, 0);
		State result = BreadthFS.bfs(startingState, -1);
		if (result != null) {
			Path path = Path.savePath(result);
			//System.out.println("--- CURRENT DEPTH: " + result.getDepth());
			//System.out.println("--- TOTAL COST: " + path.getTotalCost());
			System.out.println(path.getPath());
			System.out.println("MOVES: " + path.getTotalMoves());

			//Writing solution to output path file
			try (PrintWriter out = new PrintWriter(outputPath)) {
				out.println(path.getPath());
				out.println("Moves: " + path.getTotalMoves());
			}
		} else
			try (PrintWriter out = new PrintWriter(outputPath)) {
				out.println("No Solution found");
			}
	}

}

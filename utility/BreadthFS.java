package rushhour.utility;
import rushhour.Solver;

import java.util.HashMap;
import java.util.LinkedList;

/**
 * The BreadthFS class uses an iterative method to solve the puzzle. We first
 * load the initial board state into a LinkedList Queue implementation, which
 * stores the states of our board. The first run adds the initial board state
 * to our Queue, which we then pop into a temporary State object and then by
 * using generateAvailableStates, we calculate all possible moves for all cars
 * on the RushHour board. Then we add the generated states back to the queue
 * and check if any of the states are a solution point with isFinal(). If no
 * solution is found, we then generateAvailableStates() for every state in the
 * queue. This will continue until either a solution is found or there are no
 * solutions, in which the queue will empty and queue.poll() returns null,
 * signalling that there is no solution for the board. the isFinal() method will
 * interpret this to our solver.
 */
public class BreadthFS {
	
	public static State bfs(State initial, int counter) {
		Solver.visitedStates = new HashMap<String, Boolean>();
		LinkedList<State> queue = new LinkedList<State>();
		queue.add(initial);
		while (!queue.isEmpty()) {
			State temp = queue.poll();
			if (temp.isFinal()) return temp;
			for (State state : State.generateAvailableStates(temp)) {
				state.setParent(temp);
				queue.add(state);
			}
		} return null;
	}

}

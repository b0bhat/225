package rushhour.utility;

import java.util.*;
import rushhour.Solver;

/**
 * State.java essentially creates objects that are represented as nodes
 * in the graph. For each possible pathway we create a State, and continue
 * on from there. Once a finished state is found we add it to a list of
 * solutions through addGeneratedStates, and then sort to the shortest solution which is then returned
 * through our algorithm.
 */
public class State {

	public static final int Size = 6;

	public enum moves {
		//  Up, Down, Left, Right
		U, D, L, R
	}

  public enum Direction {
		VERTICAL, HORIZONTAL
	}

	private State parent;
	private List<Vehicle> vehicles;
	private boolean[][] map;
	private int depth;
	private String moveMade;

	public State(List<Vehicle> vehicles, int depth) {
		if (depth == 0)
			this.parent = null;
		this.vehicles = vehicles;
		this.depth = depth;
		createMap();
	}

	public void setVehicles(List<Vehicle> vehicles) {
		this.vehicles = vehicles;
	}

	public void setParent(State parent) {
		this.parent = parent;
	}

	public State getParent() {
		return parent;
	}

	public List<Vehicle> getVehicles() {
		return vehicles;
	}

	public void setMoveMade(String move) {
		this.moveMade = move;
	}

	public String getMoveMade() {
		return moveMade;
	}

	public boolean[][] getMap() {
		return map;
	}

	public int getDepth() {
		return depth;
	}

	public void setDepth(int depth) {
		this.depth = depth;
	}


	public String createUniqueString(State state) {
		StringBuilder string = new StringBuilder();

		for (Vehicle vehicle : state.getVehicles())
			string.append(vehicle.getInfo());

		return string.toString();
	}

	public State createCopy() {

		List<Vehicle> newVehicles = new ArrayList<Vehicle>();

		for (Vehicle vehicle : getVehicles())
			newVehicles.add(vehicle.createCopy());

		State newState = new State(newVehicles, getDepth());
		newState.setParent(getParent());
		newState.setMoveMade(getMoveMade());

		return newState;
	}
  
  
	/**
	 * creates empty map and add vehicles
	 */
	private void createMap() {
		map = new boolean[Size][Size];

		for (int i = 0; i < Size; i++)
			for (int j = 0; j < Size; j++)
				map[i][j] = false;

		updateMap(vehicles);
	}
	

	public static List<State> generateAvailableStates(State initial) {

		List<State> newStates = new LinkedList<State>();

		for (Vehicle vehicle : initial.getVehicles()) {

			if (vehicle.getDirection() == Vehicle.Direction.HORIZONTAL) {

				// check left
				for (int i = vehicle.getPosition().getColumn() - 1, shift = 1; i >= 0; i--, shift++) {
					if (initial.getMap()[vehicle.getPosition().getRow()][i] == true)
						break;
					addGeneratedStates(initial, vehicle, newStates, shift, moves.L);
				}
				// check right
				for (int i = vehicle.getPosition().getColumn()
						+ vehicle.getLength(), shift = 1; i < Size; i++, shift++) {
					if (initial.getMap()[vehicle.getPosition().getRow()][i] == true)
						break;
					addGeneratedStates(initial, vehicle, newStates, shift, moves.R);
				}
			}

			else {
				// check up
				for (int i = vehicle.getPosition().getRow() - 1, shift = 1; i >= 0; i--, shift++) {
					if (initial.getMap()[i][vehicle.getPosition().getColumn()] == true)
						break;
					addGeneratedStates(initial, vehicle, newStates, shift, moves.U);
				}
				// check down
				for (int i = vehicle.getPosition().getRow()
						+ vehicle.getLength(), shift = 1; i < Size; i++, shift++) {
					if (initial.getMap()[i][vehicle.getPosition().getColumn()] == true)
						break;
					addGeneratedStates(initial, vehicle, newStates, shift, moves.D);
				}

			}
		}

		return newStates;
	}


	public boolean isFinal() {
		if (vehicles.get(0).getDirection() == Vehicle.Direction.HORIZONTAL
				&& vehicles.get(0).getPosition().getColumn() + vehicles.get(0).getLength() - 1 == (Size - 1))
			return true;
		else
			return false;
	}


	public void updateMap(List<Vehicle> vehicles) {
		for (Vehicle vehicle : vehicles) {
			
			if (vehicle.getDirection() == Vehicle.Direction.HORIZONTAL)
				for (int i = 0; i < vehicle.getLength(); i++)
					map[vehicle.getPosition().getRow()][vehicle.getPosition().getColumn() + i] = true;
			else
				for (int i = 0; i < vehicle.getLength(); i++)
					map[vehicle.getPosition().getRow() + i][vehicle.getPosition().getColumn()] = true;
			
		}
	}
	
	/*
	 * method creates possible move and checks, if it's already in hash table
	 */
	public static void addGeneratedStates(State initial, Vehicle vehicle, List<State> newStates, int shift,
			moves move) {

		State newState = initial.createCopy();
		Vehicle newVehicle = newState.getVehicles().get(vehicle.getID());

		switch (move) {
		case U:
			newVehicle.getPosition().setRow(newVehicle.getPosition().getRow() - shift);
			break;
		case D:
			newVehicle.getPosition().setRow(newVehicle.getPosition().getRow() + shift);
			break;
		case L:
			newVehicle.getPosition().setColumn(newVehicle.getPosition().getColumn() - shift);
			break;
		case R:
			newVehicle.getPosition().setColumn(newVehicle.getPosition().getColumn() + shift);
			break;
		}

		newState.createMap();
		newState.setDepth(initial.getDepth() + 1);
		newState.setMoveMade(newVehicle.getName() + move + shift);

		if (!Solver.visitedStates.containsKey(initial.createUniqueString(newState))) {
			Solver.visitedStates.put(initial.createUniqueString(newState), true);
			newStates.add(newState);
		}
	}

}

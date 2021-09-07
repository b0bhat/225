package rushhour.utility;

import java.util.*;
import java.io.*;

/**
 * This class is created for the sole purpose of reading the
 * board from the file and saving each car into a Vehicle object.
 * We then create an ArrayList of vehicles storing every car,
 * while also placing the target car into the first position
 * for use later in our algorithm.
 */
public class BoardReader {

  List<Vehicle> cars;

  public BoardReader(String filename) throws FileNotFoundException {
    this.cars = readfile(filename);
  }

  public List<Vehicle> readfile(String filename) throws FileNotFoundException {

      ArrayList<Integer> carNames = new ArrayList<Integer>();
      ArrayList<Integer> carSize = new ArrayList<Integer>();
      ArrayList<String> carOrient = new ArrayList<String>();
      ArrayList<Integer> x = new ArrayList<Integer>();
      ArrayList<Integer> y = new ArrayList<Integer>();
      int row = 0;

      System.out.println(filename);
      try {
      File file = new File(filename);
      Scanner reader = new Scanner(file); 
        while (reader.hasNextLine()) {
          String data = reader.nextLine();
          for(int column = 0; column < 6; column++) {
            int cur = data.charAt(column);
            System.out.print((char)cur + " ");
            if (cur != '.') {
              if (!carNames.contains(cur)) {
                carNames.add(cur);
                carSize.add(1);
                x.add(column);
                y.add(row);
                carOrient.add("v");
              } else {
                int index = carNames.indexOf(cur);
                carSize.set(index, carSize.get(index)+1);
                if (column != x.get(index))  carOrient.set(index, "h");
              }
            }
          } row++; System.out.println();
        } reader.close();
        row = 0;
      } catch (FileNotFoundException e) { throw e;}

    List<Vehicle> vehicles = new ArrayList<Vehicle>();
    int i;

    System.out.println("CARS: " + carNames.size());

    int ind = carNames.indexOf(88);
    System.out.println("X at ind " + ind);
    Vehicle car = new Vehicle(0, String.valueOf(Character.toChars(carNames.get(ind))), carSize.get(ind), y.get(ind), x.get(ind), carOrient.get(ind).charAt(0));
    vehicles.add(car);
    carNames.remove(ind);
    carSize.remove(ind);
    x.remove(ind);
    y.remove(ind);
    carOrient.remove(ind);

    for(i=0; i<carNames.size(); i++) {
      Vehicle car2 = new Vehicle(i+1, String.valueOf(Character.toChars(carNames.get(i))), carSize.get(i), y.get(i), x.get(i), carOrient.get(i).charAt(0));
      vehicles.add(car2);
    }

    //debug list all cars
    //for(i=0; i<carNames.size()+1; i++) System.out.println("id: " + vehicles.get(i).getID() + "Name:" + vehicles.get(i).getColor() + " Position: " + vehicles.get(i).getPosition().getColumn() + "," + vehicles.get(i).getPosition().getRow() + " Length: " + vehicles.get(i).getLength() + " Direction: " + vehicles.get(i).getDirection());

    return vehicles;
  }

  public List<Vehicle> getCarList() {
    return this.cars;
  }
}
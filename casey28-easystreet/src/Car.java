/*
 * TCSS 305 April 23, 2013 
 */

import java.util.Map;

/**
 * A vehicle that drives on streets only and stops at red lights.
 *
 * @author Casey Morrison
 * @version April 23, 2013
 */
public class Car extends AbstractVehicle {
  
  /** The time this vehicle stays dead. */
  private static final int DEATH_TIME = 10;
  
  /** The class name of this vehicle. */
  private static final String CLASS_NAME = "car";

  /**
   * Constructs a vehicle of type Car object.
   * The car has a starting point on the map and a direction it starts facing.
   * 
   * @param the_x - the initial x value on the map.
   * @param the_y - the initial y value on the map.
   * @param the_dir - the initial direction the vehicle is facing.
   */
  public Car(final int the_x, final int the_y, final Direction the_dir) {
    super(the_x, the_y, the_dir, CLASS_NAME, DEATH_TIME);
  }
  
  /**
   * Chooses the direction the vehicle prefers to travel.
   * 
   * @param the_neighbors - the map of neighboring terrains.
   * @param the_light - the status of the lights.
   * @return result - the direction the vehicle chooses to go.
   */
  @Override
  public Direction chooseDirection(final Map<Direction, Terrain> the_neighbors,
                                   final Light the_light) {
    Direction result = Direction.CENTER; 
    if (the_neighbors.get(getDirection()) == Terrain.LIGHT && the_light == Light.RED) {
      result = Direction.CENTER;
    } else if (the_neighbors.get(getDirection()) == Terrain.STREET 
        || the_neighbors.get(getDirection()) == Terrain.LIGHT) {                //go straight
      result = getDirection();
    } else if (the_neighbors.get(getDirection().left()) == Terrain.STREET 
               || the_neighbors.get(getDirection().left()) == Terrain.LIGHT) {  //go left
      result = getDirection().left();
    } else if (the_neighbors.get(getDirection().right()) == Terrain.STREET 
               || the_neighbors.get(getDirection().right()) == Terrain.LIGHT) { //go right
      result = getDirection().right();
    } else {
      result = getDirection().reverse();                                        //go back
    }

    return result;
  }
  
  /**
   * This method returns weather the terrain can be traversed to.
   * 
   * @param the_terrain - the terrain trying to pass.
   * @param the_light - the status of the lights.
   * @return result - value if the vehicle can pass.
   */
  @Override
  public boolean canPass(final Terrain the_terrain, final Light the_light) {
    boolean result = false;
    if (the_terrain == Terrain.STREET || the_terrain == Terrain.LIGHT) {
      result = true;
    }
    return result;
  }
  
  /**
   * Returns a String of the direction for debugging.
   * @return getDirection().toString() - for debugging.
   */
  @Override
  public String toString() {
    return getDirection().toString();
  }
}

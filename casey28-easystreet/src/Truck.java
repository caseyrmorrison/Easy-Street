/*
 * TCSS 305 April 23, 2013 
 */

import java.util.Map;

/**
 * A vehicle that does whatever it wants on the streets.
 *
 * @author Casey Morrison
 * @version April 23, 2013
 */
public class Truck extends AbstractVehicle {
  
  /** The time this vehicle stays dead. */
  private static final int DEATH_TIME = 0;
  
  /** The class name of this vehicle. */
  private static final String CLASS_NAME = "truck";

  /**
   * Constructs a vehicle of type truck.
   * The trucks has a starting point and direction on the map.
   * 
   * @param the_x - the initial x value on the map.
   * @param the_y - the initial y value on the map.
   * @param the_dir - the initial direction the vehicle is facing.
   */
  public Truck(final int the_x, final int the_y, final Direction the_dir) {
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
    Direction result = Direction.random();
    if (the_neighbors.get(result) != Terrain.STREET 
          || the_neighbors.get(result) != Terrain.LIGHT) {   //if it's not a street 
      result = Direction.random();                            //or light change it
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

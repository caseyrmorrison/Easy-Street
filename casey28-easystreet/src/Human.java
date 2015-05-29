/*
 * TCSS 305 April 23, 2013 
 */

import java.util.Map;

/**
 * A vehicle that moves on only it's starting terrain.
 *
 * @author Casey Morrison
 * @version April 23, 2013
 */
public class Human extends AbstractVehicle {
  
  /** The time this vehicle stays dead. */
  private static final int DEATH_TIME = 50;
  
  /** The class name of this vehicle. */
  private static final String CLASS_NAME = "human";
  
  /** The terrain used for travel. */
  private final Terrain my_terrain;
  
  /**
   * Constructs a vehicle of type human.
   * The human has an initial starting point and direction on the map.
   * The human also has an terrain that it will only travel on.
   * 
   * @param the_x - the initial x value on the map.
   * @param the_y - the initial y value on the map.
   * @param the_dir - the initial direction the vehicle is facing.
   * @param the_terrain - the terrain the human will travel on.
   */
  public Human(final int the_x, final int the_y, final Direction the_dir, 
               final Terrain the_terrain) {
    super(the_x, the_y, the_dir, CLASS_NAME, DEATH_TIME);
    my_terrain = the_terrain;
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
    if (the_neighbors.get(result) != my_terrain) {   //if it's not the same terrain
      result = Direction.random();
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
    if (the_terrain.toString().equals(my_terrain.toString())) {
      result = true;
    } else if (my_terrain == Terrain.LIGHT && the_terrain == Terrain.STREET) {
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

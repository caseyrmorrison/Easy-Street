/*
 * TCSS 305 April 23, 2013 
 */

import java.util.Map;

/**
 * A vehicle that rides on streets and trails.
 * Stops at red and yellow lights.
 *
 * @author Casey Morrison
 * @version April 23, 2013
 */
public class Bicycle extends AbstractVehicle {
  
  /** The time this vehicle stays dead. */
  private static final int DEATH_TIME = 20;
  
  /** The class name of this vehicle. */
  private static final String CLASS_NAME = "bicycle";

  /**
   * Constructs a vehicle of type bicycle.
   * The bicycle has an initial starting point and an initial direction.
   * 
   * @param the_x - the initial x value on the map.
   * @param the_y - the initial y value on the map.
   * @param the_dir - the initial direction the vehicle is facing.
   */
  public Bicycle(final int the_x, final int the_y, final Direction the_dir) {
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
    final Direction center = Direction.CENTER;

    if (the_neighbors.get(getDirection()) == Terrain.STREET 
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
    
    if (the_neighbors.get(getDirection()) == Terrain.LIGHT && the_light != Light.GREEN) {
      result = Direction.CENTER;
    } else if (the_neighbors.get(center) == Terrain.TRAIL 
        || the_neighbors.get(getDirection()) == Terrain.TRAIL) {      //looking for trails
      result = getDirection();
    } else if (the_neighbors.get(getDirection().left()) == Terrain.TRAIL) {
      result = getDirection().left();
    } else if (the_neighbors.get(getDirection().right()) == Terrain.TRAIL) {
      result = getDirection().right();
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
    if (the_terrain == Terrain.TRAIL || the_terrain == Terrain.STREET 
                                     || (the_terrain == Terrain.LIGHT
                                         && the_light == Light.GREEN)) {
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

/*
 * TCSS 305 April 28, 2013 
 */

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

/**
 * A test for class, Truck.
 *
 * @author Casey Morrison
 * @version April 28, 2013
 */
public class TruckTest {
  
  /**
   * The number of times to repeat a test to have a high probability that all
   * random possibilities have been explored.
   */
  private static final int TRIES_FOR_RANDOMNESS = 50;

  /**
   * Test method for {@link Truck#chooseDirection(java.util.Map, Light)}.
   */
  @Test
  public void testChooseDirection() {
    // trucks can do anything on streets and lights.

    // Trucks can not choose Center. Choosing Center would result in no
    // movement.

    final Map<Direction, Terrain> neighbors = new HashMap<Direction, Terrain>();
    neighbors.put(Direction.WEST, Terrain.WALL);
    neighbors.put(Direction.NORTH, Terrain.WALL);

    for (Terrain t : Terrain.values()) {
      if (t != Terrain.WALL) { // Humans don't start on Walls.
        neighbors.put(Direction.CENTER, t);
        neighbors.put(Direction.EAST, t);
        neighbors.put(Direction.SOUTH, t);
        final Truck truck = new Truck(0, 0, Direction.CENTER);
        for (Light l : Light.values()) {
          int tries = 0;
          boolean seen_south = false;
          boolean seen_east = false;
          while (tries < TRIES_FOR_RANDOMNESS && (!seen_south || !seen_east)) {
            tries = tries + 1;
            final Direction dir = truck.chooseDirection(neighbors, l);
            assertTrue("invalid dir chosen, should be east or south, was " + dir,
                       dir == Direction.EAST || dir == Direction.SOUTH);
            seen_south = seen_south || dir == Direction.SOUTH;
            seen_east = seen_east || dir == Direction.EAST;
          }
          assertTrue("truck randomness", seen_south && seen_east);
        }
      }
    }
  }

  /**
   * Test method for {@link Truck#canPass(Terrain, Light)}.
   */
  @Test
  public void testCanPass() {
    for (Terrain test_terrain : Terrain.values()) {
      final Truck truck = new Truck(0, 0, Direction.CENTER);
      for (Terrain t : Terrain.values()) {
        for (Light l : Light.values()) {
          if ((t == test_terrain) || (t == Terrain.LIGHT && test_terrain == Terrain.STREET)
              || (t == Terrain.STREET && test_terrain == Terrain.LIGHT)) {
            // trucks can go anywhere on a street or light
            
            assertTrue("Truck started on " + test_terrain + " should be able to pass " + t
                       + ", with light " + l, truck.canPass(t, l));
          } 
        }
      }
    }
  }
}

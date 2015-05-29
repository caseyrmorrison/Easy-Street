/*
 * TCSS 305 April 23, 2013 
 */

/**
 * The parent class used by the subclasses.
 * Implements vehicle.
 *
 * @author Casey Morrison
 * @version April 23, 2013
 */
public abstract class AbstractVehicle implements Vehicle {
  
  // Instance fields
  
  /** The x coordinate that the vehicle is at on the map. */
  private int my_x;
  
  /** The y coordinate that the vehicle is at on the map. */
  private int my_y;
  
  /** The original starting x coordinate of the vehicle on the map. */
  private final int my_original_x;
  
  /** The original starting y coordinate of the vehicle on the map. */
  private final int my_original_y;
  
  /** The amount of turns this vehicle will stay dead. */
  private final int my_death_time;
  
  /** The amount of turns left before this vehicle is revived. */
  private int my_dead_left;
  
  /** The image name for this alive vehicle. */
  private final String my_alive_image;
  
  /** The image name for this dead vehicle. */
  private final String my_dead_image;
  
  /** The value showing if this car is alive or dead. */
  private Boolean my_alive = true;
  
  /** The direction the vehicle is currently facing. */
  private Direction my_dir;
  
  /** The original direction this vehicle started at. */
  private final Direction my_original_dir;
  
  /**
   * This constructor constructs a vehicle from a sub class.
   * This initializes all the variables used in the AbstractVehicle class.
   * Cannot create an instance of this class object.
   * 
   * @param the_x - the initial x position.
   * @param the_y - the initial y position.
   * @param the_dir - the initial direction.
   * @param the_class - the class name.
   * @param the_death_time - the death time of the vehicle.
   */
  protected AbstractVehicle(final int the_x, final int the_y,
                            final Direction the_dir, final String the_class,
                            final int the_death_time) {
    my_x = the_x;
    my_y = the_y;
    my_original_x = the_x;
    my_original_y = the_y;
    my_dir = the_dir;
    my_original_dir = the_dir;
    my_alive_image = the_class + ".gif";
    my_dead_image = the_class + "_dead.gif";
    //my_alive = true;
    my_death_time = the_death_time;
    my_dead_left = 0;
  }

  /** {@inheritDoc} */
  @Override
  public void collide(final Vehicle the_other) {
    if (my_alive && the_other.isAlive() && the_other.getDeathTime() < my_death_time) {
      my_alive = false;
      my_dead_left = my_death_time;
    }
  }

  /** {@inheritDoc} */
  @Override
  public int getDeathTime() {
    return my_death_time;
  }

  /** {@inheritDoc} */
  @Override
  public String getImageFileName() {
    String image = null;
    if (my_alive) {
      image = my_alive_image;
    } else {
      image = my_dead_image;
    }
    return image;
  }

  /** {@inheritDoc} */
  @Override
  public Direction getDirection() {
    return my_dir;
  }

  /** {@inheritDoc} */
  @Override
  public int getX() {
    return my_x;
  }

  /** {@inheritDoc} */
  @Override
  public int getY() {
    return my_y;
  }

  /** {@inheritDoc} */
  @Override
  public boolean isAlive() {
    return my_alive;
  }

  /** {@inheritDoc} */
  @Override
  public void poke() {
    if (!my_alive) {
      if (my_dead_left == 0) {
        my_alive = true;
        setDirection(Direction.random());
      } else {
        my_dead_left = my_dead_left - 1;
      }
    }
  }

  /** {@inheritDoc} */
  @Override
  public void reset() {
    setX(my_original_x);
    setY(my_original_y);
    my_alive = true;
    setDirection(my_original_dir);
  }

  /** {@inheritDoc} */
  @Override
  public void setDirection(final Direction the_dir) {
    my_dir = the_dir;
  }

  /** {@inheritDoc} */
  @Override
  public void setX(final int the_x) {
    my_x = the_x;
  }

  /** {@inheritDoc} */
  @Override
  public void setY(final int the_y) {
    my_y = the_y;
  }
}

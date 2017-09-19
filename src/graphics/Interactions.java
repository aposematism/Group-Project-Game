package graphics;

import Exceptions.NotImplementedYetException;

/**Contains a bunch of miscellaneous static methods for managing collision,
 cell adjacency, proximity among other things.
 * Created by weirjosh on 19/09/17.
 */
public class Interactions {

    /**
     *Returns whether or not two sprites are touching each other.
     */
    static Boolean colliding(Sprite sprite1, Sprite sprite2){
        throw new NotImplementedYetException();
    }

    /**
     * returns true if two sprites are in adjacent cells
     * NOTE: assumes each sprite is using same Grid object!
     */
    static boolean adjacent(Sprite sprite1, Sprite sprite2){
        throw new NotImplementedYetException();
    }

    /**
     *Calculates euclidian distance between the center point of two sprites.
     */
    static float distance(Sprite sprite1, Sprite sprite2){
        throw new NotImplementedYetException();
    }

    /**
     * Check if two sprites are within the same cell
     * NOTE: assumes each sprite is using same Grid object!
     */
    static boolean sameCell(Sprite sprite1, Sprite sprite2){
        throw new NotImplementedYetException();
    }
}

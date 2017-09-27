package com.swen.herebethetitle.graphics;

import com.swen.herebethetitle.entity.Entity;
import com.swen.herebethetitle.exceptions.NotImplementedYetException;

/**Contains a bunch of miscellaneous static methods for managing collision,
 cell adjacency, proximity among other things.
 * Created by weirjosh on 19/09/17.
 */

/*TODO This class will need to undergo a multitude of changes, if it even is to remain in this library
  TODO I will probably start paying attention to this when we need to worry about proper collision detection

TODO Perhaps a similar class could go into the utilities library...?
 */
public class Interactions {


    /**
     *Returns whether or not two sprites are touching each other.
     */
    static Boolean colliding(Entity entity1, Entity entity2){
        throw new NotImplementedYetException();
    }

    /**
     * returns true if two sprites are in adjacent cells
     * NOTE: assumes each sprite is using same GridManager object!
     */
    static boolean adjacent(Entity entity1, Entity entity2){
        throw new NotImplementedYetException();
    }

    /**
     *Calculates euclidian distance between the center point of two sprites.
     */
    static float distance(Entity entity1, Entity entity2){
        throw new NotImplementedYetException();
    }

    /**
     * Check if two sprites are within the same cell
     * NOTE: assumes each sprite is using same GridManager object!
     */
    static boolean sameCell(Entity entity1, Entity entity2){
        throw new NotImplementedYetException();
    }
}

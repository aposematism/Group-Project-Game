package com.swen.herebethetitle.graphics;

import com.swen.herebethetitle.entity.Entity;
import com.swen.herebethetitle.exceptions.NotImplementedYetException;
import javafx.scene.image.*;

/**Contains a bunch of miscellaneous static methods for managing collision,
 cell adjacency, proximity among other things.
 * Created by weirjosh on 19/09/17.
 */

/*TODO This class will need to undergo a multitude of changes, if it even is to remain in this library
  TODO I will probably start paying attention to this when we need to worry about proper collision detection

TODO Perhaps a similar class could go into the utilities library...?
 */
public class Actions {

    /**
     * Constructs JavaFX image from source url
     * @param url Sprite File URL
     * @return JavaFX Image
     */
    public static Image createImage(String url){
        return new Image(url);
    }
}

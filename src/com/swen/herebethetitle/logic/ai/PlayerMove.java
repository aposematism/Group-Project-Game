package com.swen.herebethetitle.logic.ai;

import java.util.Optional;

import com.swen.herebethetitle.entity.Player;
import com.swen.herebethetitle.logic.Notifier;
import com.swen.herebethetitle.model.Region;
import com.swen.herebethetitle.model.Tile;
import com.swen.herebethetitle.pathfinding.Graph;
import com.swen.herebethetitle.pathfinding.Path;

import javafx.scene.input.MouseEvent;


/**
 * Interaction to move the player.
 * @author J Woods
 *
 */
public class PlayerMove implements Interaction {
	
	public Player player;
	public Tile dest;

	@Override
	public void tick(Region region, Notifier notifier) throws InteractionOver {
		/*find optimal path*/
        Graph graph = new Graph(region, region.getPlayerTile(), dest);
        Optional<Path> optimalPath = graph.findPath();
        
        if(!optimalPath.isPresent()) {
        	//no optimal path exists, do nothing
        	throw new InteractionOver();
        }
        
        /*Move the player*/
        if(!(region.getPlayerTile()==dest)) {	//don't move if we're already there
        	region.move(player, optimalPath.get().next());
        }else {
        	throw new InteractionOver();
        }
	}

	@Override
	public boolean isSameAs(Interaction interaction) {
        if(!(interaction instanceof PlayerMove)) {
        	return false;
        }
        if(((PlayerMove)interaction).dest==this.dest) {
        	return true;
        }
        return false;
	}

}

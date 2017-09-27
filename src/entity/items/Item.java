package entity.items;

import entity.Entity;
import entity.Player;
import javafx.scene.image.Image;
import model.GameContext;
import util.GridLocation;

/**
 * Item - can be picked up by the player and stored in their inventory,
 * and then can be interacted with. Its actions are defined in an actions interface.
 *
 * Created by Mark on 19/09/17.
 */
public class Item extends Entity {

	private Actions actions;

	public Item(GameContext context, GridLocation spawnLocation, Image sprite){
		super(context, spawnLocation, sprite);
	}

	public void interact(GameContext context) {
		if(player().possesses(this))
			actions.use(this);
		else if(player().getLocation().adjacent(this.getLocation()))
			actions.pickup(this);
	}

	public void setActions(Actions actions){
		this.actions = actions;
	}

	public interface Actions {
		void pickup(Item item);
		void use(Item item);
		String toString();
	}

	@Override
	public String toString() {
		return null;
	}

	@Override
	public boolean isPenetrable(){
		return true;
	}
}

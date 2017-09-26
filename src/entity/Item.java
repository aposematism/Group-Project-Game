package entity;

import javafx.scene.image.Image;
import util.GridLocation;

/**
 * Item - can be picked up by the player and stored in their inventory,
 * and then can be interacted with. Its actions are defined in an actions interface.
 *
 * Created by Mark on 19/09/17.
 */
public class Item implements Entity {

	private Player player;
	private GridLocation location;
	private Image sprite;

	private Actions actions;

	public Item(Player player, GridLocation spawnLocation, Image sprite){
		this.player = player;
		this.location = spawnLocation;
		this.sprite = sprite;
	}

	public void interact(Player player) {
		if(player.possesses(this))
			actions.use(this);
		else if(player.getLocation().adjacent(this.getLocation()))
			actions.pickup(this);
	}

	public void setActions(Actions actions){
		this.actions = actions;
	}

	public GridLocation getLocation() {
		return location;
	}

	public void setLocation(GridLocation location){
		this.location = location;
	}

	public Player getPlayer(){
		return player;
	}

	public Image getSprite(){
		return sprite;
	}

	interface Actions {
		void pickup(Item item);
		void use(Item item);
	}
}

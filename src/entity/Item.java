package entity;

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

	private Actions actions;

	public Item(Player player, GridLocation spawnLocation){
		this.player = player;
		this.location = spawnLocation;
	}

	public void interact(Player player) {
		//TODO
	}

	public void setActions(Actions actions){
		this.actions = actions;
	}

	public void pickup(){
		actions.pickup(this);
	}

	public void use(){
		actions.use(this);
	}

	public boolean playerPossesses(){
		//TODO
		return false;
	}

	public GridLocation getLocation() {
		return location;
	}

	public Player getPlayer(){
		return player;
	}

	interface Actions {
		void pickup(Item item);
		void use(Item item);
	}
}

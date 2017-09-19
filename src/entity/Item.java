package entity;

import graph.Node;

/**
 * Item - can be picked up by the player and stored in their inventory,
 * and then can be interacted with. Its actions are defined in an actions interface.
 *
 * Created by Mark on 19/09/17.
 */
public class Item implements Entity {

	private Player player;
	private Node position;

	private Actions actions;

	public Item(Player player, Node spawnPos){
		this.player = player;
		this.position = spawnPos;
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

	public Node getPosition() {
		return position;
	}

	public Player getPlayer(){
		return player;
	}

	interface Actions {
		void pickup(Item item);
		void use(Item item);
	}
}

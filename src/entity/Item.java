package entity;

import graph.Node;

/**
 * Created by metcalmark on 19/09/17.
 */
public abstract class Item implements Entity {

	Player player;
	Node pos;

	public Item(Player player, Node spawnPos){
		this.player = player;
		this.pos = spawnPos;
	}

	public void interact(Player player) {
		//TODO
	}

	boolean playerPossesses(){
		//TODO
		return false;
	}

	abstract void pickup();

	abstract void use();

}

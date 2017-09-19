package entity;

import graph.Node;

/**
 * Created by metcalmark on 19/09/17.
 */
public class NPC extends Mob {

	private Actions actions;

	public NPC(Node spawnPos, int startingHealth, Direction direction){
		super(spawnPos, startingHealth, direction);
	}

	public void setActions(Actions actions){
		this.actions = actions;
	}

	public void move(){
		actions.move(this);
	}

	public void attack(){
		actions.attack(this);
	}

	public void die(){
		actions.die(this);
	}

	@Override
	public void interact(Player player) {
		actions.receiveAttack(this);
	}

	interface Actions {
		void move(NPC npc);
		void attack(NPC npc);
		void receiveAttack(NPC npc);
		void die(NPC npc);
	}
}

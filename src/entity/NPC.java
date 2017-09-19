package entity;

import graph.Node;

/**
 * Created by metcalmark on 19/09/17.
 */
public class NPC extends Mob {

	private Type strategy;

	public NPC(Node spawnPos, int startingHealth, FacingDirection direction){
		super(spawnPos, startingHealth, direction);
	}

	public void move(){
		strategy.move(this);
	}

	public void attack(){
		strategy.attack(this);
	}

	public void die(){
		strategy.die(this);
	}

	@Override
	public void interact(Player player) {
		strategy.receiveAttack(this);
	}

	interface Type {
		void move(NPC npc);
		void attack(NPC npc);
		void receiveAttack(NPC npc);
		void die(NPC npc);
	}
}

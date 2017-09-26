package entity;

import javafx.scene.image.Image;
import util.Direction;
import util.GridLocation;

/**
 * NPC - Non Player Character - will be used for representing monsters and potentially other types of NPC
 * Uses strategy pattern for deciding its actions.
 *
 * Created by Mark on 19/09/17.
 */
public class NPC extends Mob {

	private Actions actions;

	public NPC(GridLocation spawnPos, int startingHealth, Direction direction, Image sprite){
		super(spawnPos, startingHealth, direction, sprite);
	}

	public void setActions(Actions actions){
		this.actions = actions;
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
		void attack(NPC npc);
		void receiveAttack(NPC npc);
		void die(NPC npc);
	}
}

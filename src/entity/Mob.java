package entity;

import graph.Node;

/**
 * Created by metcalmark on 19/09/17.
 */
public abstract class Mob implements Entity {

	enum FacingDirection {
		LEFT,
		UP,
		RIGHT,
		DOWN
	}

	private final int startingHealth;
	private int health;

	Node position;
	FacingDirection direction;

	public Mob(Node spawnPos, int startingHealth, FacingDirection direction){
		this.position = spawnPos;
		this.startingHealth = startingHealth;
		this.health = startingHealth;
		this.direction = direction;
	}

	public void interact(Player player) {
		//TODO
	}

	Node getPosition(){
		return position;
	}

	FacingDirection getDirection(){
		return direction;
	}

	void setDirection(FacingDirection direction){
		this.direction=direction;
	}

	int getHealth(){
		return health;
	}

	void damage(int amount){
		health = health-amount<0 ? 0 : health-amount;
	}

	void heal(int amount){
		health = health+amount>startingHealth ? startingHealth : health+amount;
	}
}

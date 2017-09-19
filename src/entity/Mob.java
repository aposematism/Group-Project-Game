package entity;

import graph.Node;

/**
 * Created by metcalmark on 19/09/17.
 */
public abstract class Mob implements Entity {

	private final int startingHealth;
	private int health;

	private Node position;
	private Direction direction;

	public Mob(Node spawnPos, int startingHealth, Direction direction){
		this.position = spawnPos;
		this.startingHealth = startingHealth;
		this.health = startingHealth;
		this.direction = direction;
	}

	public void interact(Player player) {
		//TODO
	}

	public Node getPosition(){
		return position;
	}

	public Direction getDirection(){
		return direction;
	}

	public void setDirection(Direction direction){
		this.direction=direction;
	}

	public int getHealth(){
		return health;
	}

	public void damage(int amount){
		health = health-amount<0 ? 0 : health-amount;
	}

	public void heal(int amount){
		health = health+amount>startingHealth ? startingHealth : health+amount;
	}
}

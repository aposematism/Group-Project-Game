package entity;

import graph.Node;

/**
 * Created by Mark on 19/09/2017.
 */
public class Weapon extends Item {

	final double STRENGTH;

	private Action action;

	public Weapon(Player player, Node spawnPos, double strength){
		super(player, spawnPos);
		this.STRENGTH = strength;
	}

	public void attack(){
		action.attack(this);
	}

	interface Action {
		void attack(Weapon weapon);
	}

	public void setAction(Action newAction){
		this.action=newAction;
	}

	void pickup() {
		//TODO
	}

	void use() {
		//TODO
	}
}

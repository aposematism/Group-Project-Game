package com.swen.herebethetitle.entity.items;

import com.swen.herebethetitle.model.GameContext;
import javafx.scene.image.Image;

/**
 * Created by Mark on 30/09/2017.
 */
public final class Potion extends Item {

	private final int HEALTH_CHANGE;

	public Potion(Image sprite, int healthChange){
		super(sprite);
		this.HEALTH_CHANGE = healthChange;
	}

	@Override
	public void use(GameContext context) {
		if(HEALTH_CHANGE>0){
			context.player.heal(HEALTH_CHANGE);
		} else if(HEALTH_CHANGE<0){
			context.player.damage(HEALTH_CHANGE);
		}
		super.use(context);
	}

	@Override
	public String toString() {
		return getClass().getName()+" "+HEALTH_CHANGE;
	}
}

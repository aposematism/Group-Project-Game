package com.swen.herebethetitle.entity;
import com.swen.herebethetitle.util.Direction;
import com.swen.herebethetitle.model.GameContext;
import com.swen.herebethetitle.logic.Notifier;
/**
 * Created by milburjord on 16/10/17.
 */
public class RegionDoor extends Entity{
    private Direction direction;

    public RegionDoor(String name, String spritePath, Direction dir){
        super(name, spritePath);
        this.direction = dir;
    }

    public void interact(GameContext context, Notifier notifier){

    }

    public boolean isPenetrable(){
        return true;
    }

    public String toString(){
        return "";
    }
}

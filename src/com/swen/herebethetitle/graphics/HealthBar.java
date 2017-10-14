package com.swen.herebethetitle.graphics;

import com.swen.herebethetitle.entity.Mob;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.*;
import javafx.scene.paint.Color;

public class HealthBar {

    Stop[] frontStops = new Stop[] { new Stop(0, Color.RED), new Stop(1, Color.DARKRED)};

    public HealthBar(){
    }

    public void draw(int x, int y, double health, GraphicsContext gc, int width, int height){
        double t = (health - Mob.NO_HEALTH)/(Mob.FULL_HEALTH-Mob.NO_HEALTH);
        System.out.println(t);
        double newWidth = (x + t*((x+width)-x)) - x;

        gc.setFill(Color.DARKGREY);
        gc.setFill(new Color(0.3,0.3,0.3,0.5));
        gc.fillRect(x,y,width, height);
        gc.setFill(new LinearGradient(0,0, 0,1, true, CycleMethod.NO_CYCLE, frontStops));
        gc.fillRect(x,y,newWidth, height);
        gc.setLineWidth(4);
        gc.setStroke(Color.BLACK);
        gc.strokeRect(x,y,width, height);
    }
}

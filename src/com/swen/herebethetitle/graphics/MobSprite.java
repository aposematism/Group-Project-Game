package com.swen.herebethetitle.graphics;

import com.swen.herebethetitle.util.GridLocation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.SnapshotParameters;
import javafx.scene.effect.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.util.Duration;

import javax.management.timer.Timer;

public class MobSprite extends Sprite {

    Image damaged;
    Image fine = img;

    public MobSprite(Image i, GridLocation l){
        super(i,l);
    }

    /**
     * Colours the sprite image Red for a brief amount of time.
     */
    public void damage(){
        ImageView imView = new ImageView(img);

        Lighting l = new Lighting();
        l.setLight(new Light.Distant(60,60,Color.RED));

        imView.setEffect(l);
        SnapshotParameters params = new SnapshotParameters();
        params.setFill(Color.TRANSPARENT);

        damaged = imView.snapshot(params, null);
        setDamaged();

        Timeline t = new Timeline(new KeyFrame(Duration.millis(300), e -> setFine()));
        t.setCycleCount(1);
        t.play();
    }

    private void setDamaged(){
        img = damaged;
    }
    private void setFine(){
        img = fine;
    }
}

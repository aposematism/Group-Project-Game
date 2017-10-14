package com.swen.herebethetitle.graphics;

import com.swen.herebethetitle.util.GridLocation;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.awt.*;

public class TextBox extends Sprite {
    protected String text;

    protected final int WIDTH;
    protected final int HEIGHT;

    protected final double FONTSIZE = 20;
    protected final String FONT = "Courier New";
    protected final int MAX_CHARS;

    public TextBox(Image i, GridLocation l, String t, int width, int height){
        img = i;
        loc = l;
        WIDTH = width;
        HEIGHT = height;

        MAX_CHARS = (int)(WIDTH*1.5/FONTSIZE);

        StringBuilder splitString = new StringBuilder(t);
        for(int j=0;j<splitString.length();j++) {
            if (j % MAX_CHARS == 0 && j > 0) {

                int k = j;
                char c = splitString.charAt(k);
                while(c != ' ' && k-1 > 0){
                    k--; c = splitString.charAt(k);
                }
//                if(j-1 > 0){
//                    j--;
//                }
                splitString.setCharAt(k, '\n');
            }
        }
        text = splitString.toString();
    }

    @Override
    public void draw(GraphicsContext gc, GridManager g, Point offset){}

    @Override
    /**
     * Draw this sprite to the given canvas.
     * @param gc the GraphicsContext used to render the image
     * @param g The gridManager for positioning
     * @author weirjosh
     */
    public void draw(GraphicsContext gc, GridManager g){
        Point pos = g.getRealCoordinates(loc);

        gc.setFill(Color.WHITE);
        gc.fillRect(pos.x, pos.y, WIDTH, HEIGHT);

        gc.setFill(Color.BLACK);
        gc.setFont(new Font(FONT, FONTSIZE));
        gc.fillText(text,pos.x+(g.getCellSize()/2), pos.y+(g.getCellSize()/2));
        gc.setStroke(Color.GREY);
        gc.strokeRect(pos.x, pos.y, WIDTH, HEIGHT);

        gc.drawImage(img, pos.x-(g.getCellSize()/2), pos.y-(g.getCellSize()/2),
                g.getCellSize(), g.getCellSize());
    }

}

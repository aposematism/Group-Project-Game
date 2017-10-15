package com.swen.herebethetitle.graphics;

import com.sun.deploy.util.StringUtils;
import com.swen.herebethetitle.util.GridLocation;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.web.HTMLEditor;

import javax.swing.text.html.HTML;
import java.awt.*;

public class TextBox extends Sprite {
    protected String text;
    protected String name;

    protected final int WIDTH;
    protected final int HEIGHT;

    protected final double FONTSIZE = 20;
    protected final String FONT = "Courier New";
    protected final int MAX_CHARS;

    public TextBox(Image im, GridLocation l, String t, int width, int height, String name){
        img = im;
        loc = l;
        WIDTH = width;

        this.name = name + ":";

        MAX_CHARS = (int)(WIDTH*1.5/FONTSIZE);
        System.out.println(MAX_CHARS);

        t = "\n\n"+t;
        StringBuilder splitString = new StringBuilder(t);
        int i = 0;
        int j = 0;
        while(i < splitString.length()){
            j++;
            if(j >= MAX_CHARS){
                int k = i;
                while(splitString.charAt(k) != ' ' && k-1 >= 0){
                    k--;
                }
                splitString.setCharAt(k, '\n');
                j = 0;
            }
            i++;
        }
        text = splitString.toString();
        int newLines = getOcurances(text, '\n');

        HEIGHT = (3+newLines)*(int)FONTSIZE;
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
        gc.setFont(Font.font(FONT, FontWeight.BOLD, FONTSIZE));
        gc.fillText(name, pos.x+(g.getCellSize()/2), pos.y+(g.getCellSize()/2));

        gc.setFont(Font.font(FONT, FONTSIZE));
        gc.fillText(text,pos.x+(g.getCellSize()/2), pos.y+(g.getCellSize()/2));

        gc.setStroke(Color.GREY);
        gc.strokeRect(pos.x, pos.y, WIDTH, HEIGHT);

        gc.drawImage(img, pos.x-(g.getCellSize()*1.3/2), pos.y-(g.getCellSize()*1.3/2),
                g.getCellSize()*1.3, g.getCellSize()*1.3);
    }


    private int getOcurances(String s, char occurance){
        int i = 0;
        char [] characters = s.toCharArray();
        for(char c: characters){
            if(c == occurance){
                i++;
            }
        }
        return i;
    }

}

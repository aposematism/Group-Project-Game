package com.swen.herebethetitle.graphics;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;

public class ToolTip {
    private static double HEIGHT;
    private static Color COLOR;
    private static Font FONT;
    private static Color FONT_COLOR;

    public ToolTip(){
        COLOR = new Color(0,0,0,0.5);
        FONT = Font.font("Courier New", 11);
        FONT_COLOR = Color.WHITE;
        HEIGHT = FONT.getSize()*1.5;
    }



    public void draw(int x, int y, String text, GraphicsContext gc){
        double width = text.length()*(FONT.getSize()/1.63);

        gc.setFill(COLOR);
        gc.fillRect(x,y,width, HEIGHT);
        gc.setFill(FONT_COLOR);
        gc.setFont(FONT);
        gc.fillText(text, x,y+FONT.getSize());
    }
}

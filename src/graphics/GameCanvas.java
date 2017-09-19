package graphics;

import Exceptions.NotImplementedYetException;
import javafx.scene.canvas.Canvas;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


/**
 * This may come useful later on, wrapper class for canvas containing
 * only information relevant for drawing game objects.
 */
public class GameCanvas extends Canvas {
    Grid grid;
    Set<Sprite> sprites = new HashSet<>();

    public GameCanvas(Grid gr, int width, int height){
        super(width, height);
        grid = gr;
    }

    public boolean addSprite(Sprite s){
        throw new NotImplementedYetException();
    }

    public Sprite removeSprite(Sprite s){
        throw new NotImplementedYetException();
    }

    public Set<Sprite> getSprites(){
        throw new NotImplementedYetException();
    }

    public Grid getGrid(){
        throw new NotImplementedYetException();
    }

    public void drawAll(){

    }

    public void drawSprite(Sprite s){
        throw new NotImplementedYetException();
    }
}

package com.swen.herebethetitle.parser;

import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * Data class that stores the x and y coordinates representing the tile position of an entity
 *
 * @author Mark Metcalfe
 */
public final class Coord {

    /**
     * X and Y coordinate values
     */
    public int x, y;

    private final static Pattern COORDS = Pattern.compile("\\(\\d,\\d\\)");

    private Coord(int x, int y){this.x = x; this.y = y;}

    /**
     * Takes a scanner input and parses a coordinate from it
     *
     * @param s A scanner with a (x,y) token next
     * @return an instance of Coord with the intepreted x and y values
     */
    public static Coord parseCoordinate(Scanner s){
        String coordBraces = s.findInLine(COORDS);

        coordBraces = coordBraces.replaceAll("\\(", "");
        coordBraces = coordBraces.replaceAll("\\)", "");
        coordBraces = coordBraces.replaceAll(",", " ");

        Scanner coord = new Scanner(coordBraces);
        return new Coord(coord.nextInt(), coord.nextInt());
    }

    @Override
    public String toString() { return "("+x+","+y+")";}
}

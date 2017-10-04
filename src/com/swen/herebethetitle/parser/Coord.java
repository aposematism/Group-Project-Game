package com.swen.herebethetitle.parser;

/**
 * Created by metcalmark on 4/10/17.
 */
public final class Coord {
    public int x, y;
    public Coord(int x, int y){this.x = x; this.y = y;}
    public String toString() { return "("+x+","+y+")";}
}

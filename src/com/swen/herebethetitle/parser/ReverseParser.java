package com.swen.herebethetitle.parser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import com.swen.herebethetitle.entity.Entity;
import com.swen.herebethetitle.model.Region;
import com.swen.herebethetitle.model.Tile;

public class ReverseParser {
	
	ArrayList<String> interactives = new ArrayList<String>();;

	public void pullInteractives(Tile[][] ra) {
		interactives = new ArrayList<String>();
		for(int i = 0; i < ra.length; i++) {
			for(int j = 0; j < ra[0].length; j++) {
				pullString(i, j , ra[i][j]);
			}
		}
	}
	
	private void pullString(int i, int j, Tile t) {
		for(Entity ent : t.getInteractives()) {
			String concat = "("+ i +","+ j +") "+ ent.toString();
			interactives.add(concat);
		}
		
	}
	
	private void pullString(ArrayList<Entity> entArray) {
		for(Entity ent : entArray) {
			interactives.add(ent.toString());
		}
	}
	
	private boolean writeTofile(Region r) throws FileNotFoundException, UnsupportedEncodingException {
		String fileName = r.getRegionName()+"currentstate.text";
		try {
			PrintWriter pw = new PrintWriter(fileName, "UTF-8");
			for(String s : interactives) {
				pw.println(s);
			}
		}
		catch(FileNotFoundException e) {
			
		}
		catch(UnsupportedEncodingException q) {
			
		}
		
		return false;
	}
}

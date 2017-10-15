package com.swen.herebethetitle.parser;

import com.swen.herebethetitle.entity.Entity;
import com.swen.herebethetitle.model.Region;
import com.swen.herebethetitle.model.Tile;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;

public class ReverseParser {
	
	ArrayList<Entity> interactives = new ArrayList<Entity>();
	ArrayList<String> entityOutput = new ArrayList<String>();
	HashMap<String, String> characterMap = new HashMap<String, String>();
	Region r;
	String[] alphabet = "abcdefghijklmnopqrstuvwxyz".split("");

	public ReverseParser(File region) throws IOException, InputMismatchException {
		reverseScanner(region);
	}

	public ReverseParser(Region reg, File file) {
		r = reg;
		parseRegion(reg, file.getPath().replace(file.getName(), ""));
	}
	
	/** 
	 * Scanner for taking files directly. Primarily for testing purposes, 
	 * but also for not creating unnecessarily files if no items have moved. Primarily used for loading.
	 * @throws IOException SyntaxError 
	 * */
	private void reverseScanner(File region) throws IOException, InputMismatchException {
		interactives = new ArrayList<Entity>();
		entityOutput = new ArrayList<String>();
		BufferedReader regionBuff = null;
		try{
			regionBuff = new BufferedReader(new FileReader(region));
			String line = regionBuff.readLine();
			while(line != null){
				if(line.contains("map:")) {
					pullEntities(r);
				}
				line = regionBuff.readLine();
			}
			regionBuff.close();
		}
		catch(IOException e){
			System.out.println("I/O exception: " + e.toString());
			throw new FileNotFoundException("File failed to initialise!");
		}
	}
	
	/** 
	 * This classes saves all the data from a region into a correct file. Primarily used for saving.
	 * */
	public void parseRegion(Region r, String path) {
		pullEntities(r);
		String fileName = path + r.getRegionName() + "currentstate.txt";
		try {
			writeToFile(fileName);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}

	/** 
	 * This pulls entities from the region.
	 * */
	private void pullEntities(Region r) {
		for(int i = 0; i < r.getXSize(); i++) {
			for(int j = 0; j < r.getYSize(); j++) {
				pullEntities(i, j , r);
			}
		}
	}
	
	private void pullEntities(int i, int j, Region r) {
		Tile t = r.get(i,j);
		for(Entity ent : t.getInteractives()) {
			if(characterMap.containsKey(t.getCharacter())) {//check if you have that entity
				if(!characterMap.get(t.getCharacter()).equals(ent.toString())) {//make sure the ent output matches.
					for(int k = 0; k < alphabet.length; k++) {
						if(!characterMap.containsKey(alphabet[k])){
							if(t.getMapFloor() != null) {
								//characterMap.put(alphabet[k], t.getMapFloor().toString() + " + " + ent.toString());
								break;
							}
							else {
								characterMap.put(alphabet[k], ent.toString());
								break;
							}
							
						}
					}
				}
			}
			else {//otherwise add it to the map.
				if(t.getMapFloor() != null) {
					characterMap.put(t.getCharacter(), t.getMapFloor().toString() + " + " + ent.toString());
				}
				else {
					characterMap.put(t.getCharacter(), ent.toString());
				}
			}
		}
	}
	
	
	public File writeToFile(String fileName) throws FileNotFoundException, UnsupportedEncodingException {
 		File outputFile = null;
		try {
			outputFile = new File(fileName);
			BufferedWriter pw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outputFile), "UTF-8"));
			pw.write("this: " + r.getRegionName()); pw.newLine();
			pw.write("north: " + r.getNeighbouringRegions()[0]); pw.newLine();
			pw.write("east: " + r.getNeighbouringRegions()[1]); pw.newLine();
			pw.write("south: " + r.getNeighbouringRegions()[2]); pw.newLine();
			pw.write("west: " + r.getNeighbouringRegions()[3]); pw.newLine();
			pw.newLine();
			pw.write("entities:");
			pw.newLine();
			for(String s : characterMap.keySet()) {
				pw.write(s + " = " + characterMap.get(s)); 
				pw.newLine();
			}
			pw.newLine();
			pw.newLine();
			pw.write("map:");
			pw.newLine();
			for (int row = 0; row < r.getYSize(); row++) {
				for (int col = 0; col < r.getXSize(); col++) {
					pw.write(r.get(col, row).getCharacter()); //col = x, row = y
				}
				pw.newLine();
			}
			pw.close();
			System.out.printf("File is located at %s%n", outputFile.getAbsolutePath());
		}
		catch(FileNotFoundException e) {
			e.printStackTrace();
		}
		catch(UnsupportedEncodingException q) {
			q.printStackTrace();
		}
		catch(IOException z) {
			z.printStackTrace();
		}
		return outputFile;
	}
	
	public ArrayList<Entity> getInteractiveList() {
		return interactives;
	}
	
	public ArrayList<String> getOutputList(){
		return entityOutput;
	}
}

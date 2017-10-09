package com.swen.herebethetitle.parser;

import com.swen.herebethetitle.entity.Entity;
import com.swen.herebethetitle.model.Region;
import com.swen.herebethetitle.model.Tile;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class ReverseParser {
	
	static ArrayList<Entity> interactives = new ArrayList<Entity>();
	static ArrayList<Coord> coordinates = new ArrayList<Coord>();
	static ArrayList<String> output = new ArrayList<String>();
	
	public ReverseParser(File region) throws IOException, SyntaxError {
		reverseScanner(region);
	}
	
	/** 
	 * Scanner for taking files directly. Primarily for testing purposes, 
	 * but also for not creating unnecessarily files if no items have moved. Primarily used for loading.
	 * @throws IOException SyntaxError 
	 * */
	private static void reverseScanner(File region)throws IOException, SyntaxError{
		interactives = new ArrayList<Entity>();
		output = new ArrayList<String>();
		coordinates = new ArrayList<Coord>();
		BufferedReader regionBuff = null;
		try{
			regionBuff = new BufferedReader(new FileReader(region));
			String line = regionBuff.readLine();
			while(line != null){
				Scanner s = new Scanner(line);
				Coord c = Coord.parseCoordinate(s);
				coordinates.add(c);
				EntityParser ep = new EntityParser(s);
				Entity ent = ep.parseEntity(s);
				interactives.add(ent);
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
	public static void parseRegion(Region r) {
		interactives = new ArrayList<Entity>();
		pullInteractives(r);
		String fileName = r.getRegionName()+"currentstate.text";
		try {
			writeToFile(fileName);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}

	private static void pullInteractives(Region r) {
		for(int i = 0; i < r.getXSize(); i++) {
			for(int j = 0; j < r.getYSize(); j++) {
				pullString(i, j , r);
			}
		}
	}
	
	private static void pullString(int i, int j, Region r) {
		Tile t = r.get(i,j);
		for(Entity ent : t.getInteractives()) {
			String concat = "("+ i +","+ j +") "+ ent.toString();
			output.add(concat);
		}
	}
	
	/** 
	 * Left public for testing.
	 * */
	public static void pullString(ArrayList<Entity> entArray) {
		for(int i = 0; i < interactives.size(); i++) {
			String concat = coordinates.get(i).toString();
			concat = concat + " " + interactives.get(i).toString();
			output.add(concat);
		}
	}
	
	public static File writeToFile(String fileName) throws FileNotFoundException, UnsupportedEncodingException {
		File outputFile = new File(fileName);
		try {
			
			BufferedWriter pw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outputFile), "UTF-8"));
			for(String s : output) {
				pw.write(s);
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
	
	public static ArrayList<Entity> getInteractiveList() {
		return interactives;
	}
	
	public static ArrayList<String> getOutputList(){
		return output;
	}
}

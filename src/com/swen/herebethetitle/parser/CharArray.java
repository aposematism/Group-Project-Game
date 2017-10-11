package com.swen.herebethetitle.parser;

import java.util.ArrayList;

/**
 * Container class for the character array used by the MapParser
 *
 * @author Mark Metcalfe
 */
public class CharArray {

	private ArrayList<Character[]> charArray = new ArrayList<>();

	public void addLine(String[] in) {
		if (in.length < 2) return;
		Character[] chars = new Character[in.length];
		for (int i = 0; i < chars.length; i++)
			chars[i] = in[i].charAt(0);
		charArray.add(chars);
	}

	public Character get(int row, int col) {
		return charArray.get(row)[col];
	}

	public int width() {
		return charArray.get(0).length;
	}

	public int height() {
		return charArray.size();
	}
}

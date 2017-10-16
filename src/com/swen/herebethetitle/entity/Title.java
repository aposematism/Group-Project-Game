package com.swen.herebethetitle.entity;

public class Title extends Item {
	public Title(String name, String sprite) {
		super(name, sprite);
	}

	public Title clone() throws CloneNotSupportedException {
		return new Title(getName(), getSpritePath());
	}
}
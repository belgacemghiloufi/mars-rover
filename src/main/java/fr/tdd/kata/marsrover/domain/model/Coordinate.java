package fr.tdd.kata.marsrover.domain.model;

public class Coordinate {
	
	private final int xPos;
	private final int yPos;
	
	public Coordinate(int xPos, int yPos) {
		this.xPos = xPos;
		this.yPos = yPos;
	}

	public int getXPos() {
		return xPos;
	}

	public int getYPos() {
		return yPos;
	}
	
}

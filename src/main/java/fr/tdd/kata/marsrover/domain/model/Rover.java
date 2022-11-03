package fr.tdd.kata.marsrover.domain.model;

public class Rover {

	private static final int MAX_WIDTH = 10;
	private static final int MAX_HEIGHT = 10;
	
	private String direction;
	private int xPos;
	private int yPos;

	public Rover(String direction, int xPos, int yPos) {
			this.direction = direction;
			this.xPos = xPos;
			this.yPos = yPos;
	}

	public String execute(String commands) {
		for (char command : commands.toCharArray()) {
				if(command == 'R')
					direction = rotateRight();
				if(command == 'L')
					direction = rotateLeft();
				if(command == 'M')
					move();
		}
		
		return xPos + " " + yPos + " " + direction;
	}

	private String rotateLeft() { 
		if(direction == "N") return "W";
		if(direction == "W") return "S";
		if(direction == "S") return "E";
		return "N";
	}	
	
	private String rotateRight() { 
		if(direction == "N") return "E";
		if(direction == "E") return "S";
		if(direction == "S") return "W";
		return "N";
	}
	
	private void move() {
		if(direction == "N")
			yPos = (yPos + 1) % MAX_HEIGHT;
		if(direction == "E")
			xPos = (xPos + 1) % MAX_WIDTH;
		if(direction == "S")
			 yPos = (yPos > 0) ? yPos - 1 : MAX_HEIGHT - 1;
		if(direction == "W")
			xPos = (xPos > 0) ? xPos - 1 : MAX_WIDTH - 1;
	}
	
}

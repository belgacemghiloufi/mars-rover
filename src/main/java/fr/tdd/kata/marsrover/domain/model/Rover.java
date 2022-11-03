package fr.tdd.kata.marsrover.domain.model;

public class Rover {
	
	private static final char MOVE_FORWARD = 'M';
	private static final char TURN_LEFT = 'L';
	private static final char TURN_RIGHT = 'R';
	
	private final Grid grid;
	private Coordinate coordinate;
	private Direction direction;

	public Rover(Grid grid, Coordinate coordinate, Direction direction) {
			this.grid = grid;
			this.direction = direction;
			this.coordinate = coordinate;
	}

	public String execute(String commands) {
		for (char command : commands.toCharArray()) {
				if(command == TURN_RIGHT) 
					direction = direction.right();
				if(command == TURN_LEFT) 
					direction = direction.left();
				if(command == MOVE_FORWARD)
					coordinate = grid.nextCoordinateFor(coordinate, direction);
		}
		return coordinate.getXPos() + " " + coordinate.getYPos() + " " + direction.getValue();
	}
	
}
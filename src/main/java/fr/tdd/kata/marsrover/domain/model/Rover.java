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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((coordinate == null) ? 0 : coordinate.hashCode());
		result = prime * result + ((direction == null) ? 0 : direction.hashCode());
		result = prime * result + ((grid == null) ? 0 : grid.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Rover other = (Rover) obj;
		if (coordinate == null) {
			if (other.coordinate != null) {
				return false;
			}
		} else if (!coordinate.equals(other.coordinate)) {
			return false;
		}
		if (direction != other.direction) {
			return false;
		}
		if (grid == null) {
			if (other.grid != null) {
				return false;
			}
		} else if (!grid.equals(other.grid)) {
			return false;
		}
		return true;
	}
	
}
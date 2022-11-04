package fr.tdd.kata.marsrover.domain.model;

import static fr.tdd.kata.marsrover.domain.model.Direction.EAST;
import static fr.tdd.kata.marsrover.domain.model.Direction.NORTH;
import static fr.tdd.kata.marsrover.domain.model.Direction.SOUTH;
import static fr.tdd.kata.marsrover.domain.model.Direction.WEST;

public class Grid {

	private final int maxWidth;
	private final int maxHeight;

	public Grid(int maxWidth, int maxHeight) {
		this.maxWidth = maxWidth;
		this.maxHeight = maxHeight;
	}

	public Coordinate nextCoordinateFor(Coordinate coordinate, Direction direction) {
		int xPos = coordinate.getXPos();
		int yPos = coordinate.getYPos();
		if (direction == NORTH)
			yPos = (yPos + 1) % maxHeight;
		if (direction == EAST)
			xPos = (xPos + 1) % maxWidth;
		if (direction == SOUTH)
			yPos = (yPos > 0) ? yPos - 1 : maxHeight - 1;
		if (direction == WEST)
			xPos = (xPos > 0) ? xPos - 1 : maxWidth - 1;
		return new Coordinate(xPos, yPos);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + maxHeight;
		result = prime * result + maxWidth;
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
		Grid other = (Grid) obj;
		if (maxHeight != other.maxHeight) {
			return false;
		}
		if (maxWidth != other.maxWidth) {
			return false;
		}
		return true;
	}

}

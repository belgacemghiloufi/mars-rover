package fr.tdd.kata.marsrover.domain.model;

import java.util.stream.Stream;

public enum Direction {

	NORTH("N", "W", "E"), SOUTH("S", "E", "W"), EAST("E", "N", "S"), WEST("W", "S", "N");

	private final String value;
	private final String left;
	private final String right;

	Direction(String value, String left, String right) {
		this.value = value;
		this.left = left;
		this.right = right;
	}
	
	public String getValue() {
		return value;
	}

	Direction right() {
		return directionMatching(right);
	}

	Direction left() {
		return directionMatching(left);
	}

	Direction directionMatching(String value) {
		return Stream.of(values())
				   .filter(direction -> direction.value == value)
				   .findFirst()
				   .get();
	}
	
	public static Direction of(String value) {
		for(Direction direction: values()) {
			if(direction.value.equals(value)) {
				return direction;
			}
		}
		return null;
	}

}
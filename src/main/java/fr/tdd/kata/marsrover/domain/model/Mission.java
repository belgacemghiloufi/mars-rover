package fr.tdd.kata.marsrover.domain.model;

public class Mission {

	private Rover rover;
	private String missionInstructions;

	public Mission(Rover rover, String missionInstructions) {
		this.rover = rover;
		this.missionInstructions = missionInstructions;
	}
	
	public String start() {
		return rover.execute(missionInstructions);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((missionInstructions == null) ? 0 : missionInstructions.hashCode());
		result = prime * result + ((rover == null) ? 0 : rover.hashCode());
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
		Mission other = (Mission) obj;
		if (missionInstructions == null) {
			if (other.missionInstructions != null) {
				return false;
			}
		} else if (!missionInstructions.equals(other.missionInstructions)) {
			return false;
		}
		if (rover == null) {
			if (other.rover != null) {
				return false;
			}
		} else if (!rover.equals(other.rover)) {
			return false;
		}
		return true;
	}


}

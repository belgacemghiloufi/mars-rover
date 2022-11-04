package fr.tdd.kata.marsrover.adapters;

import static fr.tdd.kata.marsrover.adapters.FileMissionInstructionReader.MISSION_PARAMETER_COUNT;
import static fr.tdd.kata.marsrover.adapters.FileMissionInstructionReader.SPACE_DELIMITER;

import java.util.List;
import java.util.function.Consumer;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import fr.tdd.kata.marsrover.domain.model.Grid;

public class FileMissionInstructionValidator {

	public void validateCommands(String commands) {
		final List<Character> knownCommands = List.of('M', 'L', 'R');
		boolean valid = commands.chars()
													 .mapToObj( command -> (char) command)
													 .allMatch(command -> knownCommands.contains(command));
		if (!valid)
			throw new IllegalArgumentException(String.format("unknown command: %s command should be: M or L or R", commands));
	}

	public void validateCoordinate(String coordinate) {
		final Pattern pattern = Pattern.compile("\\d+");
		boolean valid = pattern.matcher(coordinate).matches();
		if (!valid)
			throw new IllegalArgumentException(String.format("invalid coordinate: %s a coordinate should a positive natural number", coordinate));
	}

	public void validateCoordinate(String coordinate, String maxCoordinate) {
		validateCoordinate(coordinate);
		validateCoordinate(maxCoordinate);
		if (Integer.parseInt(coordinate) > Integer.parseInt(maxCoordinate))
			throw new IllegalArgumentException(String.format("invalid coordinate: %s a coordinate should not be greater than the grid size %s", coordinate, maxCoordinate));
	}

	public void validateDirection(String direction) {
		final List<String> knownDirections = List.of("N", "S", "E", "W");
		if(!knownDirections.contains(direction)) {
			throw new IllegalArgumentException(String.format("unknown direction: %s direction should be: N or S or E or W", direction));
		}
	}

	public void validatePosition(String position, String maxWidthCoordinate, String maxHeightCoordinate) {
		final String[] positionAsArray = position.split(SPACE_DELIMITER);
		int positionParameterCount = 3;
		if(positionAsArray.length != positionParameterCount)
			throw new IllegalArgumentException(String.format("invalid position for rover: %s position should be as such: 1 2 N", position));
		validateCoordinate(positionAsArray[0], maxWidthCoordinate);
		validateCoordinate(positionAsArray[1], maxHeightCoordinate);
		validateDirection(positionAsArray[2]);
	}

	public void validateMissionInstructions(String instructions, String fileLocation) throws Exception {
		if((instructions == null) || (instructions.isEmpty()))
			throw new IllegalArgumentException(String.format("invalid mission instructions: %s", instructions));
		String gridSizeLine = instructions.lines()
						  .filter(line -> !line.isEmpty())
						  .findFirst()
						  .get();
		validateGridSize(gridSizeLine);
		final Grid grid = new FileMissionInstructionReader().getGrid(fileLocation);
		int firstLine = 1;
		List<String> instructionsAsList = instructions.lines()
																		  .filter(line -> !line.isEmpty())
																		  .skip(firstLine)
																		  .collect(Collectors.toList());
		if(instructionsAsList.size() % MISSION_PARAMETER_COUNT != 0)
			throw new IllegalArgumentException(String.format("invalid mission instructions: %s unable to distribute instruction over rovers", instructions));
		
		Consumer<List<String>> missionValidator = list -> {
																			 String maxWidth = String.valueOf(grid.getMaxWidth());
																			 String maxHeight = String.valueOf(grid.getMaxHeight());
																			 String position = list.get(0);
																			 validatePosition(position, maxWidth, maxHeight);
																			 String commands = list.get(1);
																			validateCommands(commands);};
		FileMissionInstructionReader.partition(instructionsAsList, MISSION_PARAMETER_COUNT)
												    .forEach(missionValidator);
	}

	public void validateGridSize(String gridSizeLine) {
		final String[] gridSizeLineAsArray = gridSizeLine.split(SPACE_DELIMITER);
		if(gridSizeLineAsArray.length != FileMissionInstructionReader.GRID_PARAMETER_COUNT)
			throw new IllegalArgumentException(String.format("invalid grid size: %s grid size should be as such: 5 5", gridSizeLine));
		validateCoordinate(gridSizeLineAsArray[0]);
		validateCoordinate(gridSizeLineAsArray[1]);
		if((Integer.parseInt(gridSizeLineAsArray[0]) == 0) || (Integer.parseInt(gridSizeLineAsArray[1]) == 0))
			throw new IllegalArgumentException(String.format("invalid grid size: %s grid size should be greater than zero", gridSizeLine));
	}

	public void validateArgements(String[] args) {
		if(args == null || args.length == 0)
			throw new IllegalArgumentException(String.format("no argements are provided mission instructions are required to start the mars exploration"));
	}

}

package fr.tdd.kata.marsrover.adapters;

import static java.nio.charset.StandardCharsets.UTF_8;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import fr.tdd.kata.marsrover.domain.model.Coordinate;
import fr.tdd.kata.marsrover.domain.model.Direction;
import fr.tdd.kata.marsrover.domain.model.Grid;
import fr.tdd.kata.marsrover.domain.model.Mission;
import fr.tdd.kata.marsrover.domain.model.Rover;
import fr.tdd.kata.marsrover.domain.ports.MissionInstructionReader;

public class FileMissionInstructionReader implements MissionInstructionReader {

	public static final int GRID_PARAMETER_COUNT = 2;
	public static final int MISSION_PARAMETER_COUNT = 2;
	public static final String SPACE_DELIMITER = " ";
	private final FileMissionInstructionValidator instructionValidator;
	
	public FileMissionInstructionReader() {
		this.instructionValidator = new FileMissionInstructionValidator();
	}

	public FileMissionInstructionReader(FileMissionInstructionValidator instructionValidator) {
		this.instructionValidator = instructionValidator;
	}

	@Override
	public String getMissionInstructions(String fileLocation) throws Exception {
		return Files.readString(Paths.get(fileLocation), UTF_8);
	}

	@Override
	public Grid getGrid(String fileLocation) throws Exception {
		Grid result = null;
		String gridSizeLine = Files.lines(Paths.get(fileLocation))
									   .filter(line -> !line.isEmpty())
									   .findFirst()
									   .get();
		String gridSizeAsArray[] = gridSizeLine.split(SPACE_DELIMITER);
		if (gridSizeAsArray.length == GRID_PARAMETER_COUNT) {
			int maxHeight = Integer.parseInt(gridSizeAsArray[1]) + 1;
			int maxWidth = Integer.parseInt(gridSizeAsArray[0]) + 1;
			result = new Grid(maxWidth, maxHeight);
		}
		return result;
	}

	@Override
	public List<Mission> getMissions(String fileLocation) throws Exception {
		
		instructionValidator.validateMissionInstructions(getMissionInstructions(fileLocation), fileLocation);
		
		final Grid grid = getGrid(fileLocation);
		final int firstLine = 1;
		List<String> instructions = Files.lines(Paths.get(fileLocation))
						                              .filter(line -> !line.isEmpty())
						                              .skip(firstLine)
						                              .collect(Collectors.toList());
		List<List<String>> instructionsAsList = partition(instructions, MISSION_PARAMETER_COUNT);
		List<Mission>  result = constructMissions(grid, instructionsAsList);
		return Collections.unmodifiableList(result);
	}

	private List<Mission> constructMissions(Grid grid, List<List<String>> instructionsAsList) {
		return instructionsAsList.stream()
								  .map(convertToMission(grid))
								  .collect(Collectors.toList());
	}

	private Function<List<String>, Mission> convertToMission(Grid grid) {
		return missionAsList -> {
					String roverInitialPosition = missionAsList.get(0);
					String missionInstructions = missionAsList.get(1);
					String[] roverInitialPositionAsArray = roverInitialPosition.split(SPACE_DELIMITER);
					int xPos = Integer.parseInt(roverInitialPositionAsArray[0]);
					int yPos = Integer.parseInt(roverInitialPositionAsArray[1]);
					Coordinate coordinate = new Coordinate(xPos, yPos);
					Direction direction = Direction.of(roverInitialPositionAsArray[2]);
					Rover rover = new Rover(grid, coordinate, direction);
					Mission mission = new Mission(rover, missionInstructions);
					return mission;};
	}
	
	public static List<List<String>> partition(List<String> list, int chunkSize) {
		List<List<String>> result = new ArrayList<>();
		for (int index = 0; index < list.size(); index += chunkSize) {
			result.add(list.subList(index, Math.min(index + chunkSize, list.size())));
		}
		return result;
	}

}
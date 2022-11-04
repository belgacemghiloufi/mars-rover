package fr.tdd.kata.marsrover.adapters;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import fr.tdd.kata.marsrover.domain.model.Coordinate;
import fr.tdd.kata.marsrover.domain.model.Direction;
import fr.tdd.kata.marsrover.domain.model.Grid;
import fr.tdd.kata.marsrover.domain.model.Mission;
import fr.tdd.kata.marsrover.domain.model.Rover;
import fr.tdd.kata.marsrover.domain.ports.MissionInstructionReader;

public class FileMissionInstructionReader implements MissionInstructionReader {

	@Override
	public String getMissionInstructions(String fileLocation) throws Exception {
		return Files.readString(Paths.get(fileLocation), StandardCharsets.UTF_8);
	}

	@Override
	public Grid getGrid(String fileLocation) throws Exception {
		Grid result = null;
		String firstLine = Files.lines(Paths.get(fileLocation))
									   .filter(s -> !s.equals(""))
				                       .findFirst().get();
		String parts[] = firstLine.split(" ");
		if (parts.length == 2) {
			result = new Grid(Integer.parseInt(parts[0]) + 1, Integer.parseInt(parts[1]) + 1);
		}
		return result;
	}

	@Override
	public List<Mission> getMissions(String fileLocation) throws Exception {
		
		List<Mission> result = new ArrayList<>();
		
		Grid grid = getGrid(fileLocation);
		
		List<String> instructions = Files.lines(Paths.get(fileLocation))
			  .filter(s -> !s.equals(""))
			  .skip(1)
			  .collect(Collectors.toList());
		
		for(int i = 0 ; i <= (instructions.size() / 2) ; i+=2 ) {
			String roverPosition = instructions.get(i);
			String parts[] = roverPosition.split(" ");
			String missionInstructions = instructions.get(i+1);
			Coordinate coordinate = new Coordinate(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]));
			Direction direction = Direction.of(parts[2]);
			Mission mission = new Mission(new Rover(grid, coordinate, direction), missionInstructions);
			result.add(mission);
		}
		
		return result;
	}

}
